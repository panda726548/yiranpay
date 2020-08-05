package com.yiranpay.cashier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 启动类
 */
@SpringBootApplication(scanBasePackages = "com.yiranpay.cashier", exclude = WebSocketServletAutoConfiguration.class)
@ServletComponentScan
public class YiRanCashierApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiRanCashierApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ 聚合支付收银台启动成功   ლ(´ڡ`ლ)ﾞ ");
    }

}
