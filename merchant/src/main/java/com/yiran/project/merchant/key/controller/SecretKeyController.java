package com.yiran.project.merchant.key.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiran.common.utils.security.ShiroUtils;
import com.yiran.framework.web.controller.BaseController;
import com.yiran.framework.web.domain.AjaxResult;
import com.yiran.framework.web.page.TableDataInfo;
import com.yiran.project.merchant.key.domain.GatewaySecretKey;
import com.yiran.project.merchant.key.service.GateWayServiceBase;
import com.yiran.project.merchant.key.service.IGatewaySecretKeyService;

/**
 * 商户秘钥管理Controller
 * 
 * @author panda
 * @date 2020-01-15
 */
@Controller
@RequestMapping("/merchanr/key")
public class SecretKeyController extends BaseController
{
	private static Logger logger = LoggerFactory.getLogger(SecretKeyController.class);
    private String prefix = "merchant/key";

    @Autowired
    private IGatewaySecretKeyService gatewaySecretKeyService;
    @Autowired
    private GateWayServiceBase gateWayServiceBase;
    
    @GetMapping()
    public String key()
    {
        return prefix + "/key";
    }

    /**
     * 查询商户秘钥管理列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GatewaySecretKey gatewaySecretKey)
    {
        startPage();
        List<GatewaySecretKey> list = gatewaySecretKeyService.selectGatewaySecretKeyList(gatewaySecretKey);
        return getDataTable(list);
    }

    @GetMapping("/getSysPublicKey")
    @ResponseBody
    public AjaxResult getSysPublicKey()
    {
    	String publicKey = gateWayServiceBase.getPublicKey();
    	logger.info("获取系统公钥："+publicKey);
        return AjaxResult.success(publicKey);
    }

    /**
     * 新增商户秘钥管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商户秘钥管理
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GatewaySecretKey gatewaySecretKey)
    {
    	gatewaySecretKey.setMerchantId(ShiroUtils.getUserId());
    	gatewaySecretKey.setSignType("RSA");
        return toAjax(gatewaySecretKeyService.insertGatewaySecretKey(gatewaySecretKey));
    }
    /**
     * 判断当前商户是否存在公钥   存在 true 不存在 false
     * @param gatewaySecretKey
     * @return
     */
    @GetMapping("/isPublicKey")
    @ResponseBody
    public AjaxResult isPublicKey()
    {
    	GatewaySecretKey key = new GatewaySecretKey();
    	key.setMerchantId(ShiroUtils.getUserId());
    	List<GatewaySecretKey> list = gatewaySecretKeyService.selectGatewaySecretKeyList(key);
    	boolean flag = false;
    	if(list.size() > 0 ){//存在
    		flag = true;
    	}
    	return AjaxResult.success(flag);
    }

    /**
     * 修改商户秘钥管理
     */
    @GetMapping("/edit/{secretkeyId}")
    public String edit(@PathVariable("secretkeyId") Long secretkeyId, ModelMap mmap)
    {
        GatewaySecretKey gatewaySecretKey = gatewaySecretKeyService.selectGatewaySecretKeyById(secretkeyId);
        mmap.put("gatewaySecretKey", gatewaySecretKey);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户秘钥管理
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GatewaySecretKey gatewaySecretKey)
    {
        return toAjax(gatewaySecretKeyService.updateGatewaySecretKey(gatewaySecretKey));
    }

    /**
     * 删除商户秘钥管理
     */
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gatewaySecretKeyService.deleteGatewaySecretKeyByIds(ids));
    }
}
