/**
 * 
 */
package com.yiranpay.member.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.yiranpay.common.utils.BaseResult;
import com.yiranpay.member.domain.AccountBalance;

/**
 * <p>账户余额明细查询结果集</p>
 */
public class RemoteAccountBalanceListResult extends BaseResult {

    private static final long serialVersionUID = -1087975856929009580L;

    private Integer           totalCount;                              //总记录数
    private Integer           pageCount;                               //页数
    /**
     * 总收入
     */
    private BigDecimal             totalIncome = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN);
    /**
     * 总支出
     */
    private BigDecimal             totalPayout = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN);

    List<AccountBalance>      balanceList;                             //余额明细

    public RemoteAccountBalanceListResult() {
        super();
    }

    public RemoteAccountBalanceListResult(boolean success) {
        super(success);
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

    public List<AccountBalance> getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(List<AccountBalance> balanceList) {
        this.balanceList = balanceList;
    }

    public void addBalance(AccountBalance balance) {
        if (this.balanceList == null) {
            this.balanceList = new ArrayList<AccountBalance>();
        }
        this.balanceList.add(balance);
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

    
    
}
