package com.channel.bank.adapter.pay.service;


import javax.servlet.http.HttpServletRequest;

import com.channel.bank.adapter.pay.config.SignType;
import com.channel.bank.adapter.pay.mode.OrderQueryRequest;
import com.channel.bank.adapter.pay.mode.OrderQueryResponse;
import com.channel.bank.adapter.pay.mode.OrderRefundQueryRequest;
import com.channel.bank.adapter.pay.mode.OrderRefundQueryResponse;
import com.channel.bank.adapter.pay.mode.PayFundRequest;
import com.channel.bank.adapter.pay.mode.PayFundResponse;
import com.channel.bank.adapter.pay.mode.RePayRequest;
import com.channel.bank.adapter.pay.mode.RePayResponse;

import java.util.Map;

/**
 * 支付相关
 */
public interface BestPayService {

    /**
     * 发起支付.
     */
    PayFundResponse pay(PayFundRequest request);
    
    /**
     * 订单查询
     * @param request
     * @return
     */
    OrderQueryResponse query(OrderQueryRequest request);

    /**
     * 验证支付结果. 包括同步和异步.
     *
     * @param toBeVerifiedParamMap 待验证的支付结果参数.
     * @param signType             签名方式.
     * @param sign                 签名.
     * @return 验证结果.
     */
    boolean verify(Map<String, String> toBeVerifiedParamMap, SignType signType, String sign);

    /**
     * 同步回调
     * @param request
     * @return
     */
    PayFundResponse syncNotify(HttpServletRequest request);

    /**
     * 异步回调
     * @param notifyData
     * @return
     */
    PayFundResponse asyncNotify(String notifyData);

    /**
     * 退款
     * @param request
     * @return
     */
    RePayResponse refund(RePayRequest request);

    /**
     * 退款查询
     * @param request
     * @return
     */
    OrderRefundQueryResponse refundQuery(OrderRefundQueryRequest request);
}
