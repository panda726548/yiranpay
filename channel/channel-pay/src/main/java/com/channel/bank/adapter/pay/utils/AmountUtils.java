package com.channel.bank.adapter.pay.utils;
/**
 * 金额转换工具类
 * @author xupan
 *
 */
public class AmountUtils {
	
	public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";   
	
	/**
	 * 元转分
	 * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
	 * 
	 * @param amount
	 * @return
	 */
	public static String Yuan2Fen(String amount) {
		String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥
																// 或者$的金额
		int index = currency.indexOf(".");
		int length = currency.length();
		Long amLong = 0l;
		if (index == -1) {
			amLong = Long.valueOf(currency + "00");
		} else if (length - index >= 3) {
			amLong = Long.valueOf((currency.substring(0, index + 3)).replace(
					".", ""));
		} else if (length - index == 2) {
			amLong = Long.valueOf((currency.substring(0, index + 2)).replace(
					".", "") + 0);
		} else {
			amLong = Long.valueOf((currency.substring(0, index + 1)).replace(
					".", "") + "00");
		}
		return amLong.toString();
	}
	
	/**   
     * 将分为单位的转换为元并返回金额格式的字符串 （除100）  
     *   
     * @param amount  
     * @return  
     * @throws Exception   
     */    
    public static String Fen2Yuan(Long amount) throws Exception{    
        if(!amount.toString().matches(CURRENCY_FEN_REGEX)) {    
            throw new Exception("金额格式有误");    
        }    
            
        int flag = 0;    
        String amString = amount.toString();    
        if(amString.charAt(0)=='-'){    
            flag = 1;    
            amString = amString.substring(1);    
        }    
        StringBuffer result = new StringBuffer();    
        if(amString.length()==1){    
            result.append("0.0").append(amString);    
        }else if(amString.length() == 2){    
            result.append("0.").append(amString);    
        }else{    
            String intString = amString.substring(0,amString.length()-2);    
            for(int i=1; i<=intString.length();i++){    
                result.append(intString.substring(intString.length()-i,intString.length()-i+1));    
            }    
            result.reverse().append(".").append(amString.substring(amString.length()-2));    
        }    
        if(flag == 1){    
            return "-"+result.toString();    
        }else{    
            return result.toString();    
        }    
    }    


}
