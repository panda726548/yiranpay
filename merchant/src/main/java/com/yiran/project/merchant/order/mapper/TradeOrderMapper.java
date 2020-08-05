package com.yiran.project.merchant.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yiran.project.merchant.order.domain.TradeOrder;

public interface TradeOrderMapper {

	/**
	 * 获取交易订单列表
	 * @param order
	 * @return
	 */
	public List<TradeOrder> selectTradeOrderList(TradeOrder order);

	/**
	 * 根据订单号查询交易信息
	 * @param memberId
	 * @param instOrderNO
	 * @return
	 */
	public TradeOrder selectTradeOrderByInstOrderNo(@Param("memberId")String memberId, @Param("instOrderNO")String instOrderNO);

	/**
	 * 查询最近10条
	 * @return
	 */
	public List<TradeOrder> selectTradeOrderList10s(TradeOrder order);

	public double geTodayTradeMoney(@Param("memberId")String memberId,@Param("today")String today);

	public double getYesterdayTradeMoney(@Param("yesterday")String yesterday, @Param("memberId")String memberId);

	public int geTodayTradeCount(@Param("memberId")String memberId,@Param("today")String today);

	public int getYesterdayTradeCount(@Param("yesterday")String yesterday, @Param("memberId")String memberId);
	
}
