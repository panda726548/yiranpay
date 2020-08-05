package com.yiranpay.message.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.utils.DateUtils;
import com.yiranpay.common.utils.StrUtils;
import com.yiranpay.message.constants.MsgSmsConfigConstants;
import com.yiranpay.message.constants.SmsResultEnum;
import com.yiranpay.message.domain.MsgSendRecord;
import com.yiranpay.message.domain.MsgSmsConfig;
import com.yiranpay.message.domain.MsgSmsTemplate;
import com.yiranpay.message.mapper.MsgSendRecordMapper;
import com.yiranpay.message.mapper.MsgSmsConfigMapper;
import com.yiranpay.message.mapper.MsgSmsTemplateMapper;
import com.yiranpay.message.service.IMsgSmsSendService;
import com.yiranpay.message.util.StringTemplateUtils;
import com.yiranpay.message.vo.SendAuthCodeRequest;
import com.yiranpay.message.vo.SendSMSResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 短息发送和验证服务
 *
 * @author glb
 * @date 2020-04-06
 */
@Service
public class MsgSmsSendService implements IMsgSmsSendService {
	private static final Logger logger = LoggerFactory.getLogger(MsgSmsSendService.class);
	@Autowired
	private MsgSmsTemplateMapper msgSmsTemplateMapper;
	@Autowired
	private MsgSmsConfigMapper msgSmsConfigMapper;
	@Autowired
	private MsgSendRecordMapper msgSendRecordMapper;

	/**
	 * 发送验证码
	 * @param request 发送短息请求
	 * @return
	 */
	@Override
	public AjaxResult sendAuthCode(SendAuthCodeRequest request) {
		if(StrUtils.isNull(request.getSmsTemplateCode())){
			return AjaxResult.error("模版编码不能为空");
		}else if(StrUtils.isBlank(request.getSendRecordPhone())){
			return AjaxResult.error("手机号不能为空");
		}
		AjaxResult ajaxResult;

		//根据短信模版编码获取模板信息
		MsgSmsTemplate smsTemplate = msgSmsTemplateMapper.selectMsgSmsTemplateByCode(request.getSmsTemplateCode());
		if(StrUtils.isNotNull(smsTemplate)){
			//获取模板内容
			String templateContent = smsTemplate.getSmsTemplateContent();
			//6位随机验证码
			String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
			//准备参数
			Map<String, String> data =new HashMap<String, String>();
			data.put("code", verifyCode);
			//获取发送短信流水号
			String msgOrderNo = RandomUtil.randomString(32);
			//替换模板中的数据,获取最终发送的消息
			String sendData = StringTemplateUtils.render(templateContent, data);

			//设置短信发送记录
			MsgSendRecord msgSendRecord = saveMsgSendRecord(request, smsTemplate, verifyCode, msgOrderNo, sendData);

			//调用短息平台发送短信
			SendSMSResponse sendSMSResponse = sendSMS(verifyCode,request.getSendRecordPhone(), request.getSmsTemplateCode());

			if("OK".equals(sendSMSResponse.getCode())){
				msgSendRecord.setSendRecordStatus("S");
				updateMsgSendRecord(msgOrderNo, sendSMSResponse, msgSendRecord);

				ajaxResult = AjaxResult.success(SmsResultEnum.getByCode(sendSMSResponse.getCode()).getMessage());
				ajaxResult.put("msgOrderNo", msgOrderNo);
				ajaxResult.put("code", "0000");
			}else{
				msgSendRecord.setSendRecordStatus("F");
				updateMsgSendRecord(msgOrderNo, sendSMSResponse, msgSendRecord);

				ajaxResult = AjaxResult.error(SmsResultEnum.getByCode(sendSMSResponse.getCode()).getMessage());
				ajaxResult.put("code", "0003");
			}
		}else{
			ajaxResult = AjaxResult.error("获取短信模版编码失败");
			ajaxResult.put("code", "500");
		}
		return ajaxResult;
	}

	/**
	 * 保存发送记录数据
	 * @param request
	 * @param smsTemplate
	 * @param verifyCode
	 * @param msgOrderNo
	 * @param sendData
	 * @return
	 */
	private MsgSendRecord saveMsgSendRecord(SendAuthCodeRequest request, MsgSmsTemplate smsTemplate, String verifyCode, String msgOrderNo, String sendData) {
		MsgSendRecord msgSendRecord = new MsgSendRecord();
		msgSendRecord.setMsgOrderNo(msgOrderNo);
		msgSendRecord.setSendRecordBusinessType(smsTemplate.getSmsTemplateBusinessType());
		msgSendRecord.setSendRecordTemplateCode(request.getSmsTemplateCode());
		msgSendRecord.setSendRecordPhone(request.getSendRecordPhone());
		msgSendRecord.setSendRecordTemplateContent(sendData);
		msgSendRecord.setSendRecordTime(new Date());
		msgSendRecord.setSendRecordVerifyCode(verifyCode);
		msgSendRecord.setCreateTime(new Date());
		msgSendRecordMapper.insertMsgSendRecord(msgSendRecord);
		return msgSendRecord;
	}

	/**
	 * 更新发送记录数据
	 * @param msgOrderNo
	 * @param sendSMSResponse
	 * @param msgSendRecord
	 */
	private void updateMsgSendRecord(String msgOrderNo, SendSMSResponse sendSMSResponse, MsgSendRecord msgSendRecord) {
		msgSendRecord.setSendRecordSmsId(sendSMSResponse.getSmsid());
		msgSendRecord.setSendRecordCode(sendSMSResponse.getCode());
		msgSendRecord.setSendRecordMsg(sendSMSResponse.getMsg());
		msgSendRecord.setMsgOrderNo(msgOrderNo);
		msgSendRecord.setRemark(sendSMSResponse.getBizId());
		msgSendRecord.setSendRecordResponseTime(new Date());
		msgSendRecord.setUpdateTime(new Date());
		msgSendRecordMapper.updateMsgSendRecord(msgSendRecord);
	}

	/**
	 * 调用短信平台发送短信
	 * @param verifyCode, phone, templateId
	 */
	private SendSMSResponse sendSMS(String verifyCode,String phone, String templateCode) {
		SendSMSResponse sendSMSResponse = new SendSMSResponse();

		//调用查询
		MsgSendRecord qryMsgSendRecord = new MsgSendRecord();
		qryMsgSendRecord.setSendRecordPhone(phone);
		qryMsgSendRecord.setSendRecordTime(DateUtils.getNowDate());
		int smsCount = msgSendRecordMapper.getSendSmSCount(qryMsgSendRecord);

		//判断当天该手机号发送短信次数是否超出限次
		MsgSmsConfig smsConfig = msgSmsConfigMapper.selectSmsConfigBySmsKey(MsgSmsConfigConstants.KEY_SMS_LIMIT_TIMES);
		//平台配置最大次数
		int maxCount = Integer.parseInt(smsConfig.getSmsConfigValue());
		if(smsCount >= maxCount){
			sendSMSResponse.setCode("0003");
			sendSMSResponse.setMsg("短息发送失败，超出短信发送限额！");
			sendSMSResponse.setStatus("F");
		}else{
			 //调平台发短息
			 try {
				//产品名称:云通信短信API产品,开发者无需替换
				String product = "Dysmsapi";
				//产品域名,开发者无需替换
				String domain = "dysmsapi.aliyuncs.com";

				//阿里云AK
				String accessKeyId = msgSmsConfigMapper.selectSmsConfigBySmsKey(MsgSmsConfigConstants.KEY_SMS_APPID).getSmsConfigValue();
				String accessKeySecret = msgSmsConfigMapper.selectSmsConfigBySmsKey(MsgSmsConfigConstants.KEY_SMS_APPSECRET).getSmsConfigValue();
				//可自助调整超时时间
				System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
				System.setProperty("sun.net.client.defaultReadTimeout", "10000");
				//初始化acsClient,暂不支持region化
				IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
				DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
				IAcsClient acsClient = new DefaultAcsClient(profile);
				
				//组装请求对象-具体描述见控制台-文档部分内容
		        SendSmsRequest request = new SendSmsRequest();
		        //必填:待发送手机号
		        request.setPhoneNumbers(phone);
		        //必填:短信签名-可在短信控制台中找到
		        request.setSignName(msgSmsConfigMapper.selectSmsConfigBySmsKey(MsgSmsConfigConstants.KEY_SMS_SHORT_SIGNATURE).getSmsConfigValue());
		        //必填:短信模板-可在短信控制台中找到
		        request.setTemplateCode(templateCode);
		        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		        request.setTemplateParam("{\"code\":\""+verifyCode+"\"}");
		        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		        //request.setSmsUpExtendCode("90997");
		        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		        request.setOutId("wsl");
		        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		        sendSMSResponse.setCode(sendSmsResponse.getCode());
				sendSMSResponse.setMsg(SmsResultEnum.getByCode(sendSMSResponse.getCode()).getMessage());
				sendSMSResponse.setBizId(sendSmsResponse.getBizId());
			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		return sendSMSResponse;
	}

	/**
	 * 验证手机号和验证码
	 * @param request 发送短息请求
	 * @return
	 */
	@Override
	public AjaxResult verifyMobileAuthCode(SendAuthCodeRequest request) {
		if(StrUtils.isBlank(request.getSendRecordPhone())){
			return AjaxResult.error("手机号不能为空");
		}else if(StrUtils.isBlank(request.getSendRecordVerifyCode())){
			return AjaxResult.error("短信验证码不能为空");
		}
		AjaxResult ajaxResult;

		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		//根据流水号获取消息对象
		MsgSendRecord  msgLog = null;

		MsgSendRecord msgSendRecord = new MsgSendRecord();
		msgSendRecord.setSendRecordPhone(request.getSendRecordPhone());
		msgSendRecord.setSendRecordVerifyCode(request.getSendRecordVerifyCode());
		List<MsgSendRecord> logList =  msgSendRecordMapper.selectMsgSendRecordList(msgSendRecord);
		if(StrUtils.isNotEmpty(logList)){
			msgLog = logList.get(0);
			if(request.getSendRecordPhone().equals(msgLog.getSendRecordPhone()) && request.getSendRecordVerifyCode().equals(msgLog.getSendRecordVerifyCode())){
				//判断是否在有效期时间内
				MsgSmsConfig smsConfig =msgSmsConfigMapper.selectSmsConfigBySmsKey(MsgSmsConfigConstants.KEY_SMS_EFFECTIVE_TIME);
				int minutes = 0;
				try {
					//短信发送时间
					String fromDate = simpleFormat.format(msgLog.getSendRecordTime());
					//当前时间
					String toDate = simpleFormat.format(new Date());
					long from = simpleFormat.parse(fromDate).getTime();
					long to = simpleFormat.parse(toDate).getTime();
					minutes = (int) ((to - from)/(1000 * 60));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//获取设置时间有效时间
				int effective_time = Integer.parseInt(smsConfig.getSmsConfigValue());
				if(minutes > effective_time){
					ajaxResult = AjaxResult.error("验证码已过期");
					ajaxResult.put("code", "0003");
					return ajaxResult;
				}
				ajaxResult = AjaxResult.success("验证通过");
				ajaxResult.put("code", "0000");
			}else{
				ajaxResult = AjaxResult.error("验证码不正确");
				ajaxResult.put("code", "0004");
			}
			return ajaxResult;
		}
		return null;
	}
}
