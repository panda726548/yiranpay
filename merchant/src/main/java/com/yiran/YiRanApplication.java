package com.yiran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author yiran
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class YiRanApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YiRanApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  商户站启动成功   ლ(´ڡ`ლ)ﾞ ");
    }
}