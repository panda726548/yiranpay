package com.yiranpay.cashier.tool;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebTool {

    /**
     * 线程安全的获取HttpServletRequest
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }

    /**
     * 线程安全的获取HttpServletRequest
     *
     * @return HttpServletRequest
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getResponse();
    }

    /**
     * 线程安全的获取HttpSession
     *
     * @return HttpSession
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 线程安全的获取HttpSession
     *
     * @param create 是否创建
     * @return HttpSession
     */
    public static HttpSession getSession(boolean create) {
        return getRequest().getSession(create);
    }

    /**
     * 线程安全的获取SessionId
     *
     * @return sessionId
     */
    public static String getSessionId() {
        HttpSession session = getSession(false);
        return (session != null ? session.getId() : null);
    }

    /**
     * 是否为安全请求<br>例如使用Https将返回true
     *
     * @return boolean
     */
    public static boolean isSecure() {
        return getRequest().isSecure();
    }

    /**
     * 是否为Ajax请求
     *
     * @return boolean
     */
    public static boolean isAjax() {
        String requestType = getRequest().getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(requestType);
    }

    /**
     * 是否为GET请求
     *
     * @return boolean
     */
    public static boolean isGet() {
        return "GET".equalsIgnoreCase(getRequest().getMethod());
    }

    /**
     * 是否为POST请求
     *
     * @return boolean
     */
    public static boolean isPost() {
        return "POST".equalsIgnoreCase(getRequest().getMethod());
    }

    /**
     * 获取主机地址
     *
     * @return String
     */
    public static String getHostUrl() {
        HttpServletRequest request = getRequest();
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        String contextPath = request.getContextPath();
        int serverPort = request.getServerPort();

        StringBuilder sb = new StringBuilder();
        sb.append(scheme)
                .append("://")
                .append(serverName);

        if (80 != serverPort && 443 != serverPort) {
            sb.append(":").append(serverPort);
        }

        sb.append(contextPath);

        return sb.toString();
    }
}
