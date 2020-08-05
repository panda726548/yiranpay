package com.yiranpay.common.utils;

import java.io.Serializable;
import java.math.BigDecimal;

public class QueryBase
	  implements Serializable
	{
	  private static final long serialVersionUID = 8462451875633214573L;
	  private static final Integer defaultPageSize = new Integer(20);
	  private static final Integer defaultFriatPage = new Integer(1);
	  private static final Integer defaultTotleItem = new Integer(0);
	  private Integer totalItem;
	  private Integer pageSize;
	  private Integer currentPage;
	  private BigDecimal totalSum = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN);
	  private int startRow;
	  private int endRow;
	  private boolean needQeryTotal = false;

	  private boolean needDelete = false;

	  private boolean needQueryAll = false;

	  
	  public BigDecimal getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(BigDecimal totalSum) {
		this.totalSum = totalSum;
	}

	public boolean isAllowedQuery()
	  {
	    throw new RuntimeException("Please implement it in sub classes.");
	  }

	  protected final Integer getDefaultPageSize()
	  {
	    return defaultPageSize;
	  }

	  public Integer getCurrentPage()
	  {
	    if (this.currentPage == null) {
	      return defaultFriatPage;
	    }

	    return this.currentPage;
	  }

	  public void setCurrentPage(Integer cPage)
	  {
	    if ((cPage == null) || (cPage.intValue() <= 0))
	      this.currentPage = defaultFriatPage;
	    else
	      this.currentPage = cPage;
	  }

	  public Integer getPageSize()
	  {
	    if (this.pageSize == null) {
	      return getDefaultPageSize();
	    }

	    return this.pageSize;
	  }

	  public boolean hasSetPageSize()
	  {
	    return this.pageSize != null;
	  }

	  public void setPageSize(Integer pSize)
	  {
	    if (pSize == null) {
	      throw new IllegalArgumentException("PageSize can't be null.");
	    }

	    if (pSize.intValue() <= 0) {
	      throw new IllegalArgumentException("PageSize must great than zero.");
	    }

	    this.pageSize = pSize;
	  }

	  public Integer getTotalItem()
	  {
	    if (this.totalItem == null) {
	      return defaultTotleItem;
	    }

	    return this.totalItem;
	  }

	  public void setTotalItem(Integer tItem)
	  {
	    if (tItem == null) {
	      tItem = new Integer(0);
	    }

	    this.totalItem = tItem;
	  }

	  public int getTotalPage()
	  {
	    int pgSize = getPageSize().intValue();
	    int total = getTotalItem().intValue();
	    int result = total / pgSize;

	    if (total % pgSize != 0) {
	      result++;
	    }

	    return result;
	  }

	  public int getPageFristItem()
	  {
	    if (!this.needQeryTotal) {
	      int cPage = getCurrentPage().intValue();

	      if (cPage == 1) {
	        return 1;
	      }

	      cPage--;

	      int pgSize = getPageSize().intValue();

	      return pgSize * cPage + 1;
	    }
	    return 0;
	  }

	  public int getPageLastItem()
	  {
	    if (!this.needQeryTotal) {
	      int cPage = getCurrentPage().intValue();
	      int pgSize = getPageSize().intValue();
	      int assumeLast = pgSize * cPage;
	      int totalItem = getTotalItem().intValue();

	      if (assumeLast > totalItem) {
	        return totalItem;
	      }
	      return assumeLast;
	    }

	    return getTotalItem().intValue();
	  }

	  public int getEndRow()
	  {
	    return this.endRow;
	  }

	  public void setEndRow(int endRow)
	  {
	    this.endRow = endRow;
	  }

	  public int getStartRow()
	  {
	    return this.startRow;
	  }

	  public void setStartRow(int startRow)
	  {
	    this.startRow = startRow;
	  }

	  protected String getSQLBlurValue(String value)
	  {
	    if (value == null) {
	      return null;
	    }

	    return value + '%';
	  }

	  public boolean isNeedQeryTotal() {
	    return this.needQeryTotal;
	  }

	  public void setNeedQeryTotal(boolean needQeryTotal) {
	    this.needQeryTotal = needQeryTotal;
	  }

	  public boolean isNeedDelete() {
	    return this.needDelete;
	  }

	  public void setNeedDelete(boolean needDelete) {
	    this.needDelete = needDelete;
	  }

	  public boolean isNeedQueryAll() {
	    return this.needQueryAll;
	  }

	  public void setNeedQueryAll(boolean needQueryAll) {
	    this.needQueryAll = needQueryAll;
	  }

	  public <K extends QueryBase> void copyProperties(K k)
	  {
	    if (k == null) {
	      return;
	    }
	    k.setCurrentPage(this.currentPage);
	    k.setEndRow(this.endRow);
	    k.setNeedDelete(this.needDelete);
	    k.setNeedQeryTotal(this.needQeryTotal);
	    k.setNeedQueryAll(this.needQueryAll);

	    k.setStartRow(this.startRow);
	    k.setTotalItem(this.totalItem);
	    
	  }
}