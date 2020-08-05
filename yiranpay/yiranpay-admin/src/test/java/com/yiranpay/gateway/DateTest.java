package com.yiranpay.gateway;

import java.util.Date;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

public class DateTest {

	 public static void main(String[] args) {
	        // 日期字符串请补全为2位数字
	        String str1 = "2017-09-14";
	        // 自动感应格式
	        Date date1 = DateUtil.parse(str1);
	        // 日期时间偏移
	        DateTime dateTime = DateUtil.offset(new Date(), DateField.YEAR, 1);
	        //System.out.println("dateTime = " + dateTime);
	        DateTime dateTime1 = DateUtil.offsetDay(new Date(), 1);
	        System.out.println("dateTime1 = " + dateTime1.toDateStr());
	    }
}
