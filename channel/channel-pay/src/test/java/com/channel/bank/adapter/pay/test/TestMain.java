package com.channel.bank.adapter.pay.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

public class TestMain {
	private static final String KEY_SPLIT_TAG = "_";
	public static void main(String[] args) {
		String text = "[[\"expireTime\",\"2020-03-11 11:11:11\"],[\"accessChannel\",\"WEB\"],[\"channelName\",\"YLSW\"],[\"productCode\",\"20040001\"],[\"pageUrl\",\"http://192.168.10.60:8095/tpu-web/syncNotify.jsp\"],[\"subject\",\"test001\"],[\"tradeVoucherNos\",\"101158467145459353337\"],[\"partnerId\",\"200001265042\"],[\"DBCR\",\"GC\"],[\"paymentOrderNo\",\"301158467145460453338\"],[\"clientId\",\"payment\"],[\"companyOrPersonal\",\"C\"],[\"payerIp\",\"202.114.12.45\"],[\"gateOrderNo\",\"301158467145460453338\"],[\"payeeId\",\"innerMember\"],[\"memberId\",\"anonymous\"],[\"webDomain\",\"http://192.168.110.206:8080/tpu-web/mag/createInstantPayH5New.jsp\"]]";
		Map<String, String> map = convertFromDb(text);
		System.out.println(map);
		System.out.println(map.get("tradeVoucherNos"));
	}
	
	public static java.util.Map<String, String> convertFromDb(String str) {
        java.util.Map<String, String> extension = new HashMap<String, String>();
        if (StringUtils.isEmpty(str)) {
            return extension;
        }
        String[][] list = JSON.parseObject(str, String[][].class);
        for (int i = 0; i < list.length; i++) {
            extension.put(convertKey(list[i][0]), list[i][1]);
        }
        return extension;
    }
	
	/**
     * 转换字符串
     * 例：
     * AAAA_BBB_CCC-->aaaaBbbCcc
     * aaA_B       -->aaaB
     * aSa_b_C     -->asaBC
     * @param origKey
     * @return
     */
    public static String convertKey(String origKey) {
        if (StringUtils.isBlank(origKey)) {
            return origKey;
        }
        if (!origKey.contains(KEY_SPLIT_TAG)) {
            return origKey;
        }
        String[] wordArray = origKey.trim().toLowerCase().split(KEY_SPLIT_TAG);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordArray.length; i++) {
            String word = wordArray[i];
            if (i == 0) {
                sb.append(word);
            } else {
                sb.append(StringUtils.substring(word, 0, 1).toUpperCase());
                sb.append(StringUtils.substring(word, 1, word.length()));
            }
        }

        return sb.toString();
    }

}
