package com.yiranpay.gateway;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yiranpay.base.BaseJunit;
import com.yiranpay.common.config.Global;
import com.yiranpay.common.utils.property.DefaultPropertyHelper;
import com.yiranpay.gateway.response.CarBinResponse;
import com.yiranpay.gateway.service.IQueryCardBinService;
import com.yiranpay.gateway.tools.RSAUtils;
import com.yiranpay.gateway.tools.SignUtils;

public class QueryCardBinFacadeTest extends BaseJunit{
	private static Logger logger = LoggerFactory.getLogger(QueryCardBinFacadeTest.class);
	//配置文件名称
  	private static final String BASE_FILE_NAME ="app-config.properties";
  	//配置文件文所在件夹名
  	private static final String BASE ="base";
  	//私钥key
  	private static final String PRIVATE_KEY ="PRIVATE_KEY";
  	//公钥key
  	private static final String PUBLIC_KEY ="PUBLIC_KEY";
	@Autowired
	IQueryCardBinService queryCardBinService;
	@Autowired
  	private DefaultPropertyHelper propertyHelper;
	@Test
	public void queryCardBinTest(){
		Map<String, String> paraMap = new HashMap<String,String>();
		paraMap.put("partner_id", "200000000003");
		paraMap.put("version", "1.0");
		paraMap.put("_input_charset", "UTF-8");
		paraMap.put("service", "query_card_bin");
		paraMap.put("sign", "cCCXjR5qClEdxd5REM7NLsZyx+TX/9hs/5a1LnCgV2RANR/ckW/Hb1eHjwXUjdy7s6gRMUoqdbFYgfT2Gyj5taXTmE5CzSOCJIpFpPw8uBx6Gfz7tB9pieKz7EHjUjLYEPtaz+TR9xTWn4lGUkyJNnkT0B4GFdwch7/YGJgryR0=");
		paraMap.put("sign_type", "RSA");
		paraMap.put("return_url", "http://127.0.0.1/api/yiranpay/gateway/cardBin/queryCardBin");
		paraMap.put("memo", "测试");
		paraMap.put("request_no", "2020011625632410");
		paraMap.put("card_no", "6217710808791636");
		paraMap.put("buss_type", "AUTH");
		try {
			CarBinResponse process = queryCardBinService.process(paraMap);
			
			String jsonData = JSON.toJSONString(process);
			logger.info("卡Bin查询返回对象 : " + jsonData);
			
			//结果验签
			String systemPublicKey = getPublicKey();
			System.out.println("结果验签公钥："+systemPublicKey);
			jsonData =  SignUtils.genSignData(JSON.parseObject(jsonData));
			logger.info("验签原串："+jsonData);
			boolean flag = RSAUtils.verify(jsonData.getBytes(), systemPublicKey, process.getSign());
			
			if(flag){
				logger.info("结果验签成功！");
			}else{
				logger.info("结果验签失败！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
  	 * 获取系统RSA公钥
  	 * @return
  	 */
  	public String getPublicKey(){
  		String privateKey = "";
  		//获取公钥私钥配置
  		propertyHelper.setConfigFileRootPath(Global.getPrivatePublicKeyPath());
  		propertyHelper.setBaseFileName(BASE_FILE_NAME);
  		Properties properties = propertyHelper.getProperties(BASE);
  		privateKey = properties.getProperty(PUBLIC_KEY);
  		return privateKey;
  	}
}
