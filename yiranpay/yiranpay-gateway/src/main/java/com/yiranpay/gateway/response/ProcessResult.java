package com.yiranpay.gateway.response;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiranpay.gateway.exception.ErrorCodeException;

/**
 *
 * <p>服务处理结果</p>
 */
public class ProcessResult {

    // 是否成功
    private boolean    result           = true;
    // 是否跳收银台
    private boolean    isForwardCashier = false;

    private String     cashierUrl;

    private String     netBankFormUrl;
    private String     formCharset;

    ErrorCodeException exception;
    private String     memo;

    private String     partner_id;
    private String     sign_type;
    private String     outer_trade_no;
    private String     inner_trade_no;
    private String     pos_pay_no;
    private String     instPayInfo;
    private String     trade_status;
    private String 	   amount;
    
    private String     tradeList;
    private String	   payable_amount;
    private String	   submit_paytime;


    //代充查询
    private String     payeeId;
    private String     payeeIdType;
    private String     pageNo;
    private String     pageSize;
    private String     totalItem;
    private String     detailList;
    private String     encoded_img;
    private String     batch_no;
    private String     inner_pay_no;
    private String     pay_status;

    private String 	   settled_data;
    private String     page_size;
    private String     current_page;
    private String     total_items;
    private String     total_pages;
    private String     has_next_page;
	//授权绑定信息
    private String     plat_user_id;
    private String     plat_user;
    private String     member_id;
    private String     member_name;
    private String     accredit_flg;
    
    //验证身份信息
    private String     real_name;
    private String     identitycard;
    private String     validateresult;
    
    //账户查询信息
    private String     balance;
    private String     availablebalance;
    private String     frozenbalance;
    //登帐返回信息
    private String    result_code;
    private String    result_msg;
    //充值记录查询返回信息
    private String    recharge_data;
    //机构订单号
    private String    inst_pay_no;
    //卡Bin
    private String card_no;
	private String buss_type;
    private String instcode;
    private String card_type;
    private String is_access;
    private String card_name;
    //获取银行卡
    private Number nubmers;
    private String banks;
    //银行卡签约
    private String user_id;
    private String sign_code;
    //private String card_no;
    private String paybill;
    private String codesender;
    private String sign_status;
    private String needAdvance;
    private String sign_msg;
    

    
    private String bank_code;
    
    private String extension;
    private String refund_status;
    
    private String bank_name;
    private String cert_type;
    private String cert_no;
    
    private String channel_balance;
    
    private String filename;
    private String batchNo;
    private String notifyurl;
    
    private String result_data;
    private String third_trade_data;

    
    
	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getThird_trade_data() {
		return third_trade_data;
	}

	public void setThird_trade_data(String third_trade_data) {
		this.third_trade_data = third_trade_data;
	}

	public String getResult_data() {
		return result_data;
	}

	public void setResult_data(String result_data) {
		this.result_data = result_data;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getNotifyurl() {
		return notifyurl;
	}

	public void setNotifyurl(String notifyurl) {
		this.notifyurl = notifyurl;
	}

	public String getChannel_balance() {
		return channel_balance;
	}

	public void setChannel_balance(String channel_balance) {
		this.channel_balance = channel_balance;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getCert_type() {
		return cert_type;
	}

	public void setCert_type(String cert_type) {
		this.cert_type = cert_type;
	}

	public String getCert_no() {
		return cert_no;
	}

	public void setCert_no(String cert_no) {
		this.cert_no = cert_no;
	}

	public String getRefund_status() {
		return refund_status;
	}

	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getInst_pay_no() {
		return inst_pay_no;
	}

	public void setInst_pay_no(String inst_pay_no) {
		this.inst_pay_no = inst_pay_no;
	}

	public String getRecharge_data() {
		return recharge_data;
	}

	public void setRecharge_data(String recharge_data) {
		this.recharge_data = recharge_data;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getFormCharset() {
        return formCharset;
    }

    public void setFormCharset(String formCharset) {
        this.formCharset = formCharset;
    }

    public String getResult_msg() {
		return result_msg;
	}

	public void setResult_msg(String result_msg) {
		this.result_msg = result_msg;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getAvailablebalance() {
		return availablebalance;
	}

	public String getPayable_amount() {
		return payable_amount;
	}

	public void setPayable_amount(String payable_amount) {
		this.payable_amount = payable_amount;
	}

	public String getSubmit_paytime() {
		return submit_paytime;
	}

	public void setSubmit_paytime(String submit_paytime) {
		this.submit_paytime = submit_paytime;
	}

	public void setAvailablebalance(String availablebalance) {
		this.availablebalance = availablebalance;
	}

	public String getFrozenbalance() {
		return frozenbalance;
	}

	public void setFrozenbalance(String frozenbalance) {
		this.frozenbalance = frozenbalance;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getIdentitycard() {
		return identitycard;
	}

	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
	}

	public String getValidateresult() {
		return validateresult;
	}

	public void setValidateresult(String validateresult) {
		this.validateresult = validateresult;
	}

	public String getAccredit_flg() {
		return accredit_flg;
	}

	public void setAccredit_flg(String accredit_flg) {
		this.accredit_flg = accredit_flg;
	}

	public String getPlat_user_id() {
		return plat_user_id;
	}

	public void setPlat_user_id(String plat_user_id) {
		this.plat_user_id = plat_user_id;
	}

	public String getPlat_user() {
		return plat_user;
	}

	public void setPlat_user(String plat_user) {
		this.plat_user = plat_user;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

    public String getInner_pay_no() {
        return inner_pay_no;
    }

    public void setInner_pay_no(String inner_pay_no) {
        this.inner_pay_no = inner_pay_no;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getEncoded_img() {
        return encoded_img;
    }

    public void setEncoded_img(String encoded_img) {
        this.encoded_img = encoded_img;
    }

    public String getNetBankFormUrl() {
        return netBankFormUrl;
    }

    public void setNetBankFormUrl(String netBankFormUrl) {
        this.netBankFormUrl = netBankFormUrl;
    }

    public String getPos_pay_no() {
        return pos_pay_no;
    }

    public void setPos_pay_no(String pos_pay_no) {
        this.pos_pay_no = pos_pay_no;
    }

    public String getInstPayInfo() {
        return instPayInfo;
    }

    public void setInstPayInfo(String instPayInfo) {
        this.instPayInfo = instPayInfo;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ErrorCodeException getException() {
        return exception;
    }

    public void setException(ErrorCodeException exception) {
        this.exception = exception;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public boolean isForwardCashier() {
        return isForwardCashier;
    }

    public void setForwardCashier(boolean isForwardCashier) {
        this.isForwardCashier = isForwardCashier;
    }

    public String getCashierUrl() {
        return cashierUrl;
    }

    public void setCashierUrl(String cashierUrl) {
        this.cashierUrl = cashierUrl;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getOuter_trade_no() {
        return outer_trade_no;
    }

    public void setOuter_trade_no(String outer_trade_no) {
        this.outer_trade_no = outer_trade_no;
    }

    public String getInner_trade_no() {
        return inner_trade_no;
    }

    public void setInner_trade_no(String inner_trade_no) {
        this.inner_trade_no = inner_trade_no;
    }

    public String getTradeList() {
        return tradeList;
    }

    public void setTradeList(String tradeList) {
        this.tradeList = tradeList;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public String getPayeeIdType() {
        return payeeIdType;
    }

    public void setPayeeIdType(String payeeIdType) {
        this.payeeIdType = payeeIdType;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(String totalItem) {
        this.totalItem = totalItem;
    }

    public String getDetailList() {
        return detailList;
    }

    public void setDetailList(String detailList) {
        this.detailList = detailList;
    }




    public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSettled_data() {
		return settled_data;
	}

	public void setSettled_data(String settled_data) {
		this.settled_data = settled_data;
	}

	public String getPage_size() {
		return page_size;
	}

	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}

	public String getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(String current_page) {
		this.current_page = current_page;
	}

	public String getTotal_items() {
		return total_items;
	}

	public void setTotal_items(String total_items) {
		this.total_items = total_items;
	}

	public String getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(String total_pages) {
		this.total_pages = total_pages;
	}

	public String getHas_next_page() {
		return has_next_page;
	}

	public void setHas_next_page(String has_next_page) {
		this.has_next_page = has_next_page;
	}

	  public String getCard_no() {
			return card_no;
		}

		public void setCard_no(String card_no) {
			this.card_no = card_no;
		}

		public String getBuss_type() {
			return buss_type;
		}

		public void setBuss_type(String buss_type) {
			this.buss_type = buss_type;
		}

		public String getInstcode() {
			return instcode;
		}

		public void setInstcode(String instcode) {
			this.instcode = instcode;
		}

		public String getCard_type() {
			return card_type;
		}

		public void setCard_type(String card_type) {
			this.card_type = card_type;
		}

		public String getIs_access() {
			return is_access;
		}

		public void setIs_access(String is_access) {
			this.is_access = is_access;
		}
		
		
	
	public Number getNubmers() {
			return nubmers;
		}

		public void setNubmers(Number nubmers) {
			this.nubmers = nubmers;
		}

		public String getBanks() {
			return banks;
		}

		public void setBanks(String banks) {
			this.banks = banks;
		}

		
		
		
		
	public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}

		public String getSign_code() {
			return sign_code;
		}

		public void setSign_code(String sign_code) {
			this.sign_code = sign_code;
		}

		public String getPaybill() {
			return paybill;
		}

		public void setPaybill(String paybill) {
			this.paybill = paybill;
		}

		public String getCodesender() {
			return codesender;
		}

		public void setCodesender(String codesender) {
			this.codesender = codesender;
		}

	public void addInstPayInfo(String payMethod, String instPayNo) {
        if (StringUtils.isBlank(instPayNo)) {
            return;
        }
        String append = StringUtils.join(new String[] { payMethod, instPayNo }, "^");
        if (StringUtils.isBlank(this.instPayInfo)) {
            this.instPayInfo = append;
        } else {
            this.instPayInfo = this.instPayInfo + "|" + append;
        }
    }

	public String getSign_status() {
		return sign_status;
	}

	public void setSign_status(String sign_status) {
		this.sign_status = sign_status;
	}



	public String getSign_msg() {
		return sign_msg;
	}

	public void setSign_msg(String sign_msg) {
		this.sign_msg = sign_msg;
	}

	public String getNeedAdvance() {
		return needAdvance;
	}

	public void setNeedAdvance(String needAdvance) {
		this.needAdvance = needAdvance;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
	
}
