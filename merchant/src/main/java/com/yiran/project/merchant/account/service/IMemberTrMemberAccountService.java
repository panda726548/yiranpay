package com.yiran.project.merchant.account.service;


import java.util.List;

import com.yiran.project.merchant.account.domain.MemberTrMemberAccount;

/**
 * 会员账户 服务层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface IMemberTrMemberAccountService 
{
	/**
     * 查询会员账户信息
     * 
     * @param id 会员账户ID
     * @return 会员账户信息
     */
	public MemberTrMemberAccount selectMemberTrMemberAccountById(Integer id);
	
	/**
     * 查询会员账户列表
     * 
     * @param memberTrMemberAccount 会员账户信息
     * @return 会员账户集合
     */
	public List<MemberTrMemberAccount> selectMemberTrMemberAccountList(MemberTrMemberAccount memberTrMemberAccount);
	
	/**
     * 新增会员账户
     * 
     * @param memberTrMemberAccount 会员账户信息
     * @return 结果
     */
	public int insertMemberTrMemberAccount(MemberTrMemberAccount memberTrMemberAccount);
	
	/**
     * 修改会员账户
     * 
     * @param memberTrMemberAccount 会员账户信息
     * @return 结果
     */
	public int updateMemberTrMemberAccount(MemberTrMemberAccount memberTrMemberAccount);
		
	/**
     * 删除会员账户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTrMemberAccountByIds(String ids);

	
	public MemberTrMemberAccount selectMemberTrMemberAccountByMemberId(String memberId);


	
	
}
