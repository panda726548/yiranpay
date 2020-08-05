package com.yiran.project.merchant.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.yiran.common.utils.text.Convert;
import com.yiran.project.merchant.account.domain.MemberTrMemberAccount;
import com.yiran.project.merchant.account.mapper.MemberTrMemberAccountMapper;

/**
 * 会员账户 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTrMemberAccountServiceImpl implements IMemberTrMemberAccountService 
{
	private Logger        logger = LoggerFactory.getLogger(MemberTrMemberAccountServiceImpl.class);
	@Autowired
	private MemberTrMemberAccountMapper memberTrMemberAccountMapper;
	
	/**
     * 查询会员账户信息
     * 
     * @param id 会员账户ID
     * @return 会员账户信息
     */
    @Override
	public MemberTrMemberAccount selectMemberTrMemberAccountById(Integer id)
	{
	    return memberTrMemberAccountMapper.selectMemberTrMemberAccountById(id);
	}
	
	/**
     * 查询会员账户列表
     * 
     * @param memberTrMemberAccount 会员账户信息
     * @return 会员账户集合
     */
	@Override
	public List<MemberTrMemberAccount> selectMemberTrMemberAccountList(MemberTrMemberAccount memberTrMemberAccount)
	{
	    return memberTrMemberAccountMapper.selectMemberTrMemberAccountList(memberTrMemberAccount);
	}
	
    /**
     * 新增会员账户
     * 
     * @param memberTrMemberAccount 会员账户信息
     * @return 结果
     */
	@Override
	public int insertMemberTrMemberAccount(MemberTrMemberAccount memberTrMemberAccount)
	{
	    return memberTrMemberAccountMapper.insertMemberTrMemberAccount(memberTrMemberAccount);
	}
	
	/**
     * 修改会员账户
     * 
     * @param memberTrMemberAccount 会员账户信息
     * @return 结果
     */
	@Override
	public int updateMemberTrMemberAccount(MemberTrMemberAccount memberTrMemberAccount)
	{
	    return memberTrMemberAccountMapper.updateMemberTrMemberAccount(memberTrMemberAccount);
	}

	/**
     * 删除会员账户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTrMemberAccountByIds(String ids)
	{
		return memberTrMemberAccountMapper.deleteMemberTrMemberAccountByIds(Convert.toStrArray(ids));
	}

	@Override
	public MemberTrMemberAccount selectMemberTrMemberAccountByMemberId(String memberId) {
		return memberTrMemberAccountMapper.selectMemberTrMemberAccountByMemberId(memberId);
	}

	
	
	
}
