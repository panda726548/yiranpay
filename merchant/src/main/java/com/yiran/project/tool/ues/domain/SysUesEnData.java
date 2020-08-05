package com.yiran.project.tool.ues.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.framework.aspectj.lang.annotation.Excel;
import com.yiran.framework.web.domain.BaseEntity;

/**
 * UES数据加密对象 sys_ues_en_data
 * 
 * @author panda
 * @date 2020-03-03
 */
public class SysUesEnData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 票据 */
    @Excel(name = "票据")
    private String ticket;

    /** 加密密码 */
    @Excel(name = "加密密码")
    private String encryptKey;

    /** 加密密文 */
    @Excel(name = "加密密文")
    private String encryptData;

    /** 版本 */
    @Excel(name = "版本")
    private String version;

    /** 加密类型 */
    @Excel(name = "加密类型")
    private String encryptType;

    /**
	 * 原文
	 */
	private String originalText;
	
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTicket(String ticket) 
    {
        this.ticket = ticket;
    }

    public String getTicket() 
    {
        return ticket;
    }
    public void setEncryptKey(String encryptKey) 
    {
        this.encryptKey = encryptKey;
    }

    public String getEncryptKey() 
    {
        return encryptKey;
    }
    public void setEncryptData(String encryptData) 
    {
        this.encryptData = encryptData;
    }

    public String getEncryptData() 
    {
        return encryptData;
    }
    public void setVersion(String version) 
    {
        this.version = version;
    }

    public String getVersion() 
    {
        return version;
    }
    public void setEncryptType(String encryptType) 
    {
        this.encryptType = encryptType;
    }

    public String getEncryptType() 
    {
        return encryptType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ticket", getTicket())
            .append("encryptKey", getEncryptKey())
            .append("encryptData", getEncryptData())
            .append("version", getVersion())
            .append("encryptType", getEncryptType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }

	public String getOriginalText() {
		return originalText;
	}

	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}
    
}
