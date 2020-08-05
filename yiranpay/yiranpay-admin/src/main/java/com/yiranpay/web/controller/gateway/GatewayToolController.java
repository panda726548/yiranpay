package com.yiranpay.web.controller.gateway;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.gateway.tools.RSAUtils;
import com.yiranpay.gateway.tools.SignUtils;
import com.yiranpay.paychannel.utils.MapUtil;

/**
 * 网关工具类（RSA秘钥生成，签名，验签）
 * @author pandaa
 *
 */
@Controller
@RequestMapping("/gateway/tool")
public class GatewayToolController extends BaseController{
	protected static final Logger logger = LoggerFactory.getLogger(GatewayToolController.class);
	private String prefix = "gateway/tool";
	
	@GetMapping("/toolpage")
    public String add()
    {
        return prefix + "/toolpage";
    }
	
	/**
	 * 生成RSA key
	 * @return
	 */
	@PostMapping("/createRSAKey")
    @ResponseBody
    public AjaxResult createRSAKey()
    {
		Map<String,String> keys = new HashMap<String,String>();
		try {
			Map<String,Object> keyMap = RSAUtils.genKeyPair();
			String publicKey = RSAUtils.getPublicKey(keyMap);
			logger.info("RSA秘钥生成-RSA公钥：{}",publicKey);
			String privateKey = RSAUtils.getPrivateKey(keyMap);
			logger.info("RSA秘钥生成-RSA私钥：{}",privateKey);
			keys.put("publicKey", publicKey);
			keys.put("privateKey", privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return AjaxResult.success(keys);
    }
	
	@PostMapping("/rsaPrivateKeyEncryption")
    @ResponseBody
    public AjaxResult rsaPrivateKeyEncryption(@RequestBody String param)
    {
		logger.info("RSA私钥加密请求参数:{}",param);
		Map<String, String> paramMap = MapUtil.jsonToMap(param);
		String privateKey = paramMap.get("inputPrivateKey");
		String jsondata = paramMap.get("jsondata");
		//生成签名原串：
		String sign_src = SignUtils.genSignData(JSON.parseObject(jsondata));
		logger.info("RSA私钥加密-生成签名原串:{}",sign_src);
		//签名sgin
		String sign = "";
		try {
			sign = RSAUtils.sign(sign_src.getBytes(), privateKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("RSA私钥加密-生成签名sign:{}",sign);
		Map<String, String> resultParamMap = new HashMap<String, String>();
		resultParamMap.put("signSrc", sign_src);
		resultParamMap.put("sign", sign);
		return AjaxResult.success(resultParamMap);
    }
	
	@PostMapping("/rsaPublicKeyDecrypt")
    @ResponseBody
    public AjaxResult rsaPublicKeyDecrypt(@RequestBody String param)
    {
		logger.info("RSA公钥解密请求参数:{}",param);
		Map<String, String> paramMap = MapUtil.jsonToMap(param);
		String publicKey = paramMap.get("inputPublicKey");
		String verifyjsondata = paramMap.get("verifyjsondata");
		//生成签名原串：
		String verifysrcdata = SignUtils.genSignData(JSON.parseObject(verifyjsondata));
		logger.info("RSA公钥解密-生成签名原串:{}",verifysrcdata);
		//签名sgin
		boolean verifysignresult = false;
		Map<String, String> jsondata = MapUtil.jsonToMap(verifyjsondata);
		try {
			verifysignresult = RSAUtils.verify(verifysrcdata.getBytes(), publicKey, jsondata.get("sign"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("RSA公钥解密-验签结果:{}",verifysignresult);
		Map<String, Object> resultParamMap = new HashMap<String, Object>();
		resultParamMap.put("verifysrcdata", verifysrcdata);
		resultParamMap.put("verifysignresult", verifysignresult);
		return AjaxResult.success(resultParamMap);
    }
}
