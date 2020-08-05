package com.yiranpay.cashier.tool;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.Date;

public class OrderNoTool {

    public static String generate() {
        return DateUtil.format(new Date(), "yyyyMMddHHmmsss") + RandomUtil.randomNumbers(5);
    }

}
