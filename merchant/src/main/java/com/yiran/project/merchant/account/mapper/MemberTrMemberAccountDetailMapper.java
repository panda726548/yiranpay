package com.yiran.project.merchant.account.mapper;

import java.util.List;

import com.yiran.project.merchant.account.domain.MemberTrMemberAccountDetail;


/**
 * 会员账户明细 数据层
 * 
 * @author yiran
 * @date 2019-04-04
 */
public interface MemberTrMemberAccountDetailMapper 
{
	/**
     * 查询会员账户明细信息
     * 
     * @param id 会员账户明细ID
     * @return 会员账户明细信息
     */
	public MemberTrMemberAccountDetail selectMemberTrMemberAccountDetailById(Integer id);
	
	/**
     * 查询会员账户明细列表
     * 
     * @param memberTrMemberAccountDetail 会员账户明细信息
     * @return 会员账户明细集合
     */
	public List<MemberTrMemberAccountDetail> selectMemberTrMemberAccountDetailList(MemberTrMemberAccountDetail memberTrMemberAccountDetail);
	
	/**
     * 新增会员账户明细
     * 
     * @param memberTrMemberAccountDetail 会员账户明细信息
     * @return 结果
     */
	public int insertMemberTrMemberAccountDetail(MemberTrMemberAccountDetail memberTrMemberAccountDetail);
	
	/**
     * 修改会员账户明细
     * 
     * @param memberTrMemberAccountDetail 会员账户明细信息
     * @return 结果
     */
	public int updateMemberTrMemberAccountDetail(MemberTrMemberAccountDetail memberTrMemberAccountDetail);
	
	/**
     * 删除会员账户明细
     * 
     * @param id 会员账户明细ID
     * @return 结果
     */
	public int deleteMemberTrMemberAccountDetailById(Integer id);
	
	/**
     * 批量删除会员账户明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTrMemberAccountDetailByIds(String[] ids);

	public MemberTrMemberAccountDetail selectMemberTrMemberAccountDetailByMemberAccountId(Integer memberAccId);

	
}