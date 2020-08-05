package com.yiran.project.system.user.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.common.utils.text.Convert;
import com.yiran.project.system.user.domain.MemberTmOperator;
import com.yiran.project.system.user.mapper.MemberTmOperatorMapper;


/**
 * 操作员 服务层实现
 * 
 * @author yiran
 * @date 2019-03-31
 */
@Service
public class MemberTmOperatorServiceImpl implements IMemberTmOperatorService 
{
	@Autowired
	private MemberTmOperatorMapper memberTmOperatorMapper;

	/**
     * 查询操作员信息
     * 
     * @param operatorId 操作员ID
     * @return 操作员信息
     */
    @Override
	public MemberTmOperator selectMemberTmOperatorById(String operatorId)
	{
	    return memberTmOperatorMapper.selectMemberTmOperatorById(operatorId);
	}
	
	/**
     * 查询操作员列表
     * 
     * @param memberTmOperator 操作员信息
     * @return 操作员集合
     */
	@Override
	public List<MemberTmOperator> selectMemberTmOperatorList(MemberTmOperator memberTmOperator)
	{
	    return memberTmOperatorMapper.selectMemberTmOperatorList(memberTmOperator);
	}
	
    /**
     * 新增操作员
     * 
     * @param memberTmOperator 操作员信息
     * @return 结果
     */
	@Override
	public int insertMemberTmOperator(MemberTmOperator memberTmOperator)
	{
	    return memberTmOperatorMapper.insertMemberTmOperator(memberTmOperator);
	}
	
	/**
     * 修改操作员
     * 
     * @param memberTmOperator 操作员信息
     * @return 结果
     */
	@Override
	public int updateMemberTmOperator(MemberTmOperator memberTmOperator)
	{
	    return memberTmOperatorMapper.updateMemberTmOperator(memberTmOperator);
	}

	/**
     * 删除操作员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTmOperatorByIds(String ids)
	{
		return memberTmOperatorMapper.deleteMemberTmOperatorByIds(Convert.toStrArray(ids));
	}


	@Override
	public MemberTmOperator selectMemberTmOperatorByMemberId(String memberId) {
		return memberTmOperatorMapper.selectMemberTmOperatorByMemberId(memberId);
	}

}
