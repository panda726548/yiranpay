package com.channel.bank.adapter.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;

/**
 * 启动程序
 * 
 */
@SpringBootApplication(scanBasePackages = "com.channel.bank.adapter.pay",exclude = WebSocketServletAutoConfiguration.class)
public class WXChannelApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(WXChannelApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ 支付渠道启动成功   ლ(´ڡ`ლ)ﾞ ");
    }
}