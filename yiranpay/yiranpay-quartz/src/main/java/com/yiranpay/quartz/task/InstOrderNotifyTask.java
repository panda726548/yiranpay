package com.yiranpay.quartz.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yiranpay.payorder.domain.ChannelPayOrder;
import com.yiranpay.payorder.domain.PayInstOrder;
import com.yiranpay.payorder.domain.PayResultNotify;
import com.yiranpay.payorder.domaindo.ChannelPayOrderDO;
import com.yiranpay.payorder.facade.IResultNotifyFacade;
import com.yiranpay.payorder.service.IChannelPayOrderService;
import com.yiranpay.payorder.service.IPayInstOrderService;
import com.yiranpay.payorder.service.IPayResultNotifyService;

@Component("instOrderNotifyTask")
public class InstOrderNotifyTask {
	
	@Autowired
   	private IPayInstOrderService payInstOrderService;

	@Autowired
   	private IChannelPayOrderService channelPayOrderService;
	
	@Autowired
    private IPayResultNotifyService payResultNotifyService;
	
	@Autowired
	private IResultNotifyFacade resultNotifyFacade;
	
	public void notifyTask(){
		Map<String,String> xmlMap = new HashMap<String,String>();
		//1.把所有没有发送通知的发下通知
		List<ChannelPayOrderDO> channelPayOrderList = channelPayOrderService.selectChannelPayOrderListIsNotS();
		for (ChannelPayOrderDO channelPayOrder : channelPayOrderList) {
			PayInstOrder payInstOrder = payInstOrderService.loadById(channelPayOrder.getInstOrderId());
			resultNotifyFacade.notifyBiz(payInstOrder.getInstOrderNo(),xmlMap);
		}
		//2.将通知表中处理中或者发送失败的重新发送
		List<PayResultNotify> payResultNotifyList = payResultNotifyService.selectPayResultNotifyListIsFail();
		for (PayResultNotify payResultNotify : payResultNotifyList) {
			ChannelPayOrder channelPayOrder = channelPayOrderService.loadByPaymentSeqNo(payResultNotify.getInstOrderId());
			PayInstOrder payInstOrder = payInstOrderService.loadById(channelPayOrder.getInstOrderId());
			resultNotifyFacade.notifyBiz(payInstOrder.getInstOrderNo(),xmlMap);
		}
		
	}
}
