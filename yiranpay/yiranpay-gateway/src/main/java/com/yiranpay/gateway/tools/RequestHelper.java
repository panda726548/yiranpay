package com.yiranpay.gateway.tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.yiranpay.gateway.constant.ReqConstant;
import com.yiranpay.gateway.enums.ServiceKind;
import com.yiranpay.gateway.enums.SignTypeKind;
import com.yiranpay.gateway.exception.CommonDefinedException;
import com.yiranpay.gateway.exception.ErrorCodeException.CommonException;
import com.yiranpay.gateway.request.ALIPayRequest;
import com.yiranpay.gateway.request.ChinaH5PayQueryRequest;
import com.yiranpay.gateway.request.ChinaH5PayRequest;
import com.yiranpay.gateway.request.IRequest;
import com.yiranpay.gateway.request.QueryCardBinReq;
import com.yiranpay.gateway.request.RequestBase;
import com.yiranpay.gateway.request.UESRequest;
import com.yiranpay.gateway.request.WeiXinPayRequest;

/**
 *
 * <p>转化工具</p>
 */
public class RequestHelper {

    /**
     * 不可实例化
     */
    private RequestHelper() {
    }

    private static String      separ_d  = "\\$";
    private static String      separ_e  = "~";

    public static final String SEP      = "\\^";
    public static final String VERTICAL = "\\|";

    /**
     * 将request请求转为对象
     * @param paraMap
     * @return
     */
    public static IRequest convertFromMap(Map<String, String> paraMap) throws Exception {
        IRequest result = null;
        String serviceStr = paraMap.get(ReqConstant.SERVICE);
        ServiceKind service = ServiceKind.getByCode(serviceStr);
        if (service == null) {
            throw CommonDefinedException.ILLEGAL_SERVICE;
        }
        switch (service) {
            
            case query_card_bin:
            	result = convertCardBinFromMap(paraMap);
                break;
            case ues_encryption_decryption:
            	result = convertUESRequestFromMap(paraMap);
            	break;
            case china_wx_ali_pay:
            	result = convertH5PayRequestFromMap(paraMap);
            	break;
            case china_wx_ali_query_pay:
            	result = convertH5PayQueryRequestFromMap(paraMap);
            	break;
            case weixin_pay:
            	result = convertWeiXinPayRequestFromMap(paraMap);
            	break;
            case ali_pay:
            	result = convertALIPayRequestFromMap(paraMap);
            	break;
            default:
                throw CommonDefinedException.ILLEGAL_SERVICE;
        }

        return result;
    }
    


    private static ALIPayRequest convertALIPayRequestFromMap(Map<String, String> paraMap) {
    	ALIPayRequest req = new ALIPayRequest();
    	convertBase(req, paraMap);
    	
    	req.setRequestNo(paraMap.get(ReqConstant.REQUEST_NO));
    	req.setAmount(paraMap.get(ReqConstant.AMOUNT));
    	req.setProductName(paraMap.get(ReqConstant.PRODUCT_NAME));
    	req.setProductDesc(paraMap.get(ReqConstant.PRODUCT_DESC));
    	req.setProductCode(paraMap.get(ReqConstant.PRODUCT_CODE));
    	req.setOrderId(paraMap.get(ReqConstant.ORDERID));
    	req.setUserId(paraMap.get(ReqConstant.USER_ID));
    	req.setInstcode(paraMap.get(ReqConstant.INSTCODE));
    	req.setNotifyUrl(paraMap.get(ReqConstant.NOTIFY_URL));
    	req.setPaymentCode(paraMap.get(ReqConstant.PAYMENT_CODE));
    	req.setReturnUrl(paraMap.get(ReqConstant.RETURN_URL));
    	req.setPayType(paraMap.get(ReqConstant.PAY_TYPE));
    	req.setChannelName(paraMap.get(ReqConstant.CHANNEL_NAME));
		return req;
	}



	private static WeiXinPayRequest convertWeiXinPayRequestFromMap(Map<String, String> paraMap) {
    	WeiXinPayRequest req = new WeiXinPayRequest();
    	convertBase(req, paraMap);
    	
    	req.setRequestNo(paraMap.get(ReqConstant.REQUEST_NO));
    	req.setAmount(paraMap.get(ReqConstant.AMOUNT));
    	req.setProductName(paraMap.get(ReqConstant.PRODUCT_NAME));
    	req.setProductDesc(paraMap.get(ReqConstant.PRODUCT_DESC));
    	req.setProductCode(paraMap.get(ReqConstant.PRODUCT_CODE));
    	req.setOrderId(paraMap.get(ReqConstant.ORDERID));
    	req.setUserId(paraMap.get(ReqConstant.USER_ID));
    	req.setInstcode(paraMap.get(ReqConstant.INSTCODE));
    	req.setNotifyUrl(paraMap.get(ReqConstant.NOTIFY_URL));
    	req.setPaymentCode(paraMap.get(ReqConstant.PAYMENT_CODE));
    	req.setReturnUrl(paraMap.get(ReqConstant.RETURN_URL));
    	req.setChannelName(paraMap.get(ReqConstant.CHANNEL_NAME));
    	req.setOpenId(paraMap.get(ReqConstant.OPEN_ID));
    	req.setPayType(paraMap.get(ReqConstant.PAY_TYPE));
		return req;
	}



	private static ChinaH5PayQueryRequest convertH5PayQueryRequestFromMap(Map<String, String> paraMap) {
    	ChinaH5PayQueryRequest req = new ChinaH5PayQueryRequest();
    	convertBase(req, paraMap);
    	req.setRequestNo(paraMap.get(ReqConstant.REQUEST_NO));
		return req;
	}



	private static ChinaH5PayRequest convertH5PayRequestFromMap(Map<String, String> paraMap) {
    	
    	ChinaH5PayRequest h5req = new ChinaH5PayRequest();
    	convertBase(h5req, paraMap);
    	
    	h5req.setRequestNo(paraMap.get(ReqConstant.REQUEST_NO));
    	h5req.setAmount(paraMap.get(ReqConstant.AMOUNT));
    	h5req.setProductName(paraMap.get(ReqConstant.PRODUCT_NAME));
    	h5req.setProductDesc(paraMap.get(ReqConstant.PRODUCT_DESC));
    	h5req.setProductCode(paraMap.get(ReqConstant.PRODUCT_CODE));
    	h5req.setOrderId(paraMap.get(ReqConstant.ORDERID));
    	h5req.setUserId(paraMap.get(ReqConstant.USER_ID));
    	h5req.setInstcode(paraMap.get(ReqConstant.INSTCODE));
    	h5req.setNotifyUrl(paraMap.get(ReqConstant.NOTIFY_URL));
    	h5req.setPaymentCode(paraMap.get(ReqConstant.PAYMENT_CODE));
    	h5req.setReturnUrl(paraMap.get(ReqConstant.RETURN_URL));
		return h5req;
	}



	/**
     * 将request请求转为卡Bin对象
     * @param paraMap
     * @return
     */
    public static QueryCardBinReq convertCardBinFromMap(Map<String, String> paraMap) {
       
        QueryCardBinReq querycardbin=new QueryCardBinReq();

        convertBase(querycardbin, paraMap);
    
        querycardbin.setRequestNo(paraMap.get(ReqConstant.REQUEST_NO));
        querycardbin.setCardNo(paraMap.get(ReqConstant.CARD_NO));
        querycardbin.setBussType(paraMap.get(ReqConstant.BUSS_TYPE));
        
        return querycardbin;
    }

    /**
     * 将request请求转为UES请求对象
     * @param paraMap
     * @return
     */
    public static UESRequest convertUESRequestFromMap(Map<String, String> paraMap) {
       
    	UESRequest request=new UESRequest();

        convertBase(request, paraMap);
    
        request.setType(paraMap.get(ReqConstant.UES_TYPE));
        request.setEncryptType(paraMap.get(ReqConstant.UES_ENCRYPTTYPE));
        request.setOriginalText(paraMap.get(ReqConstant.UES_ORIGINALTEXT));
        request.setTicketNo(paraMap.get(ReqConstant.UES_TICKETNO));
        return request;
    }
    
    public static RequestBase convertBaseTemp(Map<String, String> paraMap) {
        RequestBase result = new RequestBase();
        convertBase(result, paraMap);
        return result;

    }

    private static void convertBase(RequestBase base, Map<String, String> paraMap) {

        base.setInputCharset(paraMap.get(ReqConstant.INPUT_CHARSET));
        base.setMemo(paraMap.get(ReqConstant.MEMO));
        base.setPartnerId(paraMap.get(ReqConstant.PARNTER_ID));
        base.setReturnUrl(paraMap.get(ReqConstant.RETURN_URL));
        base.setService(paraMap.get(ReqConstant.SERVICE));
        base.setVersion(paraMap.get(ReqConstant.VERSION));
        base.setSign(paraMap.get(ReqConstant.SIGN));
        String referer = paraMap.get(ReqConstant.REFERER);
        if(StringUtils.isNotBlank(referer) && referer.indexOf('?') != -1){
        	referer = referer.substring(0, referer.indexOf('?'));
        }
        base.setReturnUrl(referer);
        base.setUserAgent(paraMap.get(ReqConstant.USERAGENT));
        base.setSignType(SignTypeKind.getByCode(paraMap.get(ReqConstant.SIGN_TYPE)).getCode());
    }
    
   
}
