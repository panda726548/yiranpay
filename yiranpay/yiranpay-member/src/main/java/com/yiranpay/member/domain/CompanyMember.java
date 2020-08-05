package com.yiranpay.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CompanyMember {

	/**
	 * 会员类型（1：个人会员，2：企业会员，3：特约商户）
	 */
	private String memberType;
	/**
	 * 登录名， 非空
	 */
	private String loginName;
	/**
	 * 登录名类型: 1(邮箱), 2(手机号), 3(普通文字)
	 */
	private String loginNameType;
	/**
	 * 登录名平台类型
	 */
	private String loginNamePlatformType;
	/**
	 * 登录密码
	 */
	private String loginPassword;
	/**
	 * 外部平台用户标识
	 */
	private String platformUserId;
	/**
	 * 平台类型
	 */
	private String  platformType;
	/**
	 * 会员名称
	 */
	private String memberName;
	/**
	 * 注册来源
	 */
	private String registerSource;
	/**
	 * 会员状态与账户标志（0默认：会员未激活，且不开储值基本户；1：会员激活，并开未激活储值基本户；2：会员账户都激活）
	 */
	private String memberAccountFlag;
	/**
	 * 商户营业执照
	 */
	private String licenseNo;
	/**
	 * 企业营业执照失效时间(营业期限)
	 */
	private String licenseExpireDate;
	/**
	 * 企业编号
	 */
	private String companyNo;
	/**
	 * 法人代表
	 */
	private String legalPerson;
	/**
	 * 企业规模
	 */
	private String scale;
	/**
	 * 企业网址
	 */
	private String website;
	/**
	 * 企业名称
	 */
	private String companyName;
	/**
	 * 企业地址
	 */
	private String address;
	/**
	 * 营业执照所在地
	 */
	private String licenseAddress;
	/**
	 * 营业范围
	 */
	private String businessScope;
	/**
	 * 联系电话
	 */
	private String telephone;
	/**
	 * 组织机构代码
	 */
	private String organizationNo;
	/**
	 * 企业简介
	 */
	private String summary;
	/**
	 * 企业营业执照附件url
	 */
	private String licenseNoPath;
	/**
	 * 组织机构代码证附件url
	 */
	private String organizationNoPath;
	/**
	 * 税务登记证附件url
	 */
	private String taxNoPath;
	/**
	 * 单位银行结算账户开户许可证附件url
	 */
	private String clearingAccountPath;
	/**
	 * ICP备案许可附件url
	 */
	private String icpLicensePath;
	/**
	 * 法人手机号码
	 */
	private String legalPersonPhone;
	/**
	 * 登记证号名称
	 */
	private String licenseName;
	/**
	 * 法人身份证有效期
	 */
	private String legalPersonIdValidDate;
	/**
	 * 经营网址 
	 */
	private String businessWebsite;
	/**
	 * 企业类型(0企业 1其他 2事业单位 3个体工商户 4民办非企业)
	 */
	private String companyType;
	/**
	 * 商户简称
	 */
	private String shortName;
	/**
	 * 会员状态
	 */
	private String status;
	/**
	 * 会员锁定状态 
	 */
	private String lockStatus;
	
	/**********************************************************************/
	/**
	 * 账户类型
	 */
	private String accountType;
	/**
	 * 激活状态 0:未激活    1:已激活
	 */
	private String activateStatus;
	/**
	 * 冻结状态    0:未冻结    1:止出    2:止入    3:双向冻结(锁定)
	 */
	private String freezeStatus;
	/**
	 * 销户状态 0:未销户    1:已销户    2:已结转长期不动户
	 */
	private String lifeCycleStatus;
	/**
	 * 币种
	 */
	private String currencyCode;
	/**
	 * 账户属性
	 */
	private String accountAttribute;
	/**
	 * 账户分类
	 */
	private String cateEnum;
	
	/*******************************************/
	/**
	 * 商户名,已注销的会被添加后缀
	 */
	private String merchantName;
	/**
	 * 商户类型
	 */
	private String merchantType;
	/**
	 * 状态(0未激活 1正常  3注销)
	 */
	private String merchatStatus;
	
	private String extention;
	
	
	public String getExtention() {
		return extention;
	}
	public void setExtention(String extention) {
		this.extention = extention;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginNameType() {
		return loginNameType;
	}
	public void setLoginNameType(String loginNameType) {
		this.loginNameType = loginNameType;
	}
	public String getLoginNamePlatformType() {
		return loginNamePlatformType;
	}
	public void setLoginNamePlatformType(String loginNamePlatformType) {
		this.loginNamePlatformType = loginNamePlatformType;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getPlatformUserId() {
		return platformUserId;
	}
	public void setPlatformUserId(String platformUserId) {
		this.platformUserId = platformUserId;
	}
	public String getPlatformType() {
		return platformType;
	}
	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getRegisterSource() {
		return registerSource;
	}
	public void setRegisterSource(String registerSource) {
		this.registerSource = registerSource;
	}
	public String getMemberAccountFlag() {
		return memberAccountFlag;
	}
	public void setMemberAccountFlag(String memberAccountFlag) {
		this.memberAccountFlag = memberAccountFlag;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getLicenseExpireDate() {
		return licenseExpireDate;
	}
	public void setLicenseExpireDate(String licenseExpireDate) {
		this.licenseExpireDate = licenseExpireDate;
	}
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLicenseAddress() {
		return licenseAddress;
	}
	public void setLicenseAddress(String licenseAddress) {
		this.licenseAddress = licenseAddress;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getOrganizationNo() {
		return organizationNo;
	}
	public void setOrganizationNo(String organizationNo) {
		this.organizationNo = organizationNo;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getLicenseNoPath() {
		return licenseNoPath;
	}
	public void setLicenseNoPath(String licenseNoPath) {
		this.licenseNoPath = licenseNoPath;
	}
	public String getOrganizationNoPath() {
		return organizationNoPath;
	}
	public void setOrganizationNoPath(String organizationNoPath) {
		this.organizationNoPath = organizationNoPath;
	}
	public String getTaxNoPath() {
		return taxNoPath;
	}
	public void setTaxNoPath(String taxNoPath) {
		this.taxNoPath = taxNoPath;
	}
	public String getClearingAccountPath() {
		return clearingAccountPath;
	}
	public void setClearingAccountPath(String clearingAccountPath) {
		this.clearingAccountPath = clearingAccountPath;
	}
	public String getIcpLicensePath() {
		return icpLicensePath;
	}
	public void setIcpLicensePath(String icpLicensePath) {
		this.icpLicensePath = icpLicensePath;
	}
	public String getLegalPersonPhone() {
		return legalPersonPhone;
	}
	public void setLegalPersonPhone(String legalPersonPhone) {
		this.legalPersonPhone = legalPersonPhone;
	}
	public String getLicenseName() {
		return licenseName;
	}
	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}
	public String getLegalPersonIdValidDate() {
		return legalPersonIdValidDate;
	}
	public void setLegalPersonIdValidDate(String legalPersonIdValidDate) {
		this.legalPersonIdValidDate = legalPersonIdValidDate;
	}
	public String getBusinessWebsite() {
		return businessWebsite;
	}
	public void setBusinessWebsite(String businessWebsite) {
		this.businessWebsite = businessWebsite;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getActivateStatus() {
		return activateStatus;
	}
	public void setActivateStatus(String activateStatus) {
		this.activateStatus = activateStatus;
	}
	public String getFreezeStatus() {
		return freezeStatus;
	}
	public void setFreezeStatus(String freezeStatus) {
		this.freezeStatus = freezeStatus;
	}
	public String getLifeCycleStatus() {
		return lifeCycleStatus;
	}
	public void setLifeCycleStatus(String lifeCycleStatus) {
		this.lifeCycleStatus = lifeCycleStatus;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getAccountAttribute() {
		return accountAttribute;
	}
	public void setAccountAttribute(String accountAttribute) {
		this.accountAttribute = accountAttribute;
	}
	public String getCateEnum() {
		return cateEnum;
	}
	public void setCateEnum(String cateEnum) {
		this.cateEnum = cateEnum;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getMerchantType() {
		return merchantType;
	}
	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
	
	
	public String getMerchatStatus() {
		return merchatStatus;
	}
	public void setMerchatStatus(String merchatStatus) {
		this.merchatStatus = merchatStatus;
	}
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
	
}
