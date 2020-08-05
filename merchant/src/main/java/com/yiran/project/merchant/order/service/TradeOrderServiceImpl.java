package com.yiran.project.merchant.order.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.common.utils.security.ShiroUtils;
import com.yiran.project.merchant.order.domain.TradeOrder;
import com.yiran.project.merchant.order.mapper.TradeOrderMapper;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
@Service
public class TradeOrderServiceImpl implements ITradeOrderService {

	@Autowired
	private TradeOrderMapper tradeOrderMapper;
	
	@Override
	public List<TradeOrder> selectTradeOrderList(TradeOrder order) {
		String memberId = ShiroUtils.getUserId();
		order.setMemberId(memberId);
		List<TradeOrder> list = tradeOrderMapper.selectTradeOrderList(order);
		DecimalFormat df = new DecimalFormat("#.00");
		double sumAmount = 0.00;
		for (TradeOrder tradeOrder : list) {
			sumAmount += Double.parseDouble(tradeOrder.getAmount());
		}
		for (TradeOrder tradeOrder : list) {
			tradeOrder.setTotalAmount(String.valueOf(df.format(sumAmount)));
		}
		return list;
	}

	@Override
	public TradeOrder selectTradeOrderByInstOrderNo(String instOrderNO) {
		String memberId = ShiroUtils.getUserId();
		return tradeOrderMapper.selectTradeOrderByInstOrderNo(memberId,instOrderNO);
	}

	@Override
	public List<TradeOrder> selectTradeOrderList10s(TradeOrder order) {
		return tradeOrderMapper.selectTradeOrderList10s(order);
	}

	@Override
	public double geTodayTradeMoney(String memberId) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(new Date());
		double totalMoney = tradeOrderMapper.geTodayTradeMoney(memberId,today);
		return totalMoney;
	}

	@Override
	public double getYesterdayTradeMoney(String memberId) {
		DateTime yesterday = DateUtil.yesterday();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        double yesterdayTotalMoney = tradeOrderMapper.getYesterdayTradeMoney(formatter.format(yesterday),memberId);
		return yesterdayTotalMoney;
	}

	@Override
	public int geTodayTradeCount(String memberId) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(new Date());
		int totalMoney = tradeOrderMapper.geTodayTradeCount(memberId,today);
		return totalMoney;
	}

	@Override
	public int getYesterdayTradeCount(String memberId) {
		DateTime yesterday = DateUtil.yesterday();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        int yesterdayTotalMoney = tradeOrderMapper.getYesterdayTradeCount(formatter.format(yesterday),memberId);
		return yesterdayTotalMoney;
	}

	
}
