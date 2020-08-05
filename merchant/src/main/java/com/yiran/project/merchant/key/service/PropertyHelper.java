package com.yiran.project.merchant.key.service;
import java.util.Properties;

/**
 * 
 * <p>属性文件帮助类</p>
 */
public interface PropertyHelper {
    
    public Properties getProperties(String fundChannelCode);
    
    public Properties getProperties(String fundChannelCode,String defaultChannelCode);
    
}
