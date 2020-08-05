/**
 * 
 */
package com.yiranpay.member.response;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiranpay.member.base.Response;

/**
 * <p>查询账户余额收支明细响应信息</p>
 */
public class AccBalanceListResponse extends Response {
    private static final long            serialVersionUID = 1921224074862976985L;
    private List<AccountBalanceListResp> balanceListResp;                        //账户收支列表
    private Integer                      totalCount;                             //总记录数
    private Integer                      pageCount;                              //页数
    /**
     * 总收入 只有汇总时才有值
     */
    private BigDecimal                        totalIncome = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN);
    /**
     * 总支出 只有汇总时才有值
     */
    private BigDecimal                        totalPayout = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN);

    public List<AccountBalanceListResp> getBalanceListResp() {
        return balanceListResp;
    }

    public void setBalanceListResp(List<AccountBalanceListResp> balanceListResp) {
        this.balanceListResp = balanceListResp;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    
    public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public BigDecimal getTotalPayout() {
		return totalPayout;
	}

	public void setTotalPayout(BigDecimal totalPayout) {
		this.totalPayout = totalPayout;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
