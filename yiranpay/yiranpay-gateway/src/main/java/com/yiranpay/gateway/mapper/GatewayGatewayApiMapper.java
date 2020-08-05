package com.yiranpay.gateway.mapper;

import com.yiranpay.gateway.domain.GatewayGatewayApi;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 接口权限Mapper接口
 * 
 * @author panda
 * @date 2020-01-06
 */
public interface GatewayGatewayApiMapper 
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
     * 删除接口权限
     * 
     * @param apiId 接口权限ID
     * @return 结果
     */
    public int deleteGatewayGatewayApiById(Long apiId);

    /**
     * 批量删除接口权限
     * 
     * @param apiIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteGatewayGatewayApiByIds(String[] apiIds);

    /**
     * 
     * @param parentId
     * @return
     */
	public int selectCountApiByParentId(Long parentId);

	/**
     * 校验API接口名称是否唯一
     * 
     * @param apiName 接口名称
     * @param parentId 父菜单ID
     * @return 结果
     */
	public GatewayGatewayApi checkAPINameUnique(@Param("apiName")String apiName, @Param("parentId")Long parentId);

	/**
	 * 获取所有的api菜单
	 * @return
	 */
	public List<GatewayGatewayApi> selectApiMenuAll();

	/**
	 * 根据商户ID
	 * @param merchantId
	 * @return
	 */
	public List<String> selectApiAuthTree(String merchantId);

}
