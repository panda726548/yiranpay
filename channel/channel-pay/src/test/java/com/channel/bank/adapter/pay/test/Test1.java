package com.channel.bank.adapter.pay.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import com.channel.bank.adapter.pay.utils.MapUtils;

public class Test1 {

	public static void main(String[] args) throws UnsupportedEncodingException {
		//String orderNo = "20200403110025";
		//System.out.println(orderNo.substring(0, 8));
		/*String data ="XY=jlBn&payTime=2020-04-05+13%3A12%3A10&connectSys=OPENCHANNEL&sign=86690A2BCD7EBD85755D549CADF2FCD4&merName=%E6%B5%8B%E8%AF%95%E9%80%80%E8%B4%A7555&mid=898340149000005&invoiceAmount=1&settleDate=2020-04-05&billFunds=%E7%8E%B0%E9%87%91%E6%94%AF%E4%BB%980.01%E5%85%83%E3%80%82&buyerId=o8wNP0dvkzq9VknQYRdSLMt__ZwQ&mchntUuid=4aa8728a06a04f7385869df8b659cd01&tid=88880001&instMid=YUEDANDEFAULT&receiptAmount=1&targetOrderId=4200000567202004051336400611&orderDesc=%E6%B5%8B%E8%AF%95%E9%80%80%E8%B4%A7555&seqId=00701503005N&merOrderId=3194ALIPAY20200307046&targetSys=WXPay&totalAmount=1&createTime=2020-04-05+13%3A12%3A04&buyerPayAmount=1&notifyId=8ff3e39c-7bd5-4d75-93b5-d7b69d27563c&subInst=000100&status=TRADE_SUCCESS";
		String keyWord = URLDecoder.decode(data, "UTF-8");  
		System.out.println(keyWord);
		Map<String, String> map = MapUtils.getMapforUrl(keyWord);
		System.out.println(map.get("status"));*/
	}
}
