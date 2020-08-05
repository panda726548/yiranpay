package com.yiranpay.web.api.facade;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yiranpay.common.core.domain.ResultWrapper;
import com.yiranpay.paychannel.utils.MapUtil;
import com.yiranpay.payorder.domain.ChannelPayOrder;
import com.yiranpay.payorder.domaindo.ChannelPayOrderDO;
import com.yiranpay.payorder.domaindo.PayInstOrderDO;
import com.yiranpay.payorder.response.OrderInfoResponse;
import com.yiranpay.payorder.service.IChannelPayOrderService;
import com.yiranpay.payorder.service.IPayInstOrderService;

@RestController
@RequestMapping("/yiranpay/order")
public class PayOrderInfoFacade {
	private Logger logger = LoggerFactory.getLogger(PayOrderInfoFacade.class);
	
	@Autowired
	private IChannelPayOrderService channelPayOrderService;
	/**
	 * 根据订单号查询
	 * @param instOrderNo
	 * @return
	 */
	@PostMapping("/getOrderInfo")
	 public ResultWrapper<Map<String,Object>> queryOrderInfoByInstOrderNo(@RequestBody(required=false) String param) {
		logger.info("根据订单号查询订单信息->请求参数:"+param);
		Map<String, String> dataToMap = MapUtil.jsonToMap(param);
        try {
        	ChannelPayOrderDO channelPayOrder = new ChannelPayOrderDO();
        	channelPayOrder.setPaymentSeqNo(dataToMap.get("paymentSeqNo"));
        	List<ChannelPayOrderDO> channelPayOrderList = channelPayOrderService.selectChannelPayOrderList(channelPayOrder);
        	ChannelPayOrderDO order = channelPayOrderList.get(0);
        	OrderInfoResponse orderInfo = new OrderInfoResponse();
        	orderInfo.setAmount(order.getAmount());
        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	orderInfo.setBizDate(df.format(order.getGmtCreate()));
        	orderInfo.setInstCode(order.getInstCode());
        	//支付流水号
        	orderInfo.setPaymentSeqNo(order.getPaymentSeqNo());
            return ResultWrapper.ok().putData(orderInfo);
        } catch (Exception e) {
            throw new RuntimeException("调用queryOrderInfoByInstOrderNo接口异常");
        }
    }
}
