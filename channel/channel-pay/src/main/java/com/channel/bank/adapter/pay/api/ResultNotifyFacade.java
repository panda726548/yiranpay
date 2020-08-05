package com.channel.bank.adapter.pay.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.channel.bank.adapter.pay.mock.ResultReturnVO;
/**
 * 模拟处理异步通知接口
 * @author pandaa
 *
 */
@RestController
@RequestMapping("/api/yiranpay/channelpay/notify")
public class ResultNotifyFacade {
	private Logger logger = LoggerFactory.getLogger(ResultNotifyFacade.class);
	
	@PostMapping("/resultNotify")
	public ResultReturnVO returnResult(@RequestBody String data){
		logger.info("模拟订单业务通知ResultNotifyFacade-请求参数："+data);
		
		ResultReturnVO vo = new ResultReturnVO();
		vo.setRet_code("0000");
		vo.setRet_msg("交易成功");
		return vo;
	}
	
	
	
	
}
