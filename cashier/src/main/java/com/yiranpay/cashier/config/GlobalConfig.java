package com.yiranpay.cashier.config;

import cn.hutool.cache.impl.TimedCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局Bean配置
 */
@Configuration
public class GlobalConfig {

    @Bean
    public TimedCache<String, Object> timedCache() {
        //缓存24小时
        return new TimedCache<>(1000L * 60L * 60L * 24L);
    }

}
