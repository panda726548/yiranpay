package com.yiranpay.member.exception;

/**
 * 业务异常基类，所有业务异常都必须继承于此异常 .
 */
public class MenberSqeException extends RuntimeException {

    private static final long serialVersionUID = -5875371379845226068L;

    /**
     * 数据库操作,insert返回0
     */
    public static final MenberSqeException DB_INSERT_RESULT_0 = new MenberSqeException(
            10040001, "数据库操作,insert返回0");

    /**
     * 数据库操作,update返回0
     */
    public static final MenberSqeException DB_UPDATE_RESULT_0 = new MenberSqeException(
            10040002, "数据库操作,update返回0");

    /**
     * 数据库操作,selectOne返回null
     */
    public static final MenberSqeException DB_SELECTONE_IS_NULL = new MenberSqeException(
            10040003, "数据库操作,selectOne返回null");

    /**
     * 数据库操作,list返回null
     */
    public static final MenberSqeException DB_LIST_IS_NULL = new MenberSqeException(
            10040004, "数据库操作,list返回null");

    /**
     * Token 验证不通过
     */
    public static final MenberSqeException TOKEN_IS_ILLICIT = new MenberSqeException(
            10040005, "Token 验证非法");
    /**
     * 会话超时　获取session时，如果是空，throws 下面这个异常 拦截器会拦截爆会话超时页面
     */
    public static final MenberSqeException SESSION_IS_OUT_TIME = new MenberSqeException(
            10040006, "会话超时");

    /**
     * 生成序列异常时
     */
    public static final MenberSqeException DB_GET_SEQ_NEXT_VALUE_ERROR = new MenberSqeException(
            10040007, "序列生成超时");

    /**
     * 异常信息
     */
    protected String msg;

    /**
     * 具体异常码
     */
    protected int code;

    public MenberSqeException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public MenberSqeException() {
        super();
    }

    public MenberSqeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MenberSqeException(Throwable cause) {
        super(cause);
    }

    public MenberSqeException(String message) {
        super(message);
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    /**
     * 实例化异常
     * 
     * @param msgFormat
     * @param args
     * @return
     */
    public MenberSqeException newInstance(String msgFormat, Object... args) {
        return new MenberSqeException(this.code, msgFormat, args);
    }

}
