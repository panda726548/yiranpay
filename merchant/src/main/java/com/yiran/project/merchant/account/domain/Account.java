package com.yiran.project.merchant.account.domain;

public class Account {
	
	/** 主键 */
	private Integer id;
	/** 会员编号 */
	private String memberId;
	
	/** 账户编号 */
	private String accountId;
	
	/** 账户别名 */
	private String alias;
	
	/** 账户余额 */
	private String balance;
	/** 账户可用余额 */
	private String availableBalance;
	/** 账户冻结余额 */
	private String frozenBalance;
	/** 可提现金额，信用账户现金额度 */
	private String withdrawBalance;
	/** 币种 */
	private String currencyCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}
	public String getFrozenBalance() {
		return frozenBalance;
	}
	public void setFrozenBalance(String frozenBalance) {
		this.frozenBalance = frozenBalance;
	}
	public String getWithdrawBalance() {
		return withdrawBalance;
	}
	public void setWithdrawBalance(String withdrawBalance) {
		this.withdrawBalance = withdrawBalance;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", memberId=" + memberId + ", accountId=" + accountId + ", alias=" + alias
				+ ", balance=" + balance + ", availableBalance=" + availableBalance + ", frozenBalance=" + frozenBalance
				+ ", withdrawBalance=" + withdrawBalance + ", currencyCode=" + currencyCode + "]";
	}
	

}
