package com.yiran.project.system.user.domain;

import com.yiran.framework.web.domain.BaseEntity;
/**
 * 用户对象 sys_user
 * 
 * @author yiran
 */
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会员编号 */
	private String memberId;

    /** 登录名称 */
    private String loginName;

    /** 用户名称 */
    private String userName;

    private String password;
    
    private int status;
    
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [memberId=" + memberId + ", loginName=" + loginName + ", userName=" + userName + ", password="
				+ password + "]";
	}

    
}
