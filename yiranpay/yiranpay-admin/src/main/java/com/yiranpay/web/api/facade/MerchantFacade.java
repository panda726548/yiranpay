package com.yiranpay.web.api.facade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.member.base.Response;
import com.yiranpay.member.domain.MemberTmMember;
import com.yiranpay.member.domain.MemberTmMerchant;
import com.yiranpay.member.domain.MemberTrCompanyMember;
import com.yiranpay.member.enums.ResponseCode;
import com.yiranpay.member.request.ActivatePersonalRequest;
import com.yiranpay.member.request.CompanyMemberQueryRequest;
import com.yiranpay.member.request.CreateMemberInfoRequest;
import com.yiranpay.member.request.IntegratedCompanyRequest;
import com.yiranpay.member.request.IntegratedPersonalRequest;
import com.yiranpay.member.request.MemberIntegratedIdRequest;
import com.yiranpay.member.request.MemberIntegratedRequest;
import com.yiranpay.member.request.MerchantAddRequest;
import com.yiranpay.member.request.MerchantIdQueryRequest;
import com.yiranpay.member.request.MerchantQueryRequest;
import com.yiranpay.member.request.PersonalMemberInfoRequest;
import com.yiranpay.member.request.PersonalMemberQueryRequest;
import com.yiranpay.member.request.UpdateMemberLockStatusRequest;
import com.yiranpay.member.response.ActivatePersonalResponse;
import com.yiranpay.member.response.CreateMemberInfoResponse;
import com.yiranpay.member.response.IntegratedCompanyResponse;
import com.yiranpay.member.response.IntegratedPersonalResponse;
import com.yiranpay.member.response.MemberIntegratedResponse;
import com.yiranpay.member.response.MerchantAddResponse;
import com.yiranpay.member.response.MerchantListResponse;
import com.yiranpay.member.response.MerchantResponse;
import com.yiranpay.member.service.IMemberTmMemberService;
import com.yiranpay.member.service.IMemberTmMerchantService;
import com.yiranpay.member.service.IMemberTrCompanyMemberService;
import com.yiranpay.member.utils.MerchantDomainUtil;
import com.yiranpay.member.utils.ResponseUtil;
import com.yiranpay.member.validator.MerchantFacadeValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商户管理
 * @author pandaa
 *
 */
@RestController
@RequestMapping("/api/yiran/merchant")
@Api(value="商户管理接口",description="商户管理接口")
public class MerchantFacade extends BaseController{
	private Logger        logger = LoggerFactory.getLogger(MerchantFacade.class);
	
	private IMemberTmMerchantService merchantService;
	   

	@PostMapping("/queryMerchantInfo")
    @ApiOperation(value = "查询商户信息",notes="查询商户信息")
    public MerchantResponse queryMerchantInfo(MerchantIdQueryRequest request) {
        if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]查询商户请求:request={}", request);
        }
        MerchantResponse response = new MerchantResponse();
        try {
            //验证请求参数
            MerchantFacadeValidator.validator(request);
            MemberTmMerchant merchant = merchantService.selectMemberTmMerchantById(request.getMerchantId());
            if (merchant == null) {
                response.setResponseCode(ResponseCode.MERCHANT_NOT_EXIST.getCode());
                response.setResponseMessage(ResponseCode.MERCHANT_NOT_EXIST.getMessage());
            } else {
                response
                    .setMerchantInfo(MerchantDomainUtil.convertToMerchantInfoResponse(merchant));
                ResponseUtil.setSuccessResponse(response);
            }
            
            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]查询商户结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "查询商户");
        }
        return response;
    }
	
	@PostMapping("/queryMerchantInfoList")
    @ApiOperation(value = "查询商户集合信息",notes="查询商户集合信息")
    public MerchantListResponse queryMerchantInfoList(MerchantQueryRequest request) {
        if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]查询商户请求:request={}", request);
        }
        MerchantListResponse response = new MerchantListResponse();
        try {
            //验证请求参数
            MerchantFacadeValidator.validator(request);

            List<MemberTmMerchant> merchant = merchantService.queryByMemberId(request.getMemberId());
            if (CollectionUtils.isEmpty(merchant)) {
                response.setResponseCode(ResponseCode.MERCHANT_NOT_EXIST.getCode());
                response.setResponseMessage(ResponseCode.MERCHANT_NOT_EXIST.getMessage());
            } else {
                response.setMerchantInfos(MerchantDomainUtil.convertToMerchantInfosResponse(merchant));
                ResponseUtil.setSuccessResponse(response);
            }
            
            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]查询商户结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "查询商户");
        }
        return response;
    }

    
	@PostMapping("/createMerchant")
    @ApiOperation(value = "添加商户信息",notes="添加商户信息")
    public MerchantAddResponse createMerchant(MerchantAddRequest request) {
        if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]创建商户请求:request={}", request);
        }
        MerchantAddResponse response = new MerchantAddResponse();
        try {
            //验证请求参数
            MerchantFacadeValidator.validator(request);
            MemberTmMerchant merchant = MerchantDomainUtil.convertReqToMerchant(request);
            String merchantId = merchantService.createMerchant(merchant);
            response.setMerchantId(merchantId);
            response.setCreateTime(new Date());
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]创建商户结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response,e, "创建商户");
        }
        return response;

    }

    
     /* 
    @Override
    public Response updateMerchant(OperationEnvironment environment, MerchantUpdateRequest request) {
        if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]修改商户请求:request={},environment={}", request,
                Utils.toString(environment));
        }
        Response response = new Response();
        try {
            //验证请求参数
            MerchantFacadeValidator.validator(request);
            Merchant merchant = MerchantDomainUtil.convertReqToMerchant(request);
            merchantService.updateMerchant(merchant);
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]修改商户结果:response={},environment={}", response,
                    Utils.toString(environment));
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, environment, "修改商户");
        }
        return response;
    }

    @Override
	public MerchantListResponse queryVerfiedMerchants(OperationEnvironment environment,
			MerchantQueryRequest request) {
    	if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]查询商户请求:request={},environment={}", request,
                Utils.toString(environment));
        }
        MerchantListResponse response = new MerchantListResponse();
        try {
            List<Merchant> merchant = merchantService.queryVerfiedMerchants();
            if (CollectionUtils.isEmpty(merchant)) {
                response.setResponseCode(ResponseCode.MERCHANT_NOT_EXIST.getCode());
                response.setResponseMessage(ResponseCode.MERCHANT_NOT_EXIST.getMessage());
            } else {
                response.setMerchantInfos(MerchantDomainUtil
                    .convertToMerchantInfosResponse(merchant));
                ResponseUtil.setSuccessResponse(response);
            }
            
            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]查询商户结果:response={},environment={}", response,
                    Utils.toString(environment));
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, environment, "查询商户");
        }
        return response;
	}*/
    
}
