package com.yiranpay.framework.interceptor.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 日志拦截器
 * @author pandaa
 *
 */
@Component
public class SessionInterceptor extends HandlerInterceptorAdapter{

	/**
     * 会话ID
     */
    private final static String SESSION_KEY = "sessionId";


    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

//        String token = UUID.randomUUID().toString().replaceAll("-","");
        //本例测试使用sessionId，也可以使用UUID等
        String token = request.getSession().getId();
        MDC.put(SESSION_KEY, token);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // 删除
        MDC.remove(SESSION_KEY);
    }
}
