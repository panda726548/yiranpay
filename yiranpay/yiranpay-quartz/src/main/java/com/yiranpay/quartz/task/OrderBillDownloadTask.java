package com.yiranpay.quartz.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.yiranpay.common.config.Global;
import com.yiranpay.member.domain.MemberTmMerchant;
import com.yiranpay.member.service.IMemberTmMerchantService;
import com.yiranpay.reconciliation.domain.ReconciliationAccountCheckBatch;
import com.yiranpay.reconciliation.domain.ReconciliationAccountflow;
import com.yiranpay.reconciliation.domain.ReconciliationEvent;
import com.yiranpay.reconciliation.enums.BatchStatusEnum;
import com.yiranpay.reconciliation.service.IReconciliationAccountCheckBatchService;
import com.yiranpay.reconciliation.service.IReconciliationAccountflowService;
import com.yiranpay.reconciliation.service.IReconciliationEventService;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
/**
 * 每天凌晨2点跑，生成一条下载前一天账单的对账文件信息
 * @author pandaa
 *
 */
@Component("orderBillDownloadTask")
public class OrderBillDownloadTask {
	
	private static final Logger LOG = LoggerFactory.getLogger(OrderBillDownloadTask.class);
	@Autowired
	private IReconciliationEventService reconciliationEventService;
	@Autowired
	private IMemberTmMerchantService memberTmMerchantService;
	@Autowired
	private IReconciliationAccountflowService reconciliationAccountflowService;
	public void creadeBillUrlTask() {
		LOG.info("每天凌晨2点跑，生成一条下载前一天账单的对账文件信息--->>>>>开始跑定时任务");
		//遍历所有的商户，查看每个商户是否有已经对账的订单
		MemberTmMerchant members = new MemberTmMerchant();
		List<MemberTmMerchant> merchantList = memberTmMerchantService.selectMemberTmMerchantList(members);
		for (MemberTmMerchant m : merchantList) {
			ReconciliationEvent event = new ReconciliationEvent();
			//根据商户号，对账时间，对账状态S
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//昨天
			DateTime yesterday = DateUtil.yesterday();
			ReconciliationAccountflow accountflow = new ReconciliationAccountflow();
			accountflow.setAccountDate(sdf.format(yesterday));
			accountflow.setCompareFlag("S");
			accountflow.setMemberId(m.getMemberId());
			List<ReconciliationAccountflow> list = reconciliationAccountflowService.selectReconciliationAccountflowList(accountflow);
			if(list.isEmpty()){//没有对账文件
				event.setTitle("无对账文件");
				event.setStart(sdf.format(yesterday));
				event.setMemberId(m.getMemberId());
				reconciliationEventService.insertReconciliationEvent(event);
			}else{
				event.setTitle("对账文件下载");
				event.setStart(sdf.format(yesterday));
				String channelCodes = getChannelCodes(list);
				event.setChannelcodes(channelCodes);
				event.setUrl(Global.getBillDonwoleUrl());
				event.setMemberId(m.getMemberId());
				reconciliationEventService.insertReconciliationEvent(event);
				
			}
			LOG.info("定时任务结束<<<<每天凌晨2点跑，生成一条下载前一天账单的对账文件信息："+JSON.toJSONString(event));
			
		}
	}
	
	public String getChannelCodes(List<ReconciliationAccountflow> list){
		List<ReconciliationAccountflow> list2 = removeDuplicatePlan(list);
		StringBuffer sb = new StringBuffer();
		for (ReconciliationAccountflow l : list2) {
			sb.append(l.getFundsChannel());
			sb.append(",");
		}
				
		return sb.toString();
	}

	/**   
     * @Title: removeDuplicatePlan   
     * @Description: 对象按某个字段去重  
     * @param planList
     * @return  
     * @return: List<ReconciliationAccountflow>  
     */
    private static List<ReconciliationAccountflow> removeDuplicatePlan(List<ReconciliationAccountflow> list) {
        Set<ReconciliationAccountflow> set = new TreeSet<ReconciliationAccountflow>(new Comparator<ReconciliationAccountflow>() {
            @Override
            public int compare(ReconciliationAccountflow a, ReconciliationAccountflow b) {
                // 字符串则按照asicc码升序排列
                return a.getFundsChannel().compareTo(b.getFundsChannel());
            }
        });
        
        set.addAll(list);
        return new ArrayList<ReconciliationAccountflow>(set);
    }
}
