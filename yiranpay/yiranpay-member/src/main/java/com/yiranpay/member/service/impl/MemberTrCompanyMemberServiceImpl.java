package com.yiranpay.member.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiranpay.member.mapper.MemberTrCompanyMemberMapper;
import com.yiranpay.common.core.text.Convert;
import com.yiranpay.member.domain.MemberTrCompanyMember;
import com.yiranpay.member.service.IMemberTrCompanyMemberService;

/**
 * 企业会员 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTrCompanyMemberServiceImpl implements IMemberTrCompanyMemberService 
{
	@Autowired
	private MemberTrCompanyMemberMapper memberTrCompanyMemberMapper;

	/**
     * 查询企业会员信息
     * 
     * @param memberId 企业会员ID
     * @return 企业会员信息
     */
    @Override
	public MemberTrCompanyMember selectMemberTrCompanyMemberById(String memberId)
	{
	    return memberTrCompanyMemberMapper.selectMemberTrCompanyMemberById(memberId);
	}
	
	/**
     * 查询企业会员列表
     * 
     * @param memberTrCompanyMember 企业会员信息
     * @return 企业会员集合
     */
	@Override
	public List<MemberTrCompanyMember> selectMemberTrCompanyMemberList(MemberTrCompanyMember memberTrCompanyMember)
	{
	    return memberTrCompanyMemberMapper.selectMemberTrCompanyMemberList(memberTrCompanyMember);
	}
	
    /**
     * 新增企业会员
     * 
     * @param memberTrCompanyMember 企业会员信息
     * @return 结果
     */
	@Override
	public int insertMemberTrCompanyMember(MemberTrCompanyMember memberTrCompanyMember)
	{
	    return memberTrCompanyMemberMapper.insertMemberTrCompanyMember(memberTrCompanyMember);
	}
	
	/**
     * 修改企业会员
     * 
     * @param memberTrCompanyMember 企业会员信息
     * @return 结果
     */
	@Override
	public int updateMemberTrCompanyMember(MemberTrCompanyMember memberTrCompanyMember)
	{
	    return memberTrCompanyMemberMapper.updateMemberTrCompanyMember(memberTrCompanyMember);
	}

	/**
     * 删除企业会员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTrCompanyMemberByIds(String ids)
	{
		return memberTrCompanyMemberMapper.deleteMemberTrCompanyMemberByIds(Convert.toStrArray(ids));
	}
	
}
