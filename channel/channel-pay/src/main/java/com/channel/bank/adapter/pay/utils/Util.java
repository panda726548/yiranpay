package com.channel.bank.adapter.pay.utils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class Util {

    public static String makeOrderRequest(Map<String, String> params, String md5Key, String apiUrl) {
        params.put("sign", makeSign(md5Key, params));
        return apiUrl + "?" + buildUrlParametersStr(params);
    }


    public static String makeSign(String md5Key, Map<String, String> params) {
        String preStr = buildSignString(params); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        System.out.println("代签串："+preStr);
        String text = preStr + md5Key;
        
        return DigestUtils.md5Hex(getContentBytes(text)).toUpperCase();
    }

    public static boolean checkSign(String md5Key, Map<String, String> params) {
        String sign = params.get("sign");

        if (StringUtils.isBlank(sign)) {
            return false;
        }

        String signV = makeSign(md5Key, params);

        return StringUtils.equalsIgnoreCase(sign, signV);
    }

    // 获取HttpServletRequest里面的参数，并decode
    public static Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        Map<String, String> params2 = new HashMap<String, String>();
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            if (values.length > 0) {
                params2.put(key, values[0]);
            }
        }
        return params2;
    }

    public static String genMerOrderId(String msgId) {
        String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
        String rand = RandomStringUtils.randomNumeric(7);
        return msgId + date + rand;
    }

    public static String buildUrlParametersStr(Map<String, String> paramMap) {
        Map.Entry entry;
        StringBuffer buffer = new StringBuffer();

        for (Iterator iterator = paramMap.entrySet().iterator(); iterator.hasNext(); ) {
            entry = (Map.Entry) iterator.next();

            buffer.append(entry.getKey().toString()).append("=");
            try {
                if (entry.getValue() != null && StringUtils.isNotBlank(entry.getValue().toString())) {
                    buffer.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
            }

            buffer.append(iterator.hasNext() ? "&" : "");
        }

        return buffer.toString();
    }


    // 构建签名字符串
    private static String buildSignString(Map<String, String> params) {

        if (params == null || params.size() == 0) {
            return "";
        }

        List<String> keys = new ArrayList<String>(params.size());

        for (String key : params.keySet()) {
            if ("sign".equals(key))
                continue;
            if (StringUtils.isEmpty(String.valueOf(params.get(key))))
            //if (StringUtils.isEmpty(params.get(key)))
                continue;
            keys.add(key);
        }

        Collections.sort(keys);

        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = String.valueOf(params.get(key));
            //String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                buf.append(key + "=" + value);
            } else {
                buf.append(key + "=" + value + "&");
            }
        }

        return buf.toString();
    }

    // 根据编码类型获得签名内容byte[]
    private static byte[] getContentBytes(String content) {
        try {
            return content.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("签名过程中出现错误");
        }
    }

}
