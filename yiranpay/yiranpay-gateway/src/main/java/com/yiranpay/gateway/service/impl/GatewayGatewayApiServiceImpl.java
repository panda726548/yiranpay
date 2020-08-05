package com.yiranpay.gateway.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.yiranpay.common.utils.DateUtils;
import com.yiranpay.common.utils.StrUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiranpay.gateway.mapper.GatewayGatewayApiMapper;
import com.yiranpay.gateway.domain.GatewayGatewayApi;
import com.yiranpay.gateway.domain.GatewayMerchantConfig;
import com.yiranpay.gateway.service.IGatewayGatewayApiService;
import com.yiranpay.common.constant.UserConstants;
import com.yiranpay.common.core.domain.Ztree;
import com.yiranpay.common.core.text.Convert;

/**
 * 接口权限Service业务层处理
 * 
 * @author panda
 * @date 2020-01-06
 */
@Service
public class GatewayGatewayApiServiceImpl implements IGatewayGatewayApiService 
{
    @Autowired
    private GatewayGatewayApiMapper gatewayGatewayApiMapper;

    /**
     * 查询接口权限
     * 
     * @param apiId 接口权限ID
     * @return 接口权限
     */
    @Override
    public GatewayGatewayApi selectGatewayGatewayApiById(Long apiId)
    {
        return gatewayGatewayApiMapper.selectGatewayGatewayApiById(apiId);
    }

    /**
     * 查询接口权限列表
     * 
     * @param gatewayGatewayApi 接口权限
     * @return 接口权限
     */
    @Override
    public List<GatewayGatewayApi> selectGatewayGatewayApiList(GatewayGatewayApi gatewayGatewayApi)
    {
        return gatewayGatewayApiMapper.selectGatewayGatewayApiList(gatewayGatewayApi);
    }

    /**
     * 新增接口权限
     * 
     * @param gatewayGatewayApi 接口权限
     * @return 结果
     */
    @Override
    public int insertGatewayGatewayApi(GatewayGatewayApi gatewayGatewayApi)
    {
        return gatewayGatewayApiMapper.insertGatewayGatewayApi(gatewayGatewayApi);
    }

    /**
     * 修改接口权限
     * 
     * @param gatewayGatewayApi 接口权限
     * @return 结果
     */
    @Override
    public int updateGatewayGatewayApi(GatewayGatewayApi gatewayGatewayApi)
    {
        return gatewayGatewayApiMapper.updateGatewayGatewayApi(gatewayGatewayApi);
    }

    /**
     * 删除接口权限对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGatewayGatewayApiByIds(String ids)
    {
        return gatewayGatewayApiMapper.deleteGatewayGatewayApiByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除接口权限信息
     * 
     * @param apiId 接口权限ID
     * @return 结果
     */
    @Override
    public int deleteGatewayGatewayApiById(Long apiId)
    {
        return gatewayGatewayApiMapper.deleteGatewayGatewayApiById(apiId);
    }

	@Override
	public List<GatewayGatewayApi> selectMenuList(GatewayGatewayApi gatewayGatewayApi) {
		List<GatewayGatewayApi> apiList = gatewayGatewayApiMapper.selectGatewayGatewayApiList(gatewayGatewayApi);
        return apiList;
	}

	@Override
	public int selectCountApiByParentId(Long parentId) {
		return gatewayGatewayApiMapper.selectCountApiByParentId(parentId);
	}

	@Override
	public int deleteApiById(Long apiId) {
		return gatewayGatewayApiMapper.deleteGatewayGatewayApiById(apiId);
	}

	@Override
	public String checkApiNameUnique(GatewayGatewayApi gatewayGatewayApi) {
		Long apiId = StrUtils.isNull(gatewayGatewayApi.getApiId()) ? -1L : gatewayGatewayApi.getApiId();
		GatewayGatewayApi info = gatewayGatewayApiMapper.checkAPINameUnique(gatewayGatewayApi.getApiName(), gatewayGatewayApi.getParentId());
        if (StrUtils.isNotNull(info) && info.getApiId().longValue() != apiId.longValue())
        {
            return UserConstants.API_NAME_NOT_UNIQUE;
        }
        return UserConstants.API_NAME_UNIQUE;
	}

	@Override
	public GatewayGatewayApi selectApiMenuById(Long apiId) {
		return gatewayGatewayApiMapper.selectGatewayGatewayApiById(apiId);
	}

	@Override
	public List<Ztree> apiMenuTreeData() {
		List<GatewayGatewayApi> apimenuList = selectMenuAll();
        List<Ztree> ztrees = initZtree(apimenuList);
        return ztrees;
	}
	
	public List<GatewayGatewayApi> selectMenuAll()
    {
        List<GatewayGatewayApi> menuList = gatewayGatewayApiMapper.selectApiMenuAll();
        return menuList;
    }
	
	/**
     * 对象转菜单树
     * 
     * @param menuList 菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<GatewayGatewayApi> apimenuList)
    {
        return initZtree(apimenuList,null, false);
    }
    
    
    
    public String transMenuName(GatewayGatewayApi menu, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getApiName());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;</font>");
        }
        return sb.toString();
    }

	@Override
	public List<Ztree> apiMenuAuthTreeData(GatewayMerchantConfig config) {
		String merchantId = config.getMerchantId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<GatewayGatewayApi> menuList = selectMenuAll();
        if (StrUtils.isNotNull(merchantId))
        {
            List<String> roleMenuList = gatewayGatewayApiMapper.selectApiAuthTree(merchantId);
            ztrees = initZtree(menuList, roleMenuList, true);
        }
        else
        {
            ztrees = initZtree(menuList, null, true);
        }
        return ztrees;
	}
	
    public List<Ztree> initZtree(List<GatewayGatewayApi> menuList, List<String> apiAuthList, boolean permsFlag)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StrUtils.isNotNull(apiAuthList);
        for (GatewayGatewayApi menu : menuList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getApiId());
            ztree.setpId(menu.getParentId());
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getApiName());
            if (isCheck)
            {
                ztree.setChecked(apiAuthList.contains(String.valueOf(menu.getApiId())));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

}
