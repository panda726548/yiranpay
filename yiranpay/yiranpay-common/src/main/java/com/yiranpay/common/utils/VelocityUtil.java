package com.yiranpay.common.utils;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class VelocityUtil {
	private static VelocityEngine ve = null;

	  private static VelocityEngine loadEngine()
	  {
	    if (ve != null) {
	      return ve;
	    }
	    synchronized (VelocityUtil.class) {
	      if (ve != null) {
	        return ve;
	      }

	      VelocityEngine tempVe = new VelocityEngine();
	      try {
	        tempVe.init();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }

	      ve = tempVe;
	    }

	    return ve;
	  }

	  public static String getString(String templateContent, Map<String, Object> paramMap)
	    throws Exception
	  {
	    if (StrUtils.isBlank(templateContent)) {
	      return null;
	    }

	    VelocityContext context = new VelocityContext();
	    if ((paramMap != null) && (paramMap.size() > 0)) {
	      for (Map.Entry entry : paramMap.entrySet()) {
	        context.put((String)entry.getKey(), entry.getValue());
	      }
	    }

	    StringWriter writer = new StringWriter();
	    try {
	      loadEngine().evaluate(context, writer, "velocity", templateContent);

	      return writer.toString();
	    } catch (Exception e) {
	      throw new Exception("表达式[" + templateContent + "]不符合规范", e);
	    }
	  }

	  public static String mergeString(String templateContent, Map<String, Object> paramMap)
	    throws Exception
	  {
	    return getString(wrapExecuteContent(templateContent), paramMap);
	  }

	  public static String executeString(String templateContent, Map<String, Object> paramMap)
	    throws Exception
	  {
	    if (StrUtils.contains(templateContent, "#if")) {
	      return getString(templateContent, paramMap);
	    }
	    return mergeString(templateContent, paramMap);
	  }

	  public static boolean isTrue(String templateContent, Map<String, Object> paramMap)
	    throws Exception
	  {
	    return Boolean.valueOf(StrUtils.trim(executeString(templateContent, paramMap))).booleanValue();
	  }

	  

	  public static String warpVariable(String orgiValue)
	  {
	    return "${" + orgiValue + "}";
	  }

	  private static String wrapExecuteContent(String orgiContent)
	  {
	    return "#set($temp=" + orgiContent + ")$temp";
	  }

	  public static void main(String[] args) {
	    try {
	      String test = "#if(${test1}>2) true #else false #end";
	      Map paramMap = new HashMap();
	      paramMap.put("test1", Integer.valueOf(3));
	      System.out.println(executeString(test, paramMap));
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
}
