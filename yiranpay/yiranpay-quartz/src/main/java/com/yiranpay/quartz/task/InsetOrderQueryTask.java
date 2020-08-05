package com.yiranpay.quartz.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yiranpay.payorder.task.InstOrderQueryTask;

@Component("insetOrderQueryTask")
public class InsetOrderQueryTask {
	@Autowired
	private InstOrderQueryTask instOrderQueryTask;
	
	public void queryTask(){
		instOrderQueryTask.run();
	}
}
