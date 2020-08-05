
package com.yiranpay.reconciliation.parser;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.yiranpay.reconciliation.domain.ReconciliationAccountCheckBatch;
import com.yiranpay.reconciliation.vo.ReconciliationEntityVo;


/**
 * 定义一个解析的接口，实现着必须override接口中的parser方法.
 * 
 */
public interface ParserInterface {

	/**
	 * 
	 * @param file
	 * @param billDate
	 * @param batch
	 * @return
	 * @throws IOException 
	 */
	public List<ReconciliationEntityVo> parser(File file, Date billDate, ReconciliationAccountCheckBatch batch) throws IOException;

}
