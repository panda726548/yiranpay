package com.yiranpay.member.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiranpay.member.mapper.MemberTmMerchantMapper;
import com.yiranpay.common.core.text.Convert;
import com.yiranpay.common.utils.StrUtils;
import com.yiranpay.member.constant.FieldLength;
import com.yiranpay.member.constant.MaConstant;
import com.yiranpay.member.domain.MemberTmMerchant;
import com.yiranpay.member.enums.ResponseCode;
import com.yiranpay.member.exception.MaBizException;
import com.yiranpay.member.service.IMemberSequenceService;
import com.yiranpay.member.service.IMemberTmMemberIdentityService;
import com.yiranpay.member.service.IMemberTmMerchantService;
import com.yiranpay.member.utils.MerchantDomainUtil;
import com.yiranpay.member.validator.MemberValidator;

/**
 * 商户 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTmMerchantServiceImpl implements IMemberTmMerchantService 
{
	@Autowired
	private MemberTmMerchantMapper memberTmMerchantMapper;
	@Autowired
	private MemberValidator    memberValidator;
	@Autowired
	private IMemberSequenceService memberSequenceService;
	@Autowired
	private IMemberTmMemberIdentityService memberTmMemberIdentityService;
	/**
     * 查询商户信息
     * 
     * @param merchantId 商户ID
     * @return 商户信息
     */
    @Override
	public MemberTmMerchant selectMemberTmMerchantById(String merchantId)
	{
	    return memberTmMerchantMapper.selectMemberTmMerchantById(merchantId);
	}
	
	/**
     * 查询商户列表
     * 
     * @param memberTmMerchant 商户信息
     * @return 商户集合
     */
	@Override
	public List<MemberTmMerchant> selectMemberTmMerchantList(MemberTmMerchant memberTmMerchant)
	{
	    return memberTmMerchantMapper.selectMemberTmMerchantList(memberTmMerchant);
	}
	
    /**
     * 新增商户
     * 
     * @param memberTmMerchant 商户信息
     * @return 结果
     */
	@Override
	public int insertMemberTmMerchant(MemberTmMerchant memberTmMerchant)
	{
	    return memberTmMerchantMapper.insertMemberTmMerchant(memberTmMerchant);
	}
	
	/**
     * 修改商户
     * 
     * @param memberTmMerchant 商户信息
     * @return 结果
     */
	@Override
	public int updateMemberTmMerchant(MemberTmMerchant memberTmMerchant)
	{
	    return memberTmMerchantMapper.updateMemberTmMerchant(memberTmMerchant);
	}

	/**
     * 删除商户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTmMerchantByIds(String ids)
	{
		return memberTmMerchantMapper.deleteMemberTmMerchantByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<MemberTmMerchant> queryByMemberId(String memberId) throws MaBizException{
		memberValidator.validateMemberExist(memberId);
		MemberTmMerchant memberTmMerchant = new MemberTmMerchant();
		memberTmMerchant.setMemberId(memberId);
        List<MemberTmMerchant> rest = memberTmMerchantMapper.selectMemberTmMerchantList(memberTmMerchant);
        if (!(rest == null || rest.isEmpty())) {
            return rest;
        }
        return null;
	}

	@Override
	public String createMerchant(MemberTmMerchant merchant) {
		 try {
			memberValidator.validateMemberExistAndNotCancelled(merchant.getMemberId());
			if (StringUtils.isNotEmpty(merchant.getMerchantId())) {
				MemberTmMerchant merchantDo = memberTmMerchantMapper.selectMemberTmMerchantById(merchant.getMerchantId());
				if (merchantDo != null) {
					throw new MaBizException(ResponseCode.DUPLICATE_MERCHANT_ID,
							"重复商户编号：" + merchant.getMerchantId());
				}
			}
			// 创建商户
			if (StringUtils.isEmpty(merchant.getMerchantId())) {
				merchant.setMerchantId(this.genMerchantId());
			}
			//TODO:
			//String merchantId = memberTmMerchantMapper.insert(MerchantDomainUtil.convertMerchantDO(merchant));
			// 创建为商户id 创建identity
			//memberTmMemberIdentityService.insertMemberTmMemberIdentity(MerchantDomainUtil.convertToMemberIdentityDO(merchant));

			return null;
		} catch (MaBizException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String genMerchantId() {
		String prefix = MaConstant.PRE_MERCHANT_ID;
		int seqLen = FieldLength.MERCHANT_ID - prefix.length();
		String merchantId = prefix
				+ StrUtils.alignRight(
						String.valueOf(memberSequenceService.getMenberSequenceNo("SEQ_MERCHANT_ID")),
						seqLen, MaConstant.ID_FIX_CHAR);
		return merchantId;
	}
}
