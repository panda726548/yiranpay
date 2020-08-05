package com.channel.bank.adapter.pay.exception;

import com.channel.bank.adapter.pay.enums.BestPayResultEnum;

public class BestPayException extends RuntimeException {

    private Integer code;

    public BestPayException(BestPayResultEnum resultEnum) {
        super(resultEnum.getMsg());
        code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
