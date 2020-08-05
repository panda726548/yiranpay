package com.yiran.project.merchant.order.service;

import java.util.List;

import com.yiran.project.merchant.order.domain.TradeOrder;

public interface ITradeOrderService {

	public List<TradeOrder> selectTradeOrderList(TradeOrder order);

	public TradeOrder selectTradeOrderByInstOrderNo(String instOrderNO);

	/**
	 * 获取最近10条
	 * @param order
	 * @return
	 */
	public List<TradeOrder> selectTradeOrderList10s(TradeOrder order);

	public double geTodayTradeMoney(String memberId);

	public double getYesterdayTradeMoney(String memberId);

	public int geTodayTradeCount(String memberId);

	public int getYesterdayTradeCount(String memberId);
}
