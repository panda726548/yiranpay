package com.channel.bank.adapter.pay.service.impl;


import javax.servlet.http.HttpServletRequest;

import com.channel.bank.adapter.pay.config.SignType;
import com.channel.bank.adapter.pay.config.WxPayH5Config;
import com.channel.bank.adapter.pay.enums.BestPayResultEnum;
import com.channel.bank.adapter.pay.enums.BestPayTypeEnum;
import com.channel.bank.adapter.pay.exception.BestPayException;
import com.channel.bank.adapter.pay.mode.OrderQueryRequest;
import com.channel.bank.adapter.pay.mode.OrderQueryResponse;
import com.channel.bank.adapter.pay.mode.OrderRefundQueryRequest;
import com.channel.bank.adapter.pay.mode.OrderRefundQueryResponse;
import com.channel.bank.adapter.pay.mode.PayFundRequest;
import com.channel.bank.adapter.pay.mode.PayFundResponse;
import com.channel.bank.adapter.pay.mode.RePayRequest;
import com.channel.bank.adapter.pay.mode.RePayResponse;
import com.channel.bank.adapter.pay.service.BestPayService;

import java.util.Map;

public class BestPayServiceImpl extends AbstractComponent implements BestPayService {

    private WxPayH5Config wxPayH5Config;
    

    
    public void setWxPayH5Config(WxPayH5Config wxPayH5Config) {
        this.wxPayH5Config = wxPayH5Config;
    }

    @Override
    public PayFundResponse pay(PayFundRequest request) {
    	 //微信h5支付
        WxPayServiceImpl wxPayService = new WxPayServiceImpl();
        switch (request.getPayTypeEnum()) {
		case WXPAY_H5:
			wxPayService.setWxPayH5Config(this.wxPayH5Config);
			return wxPayService.pay(request);
		case WXPAY_MWEB:
			wxPayService.setWxPayH5Config(this.wxPayH5Config);
			return wxPayService.pay(request);
		case WXPAY_NATIVE:
			wxPayService.setWxPayH5Config(this.wxPayH5Config);
			return wxPayService.pay(request);
		}
        
		return null;
    }

    /**
     * 同步返回
     *
     * @param request
     * @return
     */
    public PayFundResponse syncNotify(HttpServletRequest request) {
        return null;
    }

    @Override
    public boolean verify(Map<String, String> toBeVerifiedParamMap, SignType signType, String sign) {
        return false;
    }

    /**
     * 异步回调
     *
     * @return
     */
    public PayFundResponse asyncNotify(String notifyData) {

        //微信h5支付
        WxPayServiceImpl wxPayService = new WxPayServiceImpl();
        wxPayService.setWxPayH5Config(this.wxPayH5Config);

        return wxPayService.asyncNotify(notifyData);
    }

    /**
     * 判断是什么支付类型(从同步回调中获取参数)
     *
     * @param request
     * @return
     */
    private BestPayTypeEnum payType(HttpServletRequest request) {
        //先判断是微信还是支付宝 是否是xml
        //支付宝同步还是异步
        if (request.getParameter("notify_type") == null) {
            
        } else {
            //支付宝异步(发起支付时使用这个参数标识支付方式)
            String payType = request.getParameter("passback_params");
            return BestPayTypeEnum.getByCode(payType);
        }

        throw new BestPayException(BestPayResultEnum.PAY_TYPE_ERROR);
    }

    @Override
    public RePayResponse refund(RePayRequest request) {
        //微信h5支付
        WxPayServiceImpl wxPayService = new WxPayServiceImpl();
        wxPayService.setWxPayH5Config(this.wxPayH5Config);
        return wxPayService.refund(request);
    }

	@Override
	public OrderQueryResponse query(OrderQueryRequest request) {
		//微信h5支付
        WxPayServiceImpl wxPayService = new WxPayServiceImpl();
        wxPayService.setWxPayH5Config(this.wxPayH5Config);
        return wxPayService.query(request);
	}

	@Override
	public OrderRefundQueryResponse refundQuery(OrderRefundQueryRequest request) {
		//微信h5支付
        WxPayServiceImpl wxPayService = new WxPayServiceImpl();
        wxPayService.setWxPayH5Config(this.wxPayH5Config);
		return wxPayService.refundQuery(request);
	}
}