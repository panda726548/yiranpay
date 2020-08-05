package com.yiran.project.merchant.key.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.framework.config.YiRanConfig;
/**
 * <p>
 * 服务基础类
 * </p>
 */
@Service
public class GateWayServiceBase {
    //配置文件名称
  	private static final String BASE_FILE_NAME ="app-config.properties";
  	//配置文件文所在件夹名
  	private static final String BASE ="base";
  	//公钥key
  	private static final String PUBLIC_KEY ="PUBLIC_KEY";
  
  	@Autowired
  	private DefaultPropertyHelper propertyHelper;
  	
  	
  	/**
  	 * 获取系统RSA公钥
  	 * @return
  	 */
  	public String getPublicKey(){
  		String privateKey = "";
  		//获取公钥私钥配置
  		propertyHelper.setConfigFileRootPath(YiRanConfig.getPrivatePublicKeyPath());
  		propertyHelper.setBaseFileName(BASE_FILE_NAME);
  		Properties properties = propertyHelper.getProperties(BASE);
  		privateKey = properties.getProperty(PUBLIC_KEY);
  		return privateKey;
  	}

    
   
}
