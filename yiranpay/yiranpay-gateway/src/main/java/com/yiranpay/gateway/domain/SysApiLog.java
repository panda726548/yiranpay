package com.yiranpay.gateway.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiranpay.common.annotation.Excel;
import com.yiranpay.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 网关API接口日志对象 sys_api_log
 * 
 * @author panda
 * @date 2020-04-23
 */
public class SysApiLog 
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 商户号 */
    @Excel(name = "商户号")
    private String partnerid;

    /** 1请求参数2返回结果 */
    @Excel(name = "1请求参数2返回结果")
    private Long type;

    /** 日志信息 */
    @Excel(name = "日志信息")
    private String logger;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPartnerid(String partnerid) 
    {
        this.partnerid = partnerid;
    }

    public String getPartnerid() 
    {
        return partnerid;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setLogger(String logger) 
    {
        this.logger = logger;
    }

    public String getLogger() 
    {
        return logger;
    }
    public void setCreatTime(Date creatTime) 
    {
        this.creatTime = creatTime;
    }

    public Date getCreatTime() 
    {
        return creatTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("partnerid", getPartnerid())
            .append("type", getType())
            .append("logger", getLogger())
            .append("creatTime", getCreatTime())
            .toString();
    }
}
