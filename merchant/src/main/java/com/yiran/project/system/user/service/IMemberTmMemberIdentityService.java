package com.yiran.project.system.user.service;

import java.util.List;

import com.yiran.project.system.user.domain.MemberTmMemberIdentity;

/**
 * 会员标识 服务层
 * 
 * @author yiran
 * @date 2019-03-31
 */
public interface IMemberTmMemberIdentityService 
{
	/**
     * 查询会员标识信息
     * 
     * @param memberId 会员标识ID
     * @return 会员标识信息
     */
	public MemberTmMemberIdentity selectMemberTmMemberIdentityById(String memberId);
	
	/**
     * 查询会员标识列表
     * 
     * @param memberTmMemberIdentity 会员标识信息
     * @return 会员标识集合
     */
	public List<MemberTmMemberIdentity> selectMemberTmMemberIdentityList(MemberTmMemberIdentity memberTmMemberIdentity);
	
	/**
     * 新增会员标识
     * 
     * @param memberTmMemberIdentity 会员标识信息
     * @return 结果
     */
	public int insertMemberTmMemberIdentity(MemberTmMemberIdentity memberTmMemberIdentity);
	
	/**
     * 修改会员标识
     * 
     * @param memberTmMemberIdentity 会员标识信息
     * @return 结果
     */
	public int updateMemberTmMemberIdentity(MemberTmMemberIdentity memberTmMemberIdentity);
		
	/**
     * 删除会员标识信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTmMemberIdentityByIds(String ids);

	public List<MemberTmMemberIdentity> selectMemberTmMemberIdentityByMemberId(String memberId);
	
}
