package com.yiranpay;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author yiranpay
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableRabbit
public class YiRanPayApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YiRanPayApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ 聚合支付系统启动成功   ლ(´ڡ`ლ)ﾞ ");
    }
}