package com.channel.bank.adapter.pay.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * <p>注释</p>
 */
public enum CallBackType {

    /**
     * 页面方式回调通知
     */
    PAGE("page", "页面方式回调通知"),
    
    /**
     * 服务器对服务器方式通知
     */
    SERVER("server", "服务器对服务器方式通知");

    private final String code;

    private final String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private CallBackType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CallBackType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (CallBackType item : values()) {
            if (StringUtils.equals(item.getCode(), code)) {
                return item;
            }
        }

        return null;
    }
}
