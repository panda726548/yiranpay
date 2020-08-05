package com.yiran.project.system.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.common.utils.text.Convert;
import com.yiran.project.system.user.domain.MemberTmMemberIdentity;
import com.yiran.project.system.user.mapper.MemberTmMemberIdentityMapper;

/**
 * 会员标识 服务层实现
 * 
 * @author yiran
 * @date 2019-03-31
 */
@Service
public class MemberTmMemberIdentityServiceImpl implements IMemberTmMemberIdentityService 
{
	@Autowired
	private MemberTmMemberIdentityMapper memberTmMemberIdentityMapper;

	/**
     * 查询会员标识信息
     * 
     * @param memberId 会员标识ID
     * @return 会员标识信息
     */
    @Override
	public MemberTmMemberIdentity selectMemberTmMemberIdentityById(String memberId)
	{
	    return memberTmMemberIdentityMapper.selectMemberTmMemberIdentityById(memberId);
	}
	
	/**
     * 查询会员标识列表
     * 
     * @param memberTmMemberIdentity 会员标识信息
     * @return 会员标识集合
     */
	@Override
	public List<MemberTmMemberIdentity> selectMemberTmMemberIdentityList(MemberTmMemberIdentity memberTmMemberIdentity)
	{
	    return memberTmMemberIdentityMapper.selectMemberTmMemberIdentityList(memberTmMemberIdentity);
	}
	
    /**
     * 新增会员标识
     * 
     * @param memberTmMemberIdentity 会员标识信息
     * @return 结果
     */
	@Override
	public int insertMemberTmMemberIdentity(MemberTmMemberIdentity memberTmMemberIdentity)
	{
	    return memberTmMemberIdentityMapper.insertMemberTmMemberIdentity(memberTmMemberIdentity);
	}
	
	/**
     * 修改会员标识
     * 
     * @param memberTmMemberIdentity 会员标识信息
     * @return 结果
     */
	@Override
	public int updateMemberTmMemberIdentity(MemberTmMemberIdentity memberTmMemberIdentity)
	{
	    return memberTmMemberIdentityMapper.updateMemberTmMemberIdentity(memberTmMemberIdentity);
	}

	/**
     * 删除会员标识对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTmMemberIdentityByIds(String ids)
	{
		return memberTmMemberIdentityMapper.deleteMemberTmMemberIdentityByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<MemberTmMemberIdentity> selectMemberTmMemberIdentityByMemberId(String memberId) {
		return memberTmMemberIdentityMapper.selectMemberTmMemberIdentityByMemberId(memberId);
	}
	
}
