package com.yiranpay.member.service;

import com.yiranpay.member.base.Response;
import com.yiranpay.member.domain.AccountDomain;
import com.yiranpay.member.domain.MemberTrMemberAccount;
import com.yiranpay.member.enums.AccountCategoryEnum;
import com.yiranpay.member.request.AccBalanceListRequest;
import com.yiranpay.member.request.AccRelationRequest;
import com.yiranpay.member.request.AccountRequest;
import com.yiranpay.member.request.OpenAccountRequest;
import com.yiranpay.member.request.QueryBasicAccountRequest;
import com.yiranpay.member.request.UpdateAccountFreezeStatusRequest;
import com.yiranpay.member.response.AccBalanceListResponse;
import com.yiranpay.member.response.AccRelationResponse;
import com.yiranpay.member.response.AccountInfoResponse;
import com.yiranpay.member.response.AccountResponse;
import com.yiranpay.member.response.OpenAccountResponse;

import java.util.List;

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

	/**
	 * 查询会员账户关系
	 * @param memberId
	 * @param type
	 * @param dpm
	 * @return
	 */
	public List<AccountDomain> getAccounts(String memberId, String type, AccountCategoryEnum dpm);

	/**
	 * 查询会员账户关系
	 * @param memberId
	 * @param accountTypes
	 * @return
	 */
	public List<AccountDomain> getAccounts(String memberId, List<Long> accountTypes);

	public String openAccount(AccountDomain baseAccount);

	public int updateAccountId(String accountId, String accountName, String memberId);

	public int insertMemberTrMemberAccount(AccountDomain baseAccount);

	public List<MemberTrMemberAccount> queryAllByMemberAndTypeId(String memberId, String valueOf, String code);

	/**
	 * 根据账户号查询会员账户关系
	 * @param request
	 * @return
	 */
	public AccRelationResponse queryAccountRelation(AccRelationRequest request);

	/**
	 * 根据账户id 查询会员账户信息
	 * @param accountId
	 * @return
	 */
	public AccountInfoResponse queryAccountById(String accountId);

	/**
	 * 根据会员号和账户类型查询账户信息
	 * @param request
	 * @return
	 */
	public AccountResponse queryAccountByMemberId(AccountRequest request);

	/**
	 * 设置账户冻结状态
	 * @param request
	 * @return
	 */
	public Response updateAccountFreezeStatus(UpdateAccountFreezeStatusRequest request);

	/**
	 * 开储值账户
	 * @param request
	 * @return
	 */
	public OpenAccountResponse openAccount(OpenAccountRequest request);

	/**
	 * 查询储值基本户
	 * @param request
	 * @return
	 */
	public AccountInfoResponse queryBasicAccountByMember(QueryBasicAccountRequest request);

	
	
}
