package com.yiranpay.payorder;

import java.math.BigDecimal;

import org.springframework.util.Assert;

public class MoenyTest {

	public static void main(String[] args) {
		double amount = Double.valueOf("0.01");
    	System.out.println("转换后的付款金额："+amount);
		BigDecimal money = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_DOWN);
		System.out.println("BigDecimal对象值："+money.toString());
		
		System.out.println("--------------------------------------");
		BigDecimal money_0 = new BigDecimal("0").setScale(2, BigDecimal.ROUND_DOWN);
		System.out.println("-------------------------------------");
		int flag = money.compareTo(money_0);
		//当此 BigDecimal 在数字上小于、等于或大于 val 时，返回 -1、0 或 1。
		System.out.println("flag="+flag);
		Assert.isTrue(true, "支付金额必须大于零");
	}
}
