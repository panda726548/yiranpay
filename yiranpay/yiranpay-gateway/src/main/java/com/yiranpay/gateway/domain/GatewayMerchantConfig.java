package com.yiranpay.gateway.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiranpay.common.annotation.Excel;
import com.yiranpay.common.core.domain.BaseEntity;

/**
 * 商户接口配置对象 gateway_merchant_config
 * 
 * @author panda
 * @date 2020-01-06
 */
public class GatewayMerchantConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** apiID */
    private Long id;

    /** 商户编号 */
    @Excel(name = "商户编号")
    private String merchantId;

    /** 可使用API接口 */
    @Excel(name = "可使用API接口")
    private String availableApi;

    /** 加密方式 */
    @Excel(name = "加密方式")
    private String encryptionType;

    /** 扩展1 */
    @Excel(name = "扩展1")
    private String extend1;

    /** 扩展2 */
    @Excel(name = "扩展2")
    private String extend2;

    /** 扩展3 */
    @Excel(name = "扩展3")
    private String extend3;
    
    /** 接口权限组 */
    private Long[] menuIds;

    public Long[] getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(Long[] menuIds) {
		this.menuIds = menuIds;
	}

	public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMerchantId(String merchantId) 
    {
        this.merchantId = merchantId;
    }

    public String getMerchantId() 
    {
        return merchantId;
    }
    public void setAvailableApi(String availableApi) 
    {
        this.availableApi = availableApi;
    }

    public String getAvailableApi() 
    {
        return availableApi;
    }
    public void setEncryptionType(String encryptionType) 
    {
        this.encryptionType = encryptionType;
    }

    public String getEncryptionType() 
    {
        return encryptionType;
    }
    public void setExtend1(String extend1) 
    {
        this.extend1 = extend1;
    }

    public String getExtend1() 
    {
        return extend1;
    }
    public void setExtend2(String extend2) 
    {
        this.extend2 = extend2;
    }

    public String getExtend2() 
    {
        return extend2;
    }
    public void setExtend3(String extend3) 
    {
        this.extend3 = extend3;
    }

    public String getExtend3() 
    {
        return extend3;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merchantId", getMerchantId())
            .append("availableApi", getAvailableApi())
            .append("encryptionType", getEncryptionType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("extend1", getExtend1())
            .append("extend2", getExtend2())
            .append("extend3", getExtend3())
            .toString();
    }
}
