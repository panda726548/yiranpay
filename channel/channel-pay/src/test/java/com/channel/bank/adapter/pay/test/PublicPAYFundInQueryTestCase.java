package com.channel.bank.adapter.pay.test;

import java.util.Date;

import org.junit.Test;


public class PublicPAYFundInQueryTestCase extends FundInQueryBaseTestCase {


   @Test
   public void FundInQueryTest(){
	   super.QuerySingle("CHINAPAY70001", "WXPAY", "3194ALIPAY20200307043", new Date());
   }
    
    
}
