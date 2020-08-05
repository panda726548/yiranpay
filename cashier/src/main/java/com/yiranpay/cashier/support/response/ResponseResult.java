package com.yiranpay.cashier.support.response;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -3373681610836130988L;

    private String code;

    private String msg;

    private T data;

    public String getCode() {
        return code;
    }

    public ResponseResult<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    private ResponseResult() {
    }

    public static <T> ResponseResult<T> newInstance(T data, String code, String msg) {
        return new ResponseResult<T>()
                .setData(data)
                .setCode(code)
                .setMsg(msg);
    }

    public static <T> ResponseResult<T> newInstance(String code, String msg) {
        return newInstance(null, code, msg);
    }

    public static <T> ResponseResult<T> ok(T data) {
        return newInstance(data, "200", "成功");
    }

    public static <T> ResponseResult<T> ok() {
        return ok(null);
    }

    public static <T> ResponseResult<T> fail(T data) {
        return newInstance(data, "400", "失败");
    }

    public static <T> ResponseResult<T> fail() {
        return fail(null);
    }

    public static <T> ResponseResult<T> error(T data) {
        return newInstance(data, "500", "异常");
    }

    public static <T> ResponseResult<T> error() {
        return error(null);
    }
}
