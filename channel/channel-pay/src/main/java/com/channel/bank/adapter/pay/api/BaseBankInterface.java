package com.channel.bank.adapter.pay.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.channel.bank.adapter.pay.domain.ChannelFundResult;
import com.channel.bank.adapter.pay.domain.ChannelRequest;
import com.channel.bank.adapter.pay.domain.EBankChannelFundRequest;
import com.channel.bank.adapter.pay.property.PropertyHelper;
import com.channel.bank.adapter.pay.utils.MapUtils;

/**
 * 
 * <p>
 * 基础类
 * </p>
 */
public abstract class BaseBankInterface {
	private Logger logger = LoggerFactory.getLogger(BaseBankInterface.class);
	/**
	 * Map转换为html字符串
	 * 
	 * @param formData
	 * @return
	 */
	protected String getFormData(Map<String, String> formData) {

		if (formData == null)
			return StringUtils.EMPTY;

		StringBuffer sBuffer = new StringBuffer();
		for (String key : formData.keySet()) {
			sBuffer.append("<input type=\"hidden\" name=\"").append(key)
					.append("\" value=\"").append(formData.get(key))
					.append("\" />");
		}

		return sBuffer.toString();
	}

	/**
	 * 生成HTML表单
	 * 
	 * @param formData
	 * @param bankPostUrl
	 * @return
	 */
	protected String getHtmlData(String formData, String bankPostUrl) {
		String method = "POST";
		if (StringUtils.isEmpty(formData))
			method = "GET";

		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(
				"<html><head></head><body><form id=\"frmBankID\" name=\"frmBankName\" method=\"")
				.append(method)
				.append("\" action=\"")
				.append(bankPostUrl)
				.append("\">")
				.append(formData)
				.append("</form><script language=\"javascript\">document.getElementById(\"frmBankID\").submit();</script></body></html>");

		return sBuffer.toString();
	}

	/**
	 * MAP转换成JSON
	 * 
	 * @param map
	 * @return
	 */
	protected String getMapToJson(Map<String, String> map) {
		return MapUtils.mapToJson(map);
	}
	
	/**
	 * JSON字符串转成MAP
	 * 
	 * @param ext
	 * @return
	 */
	protected Map<String, String> getJsonToMap(String ext) {

		return MapUtils.jsonToMap(ext);
	}
	
	protected ChannelFundResult convertApplyResult(
			ChannelFundResult channelFundResult,
			EBankChannelFundRequest cmfRequest) {
		if (!channelFundResult.isSuccess()) {
			return channelFundResult;
		}
			logger.info("资金源[" + cmfRequest.getFundChannelCode() + "]签名完成");
		if (logger.isDebugEnabled()) {
			String signhtml = this.getHtmlData(this.getFormData(this
					.getJsonToMap(channelFundResult.getExtension())),
					channelFundResult.getInstUrl());
			logger.debug(signhtml);
		}
		return channelFundResult;

	}
	protected String getChinaHtmlData(String formData, String bankPostUrl) {
		String method = "GET";

		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(
				"<html><head></head><body><form id=\"frmBankID\" name=\"frmBankName\" method=\"")
				.append(method)
				.append("\" action=\"")
				.append(bankPostUrl)
				.append("\">")
				.append(formData)
				.append("</form><script language=\"javascript\">document.getElementById(\"frmBankID\").submit();</script></body></html>");

		return sBuffer.toString();
	}
	protected ChannelFundResult convertChinaPayApplyResult(
			ChannelFundResult channelFundResult,
			EBankChannelFundRequest cmfRequest) {
		if (!channelFundResult.isSuccess()) {
			return channelFundResult;
		}
		String signhtml = this.getChinaHtmlData(this.getFormData(this
				.getJsonToMap(channelFundResult.getExtension())),
				channelFundResult.getInstUrl());
		logger.debug(signhtml);
		return channelFundResult;

	}


	/**
	 * 返回订单日期
	 * 
	 * @param cmfRequest
	 * @return
	 */
	protected Date getSignOrderDate(
			EBankChannelFundRequest eBankChannelFundRequest) {
		Date date = eBankChannelFundRequest.getInstOrderSubmitTime();
		return date == null ? new Date() : date;
	}

	/**
	 * 返回接口操作信息
	 * 
	 * @param request
	 * @return
	 */
	protected String getInfo(ChannelRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append("FundChannelApi=").append(request.getFundChannelCode())
				.append("-").append(request.getApiType().getCode())
				.append(",InstOrderNo=").append(request.getInstOrderNo());
		return sb.toString();
	}
}
