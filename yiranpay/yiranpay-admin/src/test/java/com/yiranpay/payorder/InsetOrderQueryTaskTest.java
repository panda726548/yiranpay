package com.yiranpay.payorder;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yiranpay.base.BaseJunit;
import com.yiranpay.payorder.task.InstOrderQueryTask;

public class InsetOrderQueryTaskTest extends BaseJunit{

	@Autowired
	private InstOrderQueryTask instOrderQueryTask;
	@Test
	public void queryTaskTest(){
		instOrderQueryTask.run();
	}
}
