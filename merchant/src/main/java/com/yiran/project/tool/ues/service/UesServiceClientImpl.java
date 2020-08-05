package com.yiran.project.tool.ues.service;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.project.tool.ues.domain.SysUesEnData;
import com.yiran.project.tool.ues.enums.EncryptType;
import com.yiran.project.tool.ues.utils.AESEncryptUtil;
import com.yiran.project.tool.ues.utils.DESEncryptUtil;


/**
 * UES客户端
 * @author pandaa
 *
 */
@Service
public class UesServiceClientImpl implements IUesServiceClient {

	private Logger    logger = LoggerFactory.getLogger(getClass());
	
	private static final String salt ="XkwE&p!^RsOuH#iZ";
	@Autowired
	private ISysUesEnDataService uesEnDataService;
	
	@Override
	public String getDataByTicket(String ticket,EncryptType encryptType) {
		if (StringUtils.isBlank(ticket)) {
            return null;
        }
		try {
			//原文
			String data = null;
			//远程调用计时开始
	        long beginTime = System.currentTimeMillis();
			//根据ticket票据获取加密对象
	        SysUesEnData uesEnData = uesEnDataService.selectUesEnDataByTicket(ticket);
	        if(uesEnData == null){
	        	return "根据ticket票据没有获取到加密数据，请确认票据是否正确";
	        }
			//获取加密密码
			String password = uesEnData.getEncryptKey();
			//获取加密密文
			String encryptData = uesEnData.getEncryptData();
			switch (encryptType) {
			case DES:
				data = DESEncryptUtil.decrypt(encryptData, password);
				break;
			case AES:
				data = AESEncryptUtil.decrypt(encryptData, password);
				break;
			}
			//远程调用计时结束
            long consumeTime = System.currentTimeMillis() - beginTime;
            //log远程调用耗时和响应
            logger.info("远程调用：{} 耗时: {} (ms)", new Object[] {"uesServiceClient#getDataByTicket", consumeTime });
			return data;
		} catch (Exception e) {
			logger.error("调用ues 加密出错", e);
            throw new RuntimeException(e);
		}
	}

	

}
