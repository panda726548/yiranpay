package com.yiranpay.gateway.service.base;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.HtmlUtils;

import com.yiranpay.common.config.Global;
import com.yiranpay.common.utils.property.DefaultPropertyHelper;
import com.yiranpay.gateway.domain.GatewaySecretKey;
import com.yiranpay.gateway.domain.SysApiLog;
import com.yiranpay.gateway.service.IGatewayCardBinService;
import com.yiranpay.gateway.service.IGatewaySecretKeyService;
import com.yiranpay.gateway.service.ISysApiLogService;
import com.yiranpay.paychannel.service.ITrFcTargetInstRelationService;
/**
 * <p>
 * 支付网关服务基础类
 * </p>
 */
public class GateWayServiceBase {
	private Logger logger = LoggerFactory.getLogger(GateWayServiceBase.class);
    //配置文件名称
  	private static final String BASE_FILE_NAME ="app-config.properties";
  	//配置文件文所在件夹名
  	private static final String BASE ="base";
  	//私钥key
  	private static final String PRIVATE_KEY ="PRIVATE_KEY";
  	//公钥key
  	private static final String PUBLIC_KEY ="PUBLIC_KEY";
    /**
     * 卡Bin服务
     */
    @Autowired
    public IGatewayCardBinService gatewayCardBinService;
    /**
     * 目标机构渠道号映射
     */
    @Autowired
    public ITrFcTargetInstRelationService trFcTargetInstRelationService;
  
  	@Autowired
  	private DefaultPropertyHelper propertyHelper;
  	@Autowired
  	private IGatewaySecretKeyService gatewaySecretKeyService;
  	@Autowired
  	private ISysApiLogService sysApiLogService;
  	/**
  	 * 获取系统RSA私钥
  	 * @return
  	 */
  	public String getPrivateKey(){
  		String privateKey = "";
  		//获取公钥私钥配置
  		propertyHelper.setConfigFileRootPath(Global.getPrivatePublicKeyPath());
  		propertyHelper.setBaseFileName(BASE_FILE_NAME);
  		Properties properties = propertyHelper.getProperties(BASE);
  		privateKey = properties.getProperty(PRIVATE_KEY);
  		return privateKey;
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

  	/**
  	 * 根据商户ID查询商户的公钥
  	 * @param merchantId
  	 * @return
  	 */
  	public String getPublicKeyByMerchantId(String merchantId){
  		String publicKey = "";
  		GatewaySecretKey gatewaySecretKey = new GatewaySecretKey();
  		gatewaySecretKey.setMerchantId(merchantId);
  		List<GatewaySecretKey> list = gatewaySecretKeyService.selectGatewaySecretKeyList(gatewaySecretKey);
  		if(list.size() > 0){
  			publicKey = list.get(0).getPublicKey();
  		}
  		return publicKey;
  	}
    
	public void saveLogs(String partnerId,String logs,long type){
  		SysApiLog sysApiLog = new SysApiLog();
  		sysApiLog.setPartnerid(partnerId);
  		logs = HtmlUtils.htmlEscape(logs);
  		sysApiLog.setLogger(logs);
  		sysApiLog.setType(type);
  		sysApiLogService.insertSysApiLog(sysApiLog);
  	}
}
