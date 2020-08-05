package com.yiranpay.mqlistener;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yiranpay.payorder.domain.ChannelFundResult;
import com.yiranpay.payorder.domain.PayInstOrderResult;
import com.yiranpay.payorder.facade.IResultNotifyFacade;
import com.yiranpay.payorder.service.IPayInstOrderService;
import com.yiranpay.payorder.service.InstitutionResultService;

@Service
public class PayresultProcessListener {
	private Logger logger = LoggerFactory.getLogger(PayresultProcessListener.class);
	@Autowired
    private InstitutionResultService   institutionResultService;
	@Autowired
   	private IPayInstOrderService payInstOrderService;
	@Autowired
    private ObjectMapper objectMapper;
	@Autowired
    private IResultNotifyFacade resultNotifyFacade;
	
	@RabbitListener(queues="queues.payresult.process")
	public void receive(@Payload byte[] message){
		ChannelFundResult result = null;
		try {
			result = objectMapper.readValue(message, ChannelFundResult.class);
			logger.info("收到MQ消息转换为ChannelFundResult对象:{}",result);
			if(result!=null){
			
				if (result != null) {
					PayInstOrderResult instOrderResult = institutionResultService.process(payInstOrderService.loadByInstOrderNo(result.getInstOrderNo()),
		           		result,false);
					resultNotifyFacade.notifyBiz(result.getInstOrderNo(), null);
					logger.info("订单号：{}，结果通过MQ消息处理完成,返回结果：{}",result.getInstOrderNo(),instOrderResult);
		       } 
			}else{
				logger.error("没有MQ消息可以消费");
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}
}
