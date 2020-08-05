package com.yiran.project.merchant.order.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yiran.common.utils.poi.ExcelUtil;
import com.yiran.common.utils.security.ShiroUtils;
import com.yiran.framework.web.controller.BaseController;
import com.yiran.framework.web.domain.AjaxResult;
import com.yiran.framework.web.page.TableDataInfo;
import com.yiran.project.merchant.order.domain.Accountflow;
import com.yiran.project.merchant.order.domain.EventVO;
import com.yiran.project.merchant.order.domain.PayerInfo;
import com.yiran.project.merchant.order.domain.ReconciliationAccountflow;
import com.yiran.project.merchant.order.domain.ReconciliationEvent;
import com.yiran.project.merchant.order.domain.TradeOrder;
import com.yiran.project.merchant.order.service.IReconciliationAccountflowService;
import com.yiran.project.merchant.order.service.IReconciliationEventService;
import com.yiran.project.merchant.order.service.ITradeOrderService;
import com.yiran.project.merchant.order.utils.ConvertUtils;
import com.yiran.project.merchant.order.utils.SensitiveInfoUtils;
@Controller
@RequestMapping("/merchant/order")
public class TradeOrderController extends BaseController{

	private String prefix = "merchant/order";
	
	@Autowired
	private ITradeOrderService tradeOrderService;
	
	@Autowired
	private IReconciliationEventService reconciliationEventService;
	@Autowired
	private IReconciliationAccountflowService reconciliationAccountflowService;
    @GetMapping()
    public String orderinfo()
    {
        return prefix + "/orderinfo";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TradeOrder order)
    {
        startPage();
        List<TradeOrder> list = tradeOrderService.selectTradeOrderList(order);
        return getDataTable(list);
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") String id, ModelMap mmap)
    {
    	TradeOrder order = tradeOrderService.selectTradeOrderByInstOrderNo(id);
    	PayerInfo info = convertPayerInfo(order.getExtend());
    	System.out.println("扩展参数对象："+info);
    	order.setPayerInfo(info);
    	mmap.put("order", order);
        return prefix + "/show";
    }

	private PayerInfo convertPayerInfo(String extend) {
		PayerInfo payerInfo = new PayerInfo();
		Map<String, String> map = ConvertUtils.convertFromDb(extend);
		if(!StringUtils.isBlank(map.get("openId"))){
			payerInfo.setOpenId(map.get("openId"));
		}
		
		if(!StringUtils.isBlank(map.get("orderName"))){
			payerInfo.setOrderName(map.get("orderName"));
		}
		
		if(!StringUtils.isBlank(map.get("accountName"))){
			payerInfo.setAccountName(SensitiveInfoUtils.chineseName(map.get("accountName")));
		}
		
		if(!StringUtils.isBlank(map.get("mobileNo"))){
			payerInfo.setMobileNo(SensitiveInfoUtils.mobilePhone(map.get("mobileNo")));
		}
		
		if(!StringUtils.isBlank(map.get("idNo"))){
			payerInfo.setIdNo(SensitiveInfoUtils.idCard(map.get("idNo")));
		}
		
		if(!StringUtils.isBlank(map.get("cardNo"))){
			payerInfo.setCardNo(SensitiveInfoUtils.bankCard(map.get("cardNo")));
		}
		
		return payerInfo;
	}
	
	@GetMapping("/showBbillWeb")
    public String showOrderBill(ModelMap mmap)
    {
        return prefix + "/bill";
    }
	
	 @PostMapping("/getData")
	 @ResponseBody
	 public AjaxResult getData(EventVO vo){
		 System.out.println("请求参数："+JSON.toJSONString(vo));
		//获取所有账单列表
		ReconciliationEvent event = new ReconciliationEvent();
		event.setMemberId(ShiroUtils.getUserId());
		event.setStart(vo.getStart());
		//event.setStart("2020-04-30");//模拟数据
		List<ReconciliationEvent> list = reconciliationEventService.selectReconciliationEventListByMoth(event);
		List<EventVO> elist = new ArrayList<EventVO>();
		for (ReconciliationEvent e : list) {
			EventVO v = new EventVO();
			v.setId(e.getId());
			v.setTitle(e.getTitle());
			v.setStart(e.getStart());
			v.setUrl(e.getUrl() == null?"":e.getUrl());
			elist.add(v);
		}
		System.out.println("EventVO集合："+JSON.toJSONString(elist));
		return AjaxResult.success("数据加载成功",JSON.toJSONString(elist));
	 }
	 
	 
 	 @PostMapping("/dowmload")
     @ResponseBody
     public AjaxResult export(EventVO vo)
     {
 		 System.out.println("下载对账文件请求参数:"+JSON.toJSONString(vo));
 		//根据id获取下载文件对象
 		ReconciliationEvent event = reconciliationEventService.selectReconciliationEventById(vo.getId());
 		System.out.println("根据id获取下载文件对象:"+JSON.toJSONString(event));
 		//获取渠道集合
 		String channelcodes = event.getChannelcodes();
 		String[] fundCodes = channelcodes.split(",");
 		String compareDate = event.getStart();
 		String compareFlag = "S";
 		String memberId = ShiroUtils.getUserId();
 		//根据渠道号、对账时间、对账成功
        List<ReconciliationAccountflow> list = reconciliationAccountflowService.selectAccountflowLists(compareDate,compareFlag,fundCodes,memberId);
        ExcelUtil<Accountflow> util = new ExcelUtil<Accountflow>(Accountflow.class);
        List<Accountflow> alist =coverAccountFlow(list);
        System.out.println("转换为导出对象："+JSON.toJSONString(alist));
        return util.exportExcel(alist, "accountflow");
     }

	private List<Accountflow> coverAccountFlow(List<ReconciliationAccountflow> list) {
		List<Accountflow> alist = new ArrayList<Accountflow>();
		if(list.size() > 0){
			for(ReconciliationAccountflow a :list){
				Accountflow af = new Accountflow();
				af.setMemberId(a.getMemberId());
				af.setPaymentSeqNo(a.getPaymentSeqNo());
				af.setAmount(a.getAmount());
				af.setBankCode(a.getBankCode());
				af.setBizNo(a.getBizNo());
				af.setBizType(a.getBizType());
				af.setGmtCreate(a.getGmtCreate());
				alist.add(af);
			}
		}
		return alist;
	}
    
}
