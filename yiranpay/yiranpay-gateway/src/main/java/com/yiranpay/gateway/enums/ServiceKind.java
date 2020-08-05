package com.yiranpay.gateway.enums;

public enum ServiceKind {

    create_instant_trade("create_instant_trade"),
    create_instant_trade_large("create_instant_trade_large"),
    create_instant_pay("create_instant_pay"),
    create_fund_pay("create_fund_pay"),
    create_instant_pay_mobile("create_instant_pay_mobile"),
    create_ensure_trade("create_ensure_trade"),
    create_settle("create_settle"),
    create_pay("create_pay"),
    create_mobile_recharge_trade("create_mobile_recharge_trade"),
    operator_substitute_recharge("operator_substitute_recharge"),
    create_refund("create_refund"),
    prepay_pay("prepay_pay"),
    query_trade("query_trade"),
    query_pay("query_pay"),
    create_deposit("create_deposit"),
    change_trade_amount("change_trade_amount"),
    query_operator_recharge("query_operator_recharge"),
    create_withdrawal("create_withdrawal"),
    create_payment_to_card("create_payment_to_card"),
    create_paysalary_to_card("create_paysalary_to_card"),
    create_payment_to_card_auto("create_payment_to_card_auto"),
    query_receipt("query_receipt"),
    create_transfer_to_account("create_transfer_to_account"),
    create_paysalary_to_account("create_paysalary_to_account"),
    create_batch_transfer_to_card("create_batch_transfer_to_card"),
    create_batch_transfer_to_account("create_batch_transfer_to_account"),
    batch_paysalary_to_account("batch_paysalary_to_account"),
    batch_paysalary_to_card("batch_paysalary_to_card"),
    create_batch_refund("create_batch_refund"),
    cancel_trade("cancel_trade"),
    query_settled_data("query_settled_data"),
    query_settled_refund_data("query_settled_refund_data"),
    query_settled_fundout_data("query_settled_fundout_data"),
    query_settled_trans_data("query_settled_trans_data"),
	create_accredit("create_accredit"),
    query_accredit("query_accredit"),
    cert_validate("cert_validate"),
    query_account("query_account"),
    entry_account("entry_account"),
    query_batch("query_batch"),
    query_recharge_record("query_recharge_record"),
    create_bank_withholding("create_bank_withholding"),
    create_bank_withholding_memberid("create_bank_withholding_memberid"),
    create_bank_withholding_batch("create_bank_withholding_batch"),
    query_collection("query_collection"),
    create_ypos_withholding("create_ypos_withholding"),
    create_ypos_refund("create_ypos_refund"),
    query_card_bin("query_card_bin"),
    query_qpay_list("query_qpay_list"),
  
    create_bidding("create_bidding"),
    create_bidding_yd("create_bidding_yd"),
    cancel_bidding_corform("cancel_bidding_corform"),
    search_sigh("search_sigh"),
    create_qpay_pay("create_qpay_pay"),
    create_qpay_pay_yd("create_qpay_pay_yd"),
    create_qpay_confirm("create_qpay_confirm"),
    sms_send("sms_send"),
    bankcard_unbind("bankcard_unbind"),
    pay_plan_change("pay_plan_change"),
    create_batch_pay_quick("create_batch_pay_quick"),
    create_ypos_withholding_batch("create_ypos_withholding_batch"),
    create_instant_financing("create_instant_financing"),
    query_account_balance("query_account_balance"),
    query_pay_bank_info("query_pay_bank_info"),
    query_refund("query_refund"),
    query_channel_balance("query_channel_balance"),
    create_sync_info("create_sync_info"),
    query_batch_withhold("query_batch_withhold"),
    create_authcard("create_authcard"),
    create_jd_recharge("create_jd_recharge"),
    query_jd("query_jd"),
    query_bank_bidding("query_bank_bidding"),
    query_trade_withholding_memberid("query_trade_withholding_memberid"),
    query_third_trade_data("query_third_trade_data"),
    query_batch_withhold_member("query_batch_withhold_member"),
    //ues加密解密
    ues_encryption_decryption("ues_encryption_decryption"),
    china_wx_ali_pay("china_wx_ali_pay"),
    china_wx_ali_query_pay("china_wx_ali_query_pay"),
    weixin_pay("weixin_pay"),
    ali_pay("ali_pay"),
    notify_uploadsucc("notify_uploadsucc");
	
    private String code;

    private ServiceKind(String code) {
        this.code = code;

    }

    public static ServiceKind getByCode(String code) {
        for (ServiceKind ls : ServiceKind.values()) {
            if (ls.code.equalsIgnoreCase(code)) {
                return ls;
            }
        }
        return null;
    }

    public boolean equals(String code) {
        return getCode().equalsIgnoreCase(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
