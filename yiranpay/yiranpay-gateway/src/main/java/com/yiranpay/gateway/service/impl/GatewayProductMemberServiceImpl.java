package com.yiranpay.gateway.service.impl;

import java.util.List;
import com.yiranpay.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiranpay.gateway.mapper.GatewayProductMemberMapper;
import com.yiranpay.gateway.domain.GatewayProductMember;
import com.yiranpay.gateway.service.IGatewayProductMemberService;
import com.yiranpay.common.core.text.Convert;

/**
 * 产品商户关联Service业务层处理
 * 
 * @author panda
 * @date 2020-04-12
 */
@Service
public class GatewayProductMemberServiceImpl implements IGatewayProductMemberService 
{
    @Autowired
    private GatewayProductMemberMapper gatewayProductMemberMapper;

    /**
     * 查询产品商户关联
     * 
     * @param id 产品商户关联ID
     * @return 产品商户关联
     */
    @Override
    public GatewayProductMember selectGatewayProductMemberById(Long id)
    {
        return gatewayProductMemberMapper.selectGatewayProductMemberById(id);
    }

    /**
     * 查询产品商户关联列表
     * 
     * @param gatewayProductMember 产品商户关联
     * @return 产品商户关联
     */
    @Override
    public List<GatewayProductMember> selectGatewayProductMemberList(GatewayProductMember gatewayProductMember)
    {
        return gatewayProductMemberMapper.selectGatewayProductMemberList(gatewayProductMember);
    }

    /**
     * 新增产品商户关联
     * 
     * @param gatewayProductMember 产品商户关联
     * @return 结果
     */
    @Override
    public int insertGatewayProductMember(GatewayProductMember gatewayProductMember)
    {
        gatewayProductMember.setCreateTime(DateUtils.getNowDate());
        return gatewayProductMemberMapper.insertGatewayProductMember(gatewayProductMember);
    }

    /**
     * 修改产品商户关联
     * 
     * @param gatewayProductMember 产品商户关联
     * @return 结果
     */
    @Override
    public int updateGatewayProductMember(GatewayProductMember gatewayProductMember)
    {
        gatewayProductMember.setUpdateTime(DateUtils.getNowDate());
        return gatewayProductMemberMapper.updateGatewayProductMember(gatewayProductMember);
    }

    /**
     * 删除产品商户关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGatewayProductMemberByIds(String ids)
    {
        return gatewayProductMemberMapper.deleteGatewayProductMemberByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品商户关联信息
     * 
     * @param id 产品商户关联ID
     * @return 结果
     */
    @Override
    public int deleteGatewayProductMemberById(Long id)
    {
        return gatewayProductMemberMapper.deleteGatewayProductMemberById(id);
    }
}
