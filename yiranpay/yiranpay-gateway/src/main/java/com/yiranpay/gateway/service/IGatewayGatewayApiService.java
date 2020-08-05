package com.yiranpay.gateway.service;

import com.yiranpay.common.core.domain.Ztree;
import com.yiranpay.gateway.domain.GatewayGatewayApi;
import com.yiranpay.gateway.domain.GatewayMerchantConfig;

import java.util.List;

/**
 * 接口权限Service接口
 * 
 * @author panda
 * @date 2020-01-06
 */
public interface IGatewayGatewayApiService 
{
    /**
     * 查询接口权限
     * 
     * @param apiId 接口权限ID
     * @return 接口权限
     */
    public GatewayGatewayApi selectGatewayGatewayApiById(Long apiId);

    /**
     * 查询接口权限列表
     * 
     * @param gatewayGatewayApi 接口权限
     * @return 接口权限集合
     */
    public List<GatewayGatewayApi> selectGatewayGatewayApiList(GatewayGatewayApi gatewayGatewayApi);

    /**
     * 新增接口权限
     * 
     * @param gatewayGatewayApi 接口权限
     * @return 结果
     */
    public int insertGatewayGatewayApi(GatewayGatewayApi gatewayGatewayApi);

    /**
     * 修改接口权限
     * 
     * @param gatewayGatewayApi 接口权限
     * @return 结果
     */
    public int updateGatewayGatewayApi(GatewayGatewayApi gatewayGatewayApi);

    /**
     * 批量删除接口权限
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGatewayGatewayApiByIds(String ids);

    /**
     * 删除接口权限信息
     * 
     * @param apiId 接口权限ID
     * @return 结果
     */
    public int deleteGatewayGatewayApiById(Long apiId);

    /**
     * 查询接口集合
     * 
     * @return 所有接口信息
     */
	public List<GatewayGatewayApi> selectMenuList(GatewayGatewayApi gatewayGatewayApi);

	/**
	 * 查询菜单数量
	 * @param parentId 菜单父ID
	 * @return
	 */
	public int selectCountApiByParentId(Long parentId);

	/**
	 * 删除菜单管理信息
	 * @param parentId 菜单父ID
	 * @return
	 */
	public int deleteApiById(Long parentId);

	/**
	 * 校验接口名称是否唯一
	 * @param gatewayGatewayApi
	 * @return
	 */
	public String checkApiNameUnique(GatewayGatewayApi gatewayGatewayApi);

	/**
	 * 
	 * @param apiId
	 * @return
	 */
	public GatewayGatewayApi selectApiMenuById(Long apiId);

	/**
	 * 所有的api接口权限
	 * @return
	 */
	public List<Ztree> apiMenuTreeData();

	/**
	 * 根据商户号查询接口权限
	 * @param config
	 * @return
	 */
	public List<Ztree> apiMenuAuthTreeData(GatewayMerchantConfig config);
}
