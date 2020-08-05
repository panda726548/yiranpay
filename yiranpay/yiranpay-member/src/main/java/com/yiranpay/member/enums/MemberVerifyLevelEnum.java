
package com.yiranpay.member.enums;

/**
 * <p>
 * 会员认证等级
 * </p>
 * 
 */
public enum MemberVerifyLevelEnum {

    NOT_VERIFY(0, "未认证"), REALNAME_VERIFY(1, "实名校验"), REALNAME_VERIFY_V1(2, "实名认证V1"), REALNAME_VERIFY_V2(
            3, "实名认证V2");
    
    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static MemberVerifyLevelEnum getByCode(long code) {
        for (MemberVerifyLevelEnum item : MemberVerifyLevelEnum.values()) {
            if (item.code == code ) {
                return item;
            }
        }
        return null;
    }

    private MemberVerifyLevelEnum(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final long    code;

    private final String msg;

    /**
     * @return the code
     */
    public long getCode() {
        return code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

}
