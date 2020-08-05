package com.yiranpay.gateway.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.yiranpay.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yiranpay.gateway.mapper.GatewayApiAuthMapper;
import com.yiranpay.gateway.mapper.GatewayGatewayApiMapper;
import com.yiranpay.gateway.mapper.GatewayMerchantConfigMapper;
import com.yiranpay.gateway.domain.GatewayApiAuth;
import com.yiranpay.gateway.domain.GatewayGatewayApi;
import com.yiranpay.gateway.domain.GatewayMerchantConfig;
import com.yiranpay.gateway.service.IGatewayMerchantConfigService;
import com.yiranpay.common.core.text.Convert;

/**
 * 商户接口配置Service业务层处理
 * 
 * @author panda
 * @date 2020-01-06
 */
@Service
public class GatewayMerchantConfigServiceImpl implements IGatewayMerchantConfigService 
{
    @Autowired
    private GatewayMerchantConfigMapper gatewayMerchantConfigMapper;
    @Autowired
    private GatewayApiAuthMapper gatewayApiAuthMapper;
    @Autowired
    private GatewayGatewayApiMapper gatewayGatewayApiMapper;
    /**
     * 查询商户接口配置
     * 
     * @param id 商户接口配置ID
     * @return 商户接口配置
     */
    @Override
    public GatewayMerchantConfig selectGatewayMerchantConfigById(Long id)
    {
        return gatewayMerchantConfigMapper.selectGatewayMerchantConfigById(id);
    }

    /**
     * 查询商户接口配置列表
     * 
     * @param gatewayMerchantConfig 商户接口配置
     * @return 商户接口配置
     */
    @Override
    public List<GatewayMerchantConfig> selectGatewayMerchantConfigList(GatewayMerchantConfig gatewayMerchantConfig)
    {
        return gatewayMerchantConfigMapper.selectGatewayMerchantConfigList(gatewayMerchantConfig);
    }

    /**
     * 新增商户接口配置
     * 
     * @param gatewayMerchantConfig 商户接口配置
     * @return 结果
     */
    @Override
    @Transactional
    public int insertGatewayMerchantConfig(GatewayMerchantConfig gatewayMerchantConfig)
    {
    	//根据接口id获取接口名称然后拼接
    	String availableApi = "";
    	StringBuffer apiMenus = new StringBuffer();
    	Long[] menuIds = gatewayMerchantConfig.getMenuIds();
    	for (int i = 0; i < menuIds.length; i++) {
    		GatewayGatewayApi gatewayApi = gatewayGatewayApiMapper.selectGatewayGatewayApiById(menuIds[i]);
    		if(gatewayApi.getParentId().intValue() !=0){
    			apiMenus.append(gatewayApi.getApiName());
        		apiMenus.append(",");
    		}
		}
    	if(apiMenus.length()>0){
    		availableApi = apiMenus.substring(0, apiMenus.length()-1);
    	}
    	gatewayMerchantConfig.setAvailableApi(availableApi);
    	gatewayMerchantConfigMapper.insertGatewayMerchantConfig(gatewayMerchantConfig);
        return insertApiMenuAuth(gatewayMerchantConfig);
    }

    private int insertApiMenuAuth(GatewayMerchantConfig gatewayMerchantConfig) {
    	int rows = 1;
        List<GatewayApiAuth> list = new ArrayList<GatewayApiAuth>();
        for (Long apiId : gatewayMerchantConfig.getMenuIds())
        {
        	GatewayApiAuth rm = new GatewayApiAuth();
            rm.setMerchantId(gatewayMerchantConfig.getMerchantId());
            rm.setApiId(apiId);
            list.add(rm);
        }
        if (list.size() > 0)
        {
            rows = gatewayApiAuthMapper.batchApiMenuAuth(list);
        }
        return rows;
	}

	/**
     * 修改商户接口配置
     * 
     * @param gatewayMerchantConfig 商户接口配置
     * @return 结果
     */
    @Override
    @Transactional
    public int updateGatewayMerchantConfig(GatewayMerchantConfig gatewayMerchantConfig)
    {
    	//根据接口id获取接口名称然后拼接
    	String availableApi = "";
    	StringBuffer apiMenus = new StringBuffer();
    	Long[] menuIds = gatewayMerchantConfig.getMenuIds();
    	for (int i = 0; i < menuIds.length; i++) {
    		GatewayGatewayApi gatewayApi = gatewayGatewayApiMapper.selectGatewayGatewayApiById(menuIds[i]);
    		if(gatewayApi.getParentId().intValue() !=0){
    			apiMenus.append(gatewayApi.getApiName());
        		apiMenus.append(",");
    		}
		}
    	if(apiMenus.length()>0){
    		availableApi = apiMenus.substring(0, apiMenus.length()-1);
    	}
    	gatewayMerchantConfig.setAvailableApi(availableApi);
    	gatewayMerchantConfigMapper.updateGatewayMerchantConfig(gatewayMerchantConfig);
    	//删除这个商户对应的权限
    	gatewayApiAuthMapper.deleteGatewayApiAuthByMerchantId(gatewayMerchantConfig.getMerchantId());
        return insertApiMenuAuth(gatewayMerchantConfig);
    }

    /**
     * 删除商户接口配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGatewayMerchantConfigByIds(String ids)
    {
        return gatewayMerchantConfigMapper.deleteGatewayMerchantConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商户接口配置信息
     * 
     * @param id 商户接口配置ID
     * @return 结果
     */
    @Override
    public int deleteGatewayMerchantConfigById(Long id)
    {
        return gatewayMerchantConfigMapper.deleteGatewayMerchantConfigById(id);
    }
}
