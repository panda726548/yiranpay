package com.yiranpay.web.controller.payorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.payorder.domain.ChannelPayOrder;
import com.yiranpay.payorder.domain.PayInstOrder;
import com.yiranpay.payorder.domain.PayInstOrderResult;
import com.yiranpay.payorder.domain.PayResultNotify;
import com.yiranpay.payorder.domain.PayResultNotifyLog;
import com.yiranpay.payorder.domain.QueryOrderResult;
import com.yiranpay.payorder.domaindo.PayInstOrderDO;
import com.yiranpay.payorder.domaindo.VInstOrder;
import com.yiranpay.payorder.domaindo.VInstOrderResult;
import com.yiranpay.payorder.domaindo.VOrder;
import com.yiranpay.payorder.domaindo.VPayOrder;
import com.yiranpay.payorder.enums.JointQueryType;
import com.yiranpay.payorder.facade.InstOrderProcessFacade;
import com.yiranpay.payorder.service.IChannelPayOrderService;
import com.yiranpay.payorder.service.IJointQueryService;
import com.yiranpay.payorder.service.IPayInstOrderResultService;
import com.yiranpay.payorder.service.IPayInstOrderService;
import com.yiranpay.payorder.service.IPayResultNotifyLogService;
import com.yiranpay.payorder.service.IPayResultNotifyService;
import com.yiranpay.reconciliation.domain.ReconciliationAccountflow;
import com.yiranpay.reconciliation.service.IReconciliationAccountflowService;

@Controller
@RequestMapping("/payorder/jointQuery")
public class JointQueryController extends BaseController{
	private Logger             logger = LoggerFactory.getLogger(JointQueryController.class);
	private String prefix = "payorder/jointQuery";
	@Autowired
	private InstOrderProcessFacade instOrderProcessFacade;
	@Autowired
	private IJointQueryService jointQueryService;
	@Autowired
	private IPayInstOrderService payInstOrderService;
	
	@Autowired
	private IChannelPayOrderService channelPayOrderService;
	
	@Autowired
	private IPayInstOrderResultService payInstOrderResultService;
	
	@Autowired
    private IPayResultNotifyLogService payResultNotifyLogService;
    @Autowired
    private IPayResultNotifyService payResultNotifyService;
    @Autowired
    private IReconciliationAccountflowService reconciliationAccountflowService;
	/**
	 * 进入查询页面
	 */
	@GetMapping("/instOrderQueryView")
	public String instOrderQueryView()
	{
	    return prefix + "/instOrderQuery";
	}
	
	@GetMapping("/instOrderQuery/{orderNo}")
	@ResponseBody
	public AjaxResult instOrderQuery(@PathVariable("orderNo") String orderNo)
	{	
		logger.info("联合查询订单号："+orderNo);
		QueryOrderResult queryOrderResult = instOrderProcessFacade.queryInstOrderResult(orderNo);
		return AjaxResult.success(queryOrderResult);
	}
	
	/**
	 * 进入查询页面
	 */
	@GetMapping("/queryView")
	public String queryView()
	{
	    return prefix + "/jointQuery";
	}
	/**
	 * 执行查询
	 * @param request
	 * @return
	 */
	@GetMapping("/query/{orderType}/{orderNo}")
	@ResponseBody
	public AjaxResult query(@PathVariable("orderType") String orderType,@PathVariable("orderNo") String orderNo)
	{	
		logger.info("联合查询订单类型："+orderType);
		logger.info("联合查询订单号："+orderNo);
		//JointQueryType
		Map<String,Object> map = getQueryData(orderType,orderNo);
		return AjaxResult.success(map);
	}
	
	
	private Map<String, Object> getQueryData(String orderType, String orderNo) {
		
		if(JointQueryType.Q1001.getCode().equals(orderType)){//机构订单ID查询
			return selectDataByInstOrderId(orderNo);
		}else if(JointQueryType.Q1002.getCode().equals(orderType)){//提交机构订单号查询
			//1.根据提交机构订单号查询出机构订单ID
			PayInstOrder payInstOrder = payInstOrderService.loadByInstOrderNo(orderNo);
			return selectDataByInstOrderId(String.valueOf(payInstOrder.getInstOrderId()));
		}else if(JointQueryType.Q1003.getCode().equals(orderType)){//机构返回流水号查询
			//机构返回流水号查询机构订单ID
			PayInstOrderResult payInstOrderResult = payInstOrderResultService.loadRealResultByInstSeqNo(orderNo);
			return selectDataByInstOrderId(String.valueOf(payInstOrderResult.getInstOrderId()));
		}else if(JointQueryType.Q1004.getCode().equals(orderType)){//支付流水号查询
			//支付流水号查询机构订单ID
			ChannelPayOrder channelPayOrder = channelPayOrderService.loadByPaymentSeqNo(orderNo);
			return selectDataByInstOrderId(String.valueOf(channelPayOrder.getInstOrderId()));
		}else if(JointQueryType.Q1005.getCode().equals(orderType)){//支付凭证号查询
			//TODO:需要完善
		}else if(JointQueryType.Q1006.getCode().equals(orderType)){//交易原始凭证号查询
			//交易原始凭证号查询机构订单ID
			ChannelPayOrder channelPayOrder = channelPayOrderService.loadByOrgiPaymentSeqNo(orderNo);
			return selectDataByInstOrderId(String.valueOf(channelPayOrder.getInstOrderId()));
		}else if(JointQueryType.Q1007.getCode().equals(orderType)){//清算请求号查询
			//TODO:需要完善
		}else if(JointQueryType.Q1008.getCode().equals(orderType)){//交易凭证号查询
			//TODO:需要完善
		}
		
		return null;
	}
	
	
	private  Map<String, Object> selectDataByInstOrderId(String instOrderId) {
		Map<String,Object> map = new HashMap<String,Object>();
		//ChannelPayOrder对象
		VPayOrder vPayOrder = new VPayOrder();
		//机构订单
		VInstOrder vInstOrder = new VInstOrder();
		//机构订单结果
		VInstOrderResult vInstOrderResult = new VInstOrderResult();
		VOrder order =new VOrder();
		vPayOrder.setInstOrderId(instOrderId);
		vInstOrder.setInstOrderId(instOrderId);
		vInstOrderResult.setInstOrderId(instOrderId);
		List<VPayOrder> channelPayOrderList = convertVPayOrder(jointQueryService.selectChannelPayOrderList(vPayOrder));
		List<VInstOrder> instOrderList = convertVInstOrder(jointQueryService.selectInstOrderList(vInstOrder));
		List<VInstOrderResult> orderResultList = convertVInstOrderResult(jointQueryService.selectInstOrderResultList(vInstOrderResult));
		PayInstOrderDO instOrderDO = payInstOrderService.selectPayInstOrderById(Integer.parseInt(instOrderId));
		order.setEscrowTradeNo(instOrderDO.getInstOrderNo());
		//List<VOrder> orderList = jointQueryService.selectInstOrderList(order);
		//查询支付结果通知
		PayResultNotify resultNotify = payResultNotifyService.selectPayResultNotifyByPaymentSeqNo(channelPayOrderList.get(0).getPaymentSeqNo());
		List<PayResultNotifyLog> notifyLogList = new ArrayList<PayResultNotifyLog>();
		if(resultNotify!=null){
			//支付结果通知明细
			PayResultNotifyLog payResultNotifyLog = new PayResultNotifyLog();
			payResultNotifyLog.setNotifyId(resultNotify.getNotifyId());
			notifyLogList = payResultNotifyLogService.selectPayResultNotifyLogList(payResultNotifyLog);		
		}
		ReconciliationAccountflow accountFlow = this.reconciliationAccountflowService.selectReconciliationAccountflowByBizNo(instOrderDO.getInstOrderNo());
		accountFlow = covertAccountFlow(accountFlow);
		List<ReconciliationAccountflow> accountFlowList= new ArrayList<ReconciliationAccountflow>();
		if(accountFlow != null){
			accountFlowList.add(accountFlow);
		}
		map.put("channelPayOrderList", channelPayOrderList);
		map.put("instOrderList", instOrderList);
		map.put("orderResultList", orderResultList);
		map.put("resultNotify", resultNotify);
		map.put("notifyLogList", notifyLogList);
		map.put("accountFlowList", accountFlowList);
		//map.put("orderList", orderList);
		return map;
	}
	
	private ReconciliationAccountflow covertAccountFlow(ReconciliationAccountflow accountFlow) {
		if(accountFlow ==null){
			return null;
		}
		if("I".equals(accountFlow.getBizType())){
			accountFlow.setBizType("入款");
		}else if("O".equals(accountFlow.getBizType())){
			accountFlow.setBizType("出款");
		}else if("B".equals(accountFlow.getBizType())){
			accountFlow.setBizType("退款款");
		}
		
		if("I".equals(accountFlow.getCompareFlag())){
			accountFlow.setCompareFlag("初始");
		}else if("S".equals(accountFlow.getCompareFlag())){
			accountFlow.setCompareFlag("成功");
		}else if("W".equals(accountFlow.getCompareFlag())){
			accountFlow.setCompareFlag("已勾销");
		}else if("L".equals(accountFlow.getCompareFlag())){
			accountFlow.setCompareFlag("逻辑错误");
		}else if("U".equals(accountFlow.getCompareFlag())){
			accountFlow.setCompareFlag("金额不等");
		}
		return accountFlow;
	}

	public List<VPayOrder> convertVPayOrder(List<VPayOrder> channelPayOrderList){
		List<VPayOrder> list = new ArrayList<VPayOrder>();
		for (VPayOrder po : channelPayOrderList) {
			po.setPaySeqNo(po.getPaySeqNo()==null?"":po.getPaySeqNo());
			po.setPaymentSeqNo(po.getPaymentSeqNo()==null?"":po.getPaymentSeqNo());
			po.setInstOrderId(po.getInstOrderId()==null?"":po.getInstOrderId());
			po.setPayMode(po.getPayMode()==null?"":po.getPayMode());
			po.setInstCode(po.getInstCode()==null?"":po.getInstCode());
			po.setPaymentNotifyStatus(po.getPaymentNotifyStatus()==null?"":po.getPaymentNotifyStatus());
			po.setStatus(po.getStatus()==null?"":po.getStatus());
			po.setConfirmStatus(po.getConfirmStatus()==null?"":po.getConfirmStatus());
			list.add(po);
		}
		return channelPayOrderList;
	}
	
	public List<VInstOrder> convertVInstOrder(List<VInstOrder> instOrderList){
		List<VInstOrder> list = new ArrayList<VInstOrder>();
		for (VInstOrder io : instOrderList) {
			io.setInstOrderId(io.getInstOrderId()==null?"":io.getInstOrderId());
			io.setInstOrderNo(io.getInstOrderNo()==null?"":io.getInstOrderNo());
			io.setFundChannel(io.getFundChannel()==null?"":io.getFundChannel());
			io.setOrderType(io.getOrderType()==null?"":io.getOrderType());
			io.setAmount(io.getAmount()==null?"":io.getAmount());
			io.setStatus(io.getStatus()==null?"":io.getStatus());
			io.setCommunicateType(io.getCommunicateType()==null?"":io.getCommunicateType());
			io.setCommunicateStatus(io.getCommunicateStatus()==null?"":io.getCommunicateStatus());
			io.setArchiveBatchId(io.getArchiveBatchId()==null?"":io.getArchiveBatchId());
			list.add(io);
		}
		return list;
	}
	
	private List<VInstOrderResult> convertVInstOrderResult(List<VInstOrderResult> instOrderResultList) {
		List<VInstOrderResult> list = new ArrayList<VInstOrderResult>();
		for (VInstOrderResult or : instOrderResultList) {
			or.setResultId(or.getResultId()==null?"":or.getResultId());
			or.setInstOrderId(or.getInstOrderId()==null?"":or.getInstOrderId());
			or.setInstSeqNo(or.getInstSeqNo()==null?"":or.getInstSeqNo());
			or.setBatchType(or.getBatchType()==null?"":or.getBatchType());
			or.setInstStatus(or.getInstStatus()==null?"":or.getInstStatus());
			or.setInstResultCode(or.getInstResultCode()==null?"":or.getInstResultCode());
			or.setApiResultCode(or.getApiResultCode()==null?"":or.getApiResultCode());
			or.setRealAmount(or.getRealAmount()==null?"":or.getRealAmount());
			or.setCardType(or.getCardType()==null?"":or.getCardType());
			or.setCompareStatus(or.getCompareStatus()==null?"":or.getCompareStatus());
			list.add(or);
		}
		
		return list;
	}
}
