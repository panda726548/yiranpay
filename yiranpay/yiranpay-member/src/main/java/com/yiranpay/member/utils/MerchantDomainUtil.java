/**
 * 
 */
package com.yiranpay.member.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yiranpay.member.domain.MemberTmMerchant;
import com.yiranpay.member.domain.MerchantInfo;
import com.yiranpay.member.enums.MerchantStatusEnum;
import com.yiranpay.member.enums.MerchantTypeEnum;
import com.yiranpay.member.request.MerchantAddRequest;


/**
 * <p>商户领域工具类</p>
 */
public class MerchantDomainUtil {

    
    /**
     * 商户领域对象转换成响应对象
     * @param merchant
     * @return
     */
    public static MerchantInfo convertToMerchantInfoResponse(MemberTmMerchant merchant) {
        MerchantInfo info = new MerchantInfo();
        info.setMemberId(merchant.getMemberId());
        info.setMerchantId(merchant.getMerchantId());
        info.setMerchantName(merchant.getMerchantName());
        info.setMerchantStatus(merchant.getStatus().longValue());
        info.setMerchantType(merchant.getMerchantType().longValue());
        return info;
    }

    /**
     * 商户领域对象转换成响应对象
     * @param merchant
     * @return
     */
    public static List<MerchantInfo> convertToMerchantInfosResponse(List<MemberTmMerchant> merchants) {
        if (CollectionUtils.isEmpty(merchants)) {
            return null;
        }
        List<MerchantInfo> list = new ArrayList<MerchantInfo>(merchants.size());
        for (MemberTmMerchant merchant : merchants) {
            list.add(convertToMerchantInfoResponse(merchant));
        }
        return list;
    }

    /**
     * 新增商户请求对象转换成商户域对象
     * @param request
     * @return
     */
    public static MemberTmMerchant convertReqToMerchant(MerchantAddRequest request) {
    	MemberTmMerchant merchant = new MemberTmMerchant();
        merchant.setMemberId(request.getMemberId());
        merchant.setMerchantId(request.getMerchantId());
        merchant.setMerchantName(request.getMerchantName());
        merchant.setMerchantType(request.getMerchantType().intValue());
        if (request.getMerchantStatus() != null) {
            merchant.setStatus(request.getMerchantStatus().intValue());
        } else {
            merchant.setStatus(MerchantStatusEnum.ACTIVE.getCode().intValue());
        }
        return merchant;
    }
}
