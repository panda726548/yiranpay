package com.yiranpay.gateway.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.yiranpay.common.utils.DateUtils;
import com.yiranpay.common.utils.StrUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiranpay.gateway.mapper.GatewayProductInfoMapper;
import com.yiranpay.gateway.domain.GatewayGatewayApi;
import com.yiranpay.gateway.domain.GatewayProductInfo;
import com.yiranpay.gateway.service.IGatewayProductInfoService;
import com.yiranpay.common.constant.UserConstants;
import com.yiranpay.common.core.domain.Ztree;
import com.yiranpay.common.core.text.Convert;

/**
 * 产品Service业务层处理
 * 
 * @author panda
 * @date 2020-04-12
 */
@Service
public class GatewayProductInfoServiceImpl implements IGatewayProductInfoService 
{
    @Autowired
    private GatewayProductInfoMapper gatewayProductInfoMapper;

    /**
     * 查询产品
     * 
     * @param productId 产品ID
     * @return 产品
     */
    @Override
    public GatewayProductInfo selectGatewayProductInfoById(Long productId)
    {
        return gatewayProductInfoMapper.selectGatewayProductInfoById(productId);
    }

    /**
     * 查询产品列表
     * 
     * @param gatewayProductInfo 产品
     * @return 产品
     */
    @Override
    public List<GatewayProductInfo> selectGatewayProductInfoList(GatewayProductInfo gatewayProductInfo)
    {
        return gatewayProductInfoMapper.selectGatewayProductInfoList(gatewayProductInfo);
    }

    /**
     * 新增产品
     * 
     * @param gatewayProductInfo 产品
     * @return 结果
     */
    @Override
    public int insertGatewayProductInfo(GatewayProductInfo gatewayProductInfo)
    {
        gatewayProductInfo.setCreateTime(DateUtils.getNowDate());
        return gatewayProductInfoMapper.insertGatewayProductInfo(gatewayProductInfo);
    }

    /**
     * 修改产品
     * 
     * @param gatewayProductInfo 产品
     * @return 结果
     */
    @Override
    public int updateGatewayProductInfo(GatewayProductInfo gatewayProductInfo)
    {
        gatewayProductInfo.setUpdateTime(DateUtils.getNowDate());
        return gatewayProductInfoMapper.updateGatewayProductInfo(gatewayProductInfo);
    }

    /**
     * 删除产品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGatewayProductInfoByIds(String ids)
    {
        return gatewayProductInfoMapper.deleteGatewayProductInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品信息
     * 
     * @param productId 产品ID
     * @return 结果
     */
    @Override
    public int deleteGatewayProductInfoById(Long productId)
    {
        return gatewayProductInfoMapper.deleteGatewayProductInfoById(productId);
    }

	@Override
	public String checkProductNameUnique(GatewayProductInfo gatewayProductInfo) {
		Long productId = StrUtils.isNull(gatewayProductInfo.getProductId()) ? -1L : gatewayProductInfo.getProductId();
		GatewayProductInfo info = gatewayProductInfoMapper.checkProductNameUnique(gatewayProductInfo.getProductName(), gatewayProductInfo.getParentId());
        if (StrUtils.isNotNull(info) && info.getProductId().longValue() != productId.longValue())
        {
            return UserConstants.API_NAME_NOT_UNIQUE;
        }
        return UserConstants.API_NAME_UNIQUE;
	}

	@Override
	public GatewayProductInfo selectProductMenuById(Long productId) {
		return gatewayProductInfoMapper.selectGatewayProductInfoById(productId);
	}

	@Override
	public List<Ztree> productMenuTreeData() {
		List<GatewayProductInfo> apimenuList = selectMenuAll();
        List<Ztree> ztrees = initZtree(apimenuList);
        return ztrees;
	}
	
	public List<GatewayProductInfo> selectMenuAll()
    {
        List<GatewayProductInfo> menuList = gatewayProductInfoMapper.selectProductMenuAll();
        return menuList;
    }
	
	/**
     * 对象转菜单树
     * 
     * @param menuList 菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<GatewayProductInfo> productmenuList)
    {
        return initZtree(productmenuList,null, false);
    }
    
    public List<Ztree> initZtree(List<GatewayProductInfo> menuList, List<String> proAuthList, boolean permsFlag)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StrUtils.isNotNull(proAuthList);
        for (GatewayProductInfo menu : menuList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getProductId());
            ztree.setpId(menu.getParentId());
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getProductName());
            if (isCheck)
            {
                ztree.setChecked(proAuthList.contains(String.valueOf(menu.getProductId())));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }
    
    public String transMenuName(GatewayProductInfo menu, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getProductName());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;</font>");
        }
        return sb.toString();
    }

	@Override
	public GatewayProductInfo selectGatewayGatewayProductById(Long parentId) {
		return gatewayProductInfoMapper.selectGatewayProductInfoById(parentId);
	}

	@Override
	public int selectCountProductByParentId(Long productId) {
		return gatewayProductInfoMapper.selectCountProductByParentId(productId);
	}
}
