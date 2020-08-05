package com.yiranpay.web.core.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yiranpay.framework.interceptor.impl.SessionInterceptor;
import com.yiranpay.web.api.interceptor.AuthorizationInterceptor;

@Configuration
public class AuthorizationConfig implements WebMvcConfigurer{
	
	protected static final Logger logger = LoggerFactory.getLogger(AuthorizationConfig.class);
	
	@Autowired
    private AuthorizationInterceptor authorizationInterceptor;
	//@Autowired
	//private SessionInterceptor sessionInterceptor;
	
	@Bean
    public SessionInterceptor getSessionInterceptor() {
        return new SessionInterceptor();
    }

	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
    	List<String> patterns = new ArrayList<String>();
    	//TODO:这里添加api路径
    	patterns.add("/api/yiranpay/gateway/**");
        registry.addInterceptor(authorizationInterceptor)
        .addPathPatterns(patterns);
        
        //registry.addInterceptor(sessionInterceptor)
        //.addPathPatterns("/*");
        logger.info("Register FirstIndexInterceptor and SecondIndexInterceptor onto InterceptorRegistry");
    }
}
