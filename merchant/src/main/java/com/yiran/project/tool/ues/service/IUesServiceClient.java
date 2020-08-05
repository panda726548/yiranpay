package com.yiran.project.tool.ues.service;

import java.util.List;
import java.util.Map;

import com.yiran.project.tool.ues.enums.EncryptType;


/**
 * UES接口
 * @author pandaa
 *
 */
public interface IUesServiceClient {

    /**
     * 解密
     * @param ticket
     * @return
     */
    public String getDataByTicket(String ticket,EncryptType encryptType);

}
