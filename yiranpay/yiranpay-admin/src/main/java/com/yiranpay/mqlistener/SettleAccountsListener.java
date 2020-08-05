package com.yiranpay.mqlistener;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yiranpay.member.domain.MemberTrMemberAccount;
import com.yiranpay.member.domain.MemberTrMemberAccountDetail;
import com.yiranpay.member.service.IMemberTrMemberAccountDetailService;
import com.yiranpay.member.service.IMemberTrMemberAccountService;
import com.yiranpay.paychannel.utils.MapUtil;
import com.yiranpay.reconciliation.domain.ReconciliationAccountflow;

@Component
public class SettleAccountsListener {
	private Logger logger = LoggerFactory.getLogger(SettleAccountsListener.class);
	@Autowired
	private IMemberTrMemberAccountService memberTrMemberAccountService;
	@Autowired
	private IMemberTrMemberAccountDetailService memberTrMemberAccountDetailService;
	
	@SuppressWarnings("unused")
	@RabbitListener(queues="queues.settle.accounts")
	public void receive(Message message){
		try {
			logger.info("收到MQ消息："+new String(message.getBody(),"utf-8"));
			Map<String, String> map = MapUtil.jsonToMap(new String(message.getBody(),"utf-8"));
			String messageJson = map.get("message");
			Map<String, String> mapFlow = MapUtil.jsonToMap(messageJson);
			logger.info("收到MQ消息转换为对象:{}",mapFlow);
			if(!mapFlow.isEmpty() || mapFlow.size()!=0){
				MemberTrMemberAccount account = new MemberTrMemberAccount();
				account.setMemberId(mapFlow.get("memberId"));
				List<MemberTrMemberAccount> accountList = memberTrMemberAccountService.selectMemberTrMemberAccountList(account);
				account = accountList.get(0);
				MemberTrMemberAccountDetail accountDetail = memberTrMemberAccountDetailService.selectMemberTrMemberAccountDetailByMemberAccountId(account.getId());
				//更新余额和可用余额
				double balance = Double.parseDouble(mapFlow.get("amount")) + Double.parseDouble(accountDetail.getBalance());
				accountDetail.setBalance(String.valueOf(balance));
				double availableBalance = Double.parseDouble(mapFlow.get("amount")) + Double.parseDouble(accountDetail.getAvailableBalance());
				accountDetail.setAvailableBalance(String.valueOf(availableBalance));
				
				memberTrMemberAccountDetailService.updateMemberTrMemberAccountDetail(accountDetail);
				
				logger.info("商户号：{}，交易金额：{}，余额：{}，可用余额：{}",mapFlow.get("memberId"),mapFlow.get("amount"),balance,availableBalance);
			}else{
				logger.error("没有MQ消息可以消费");
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		 
	}
}
