package com.channel.bank.adapter.pay.domain;

import java.io.Serializable;

public class BaseResult implements Serializable
{
private static final long serialVersionUID = -2253380911043438025L;
protected boolean success = false;
protected String resultMessage;

public BaseResult()
{
}

public BaseResult(boolean success)
{
  this.success = success;
}

public BaseResult(boolean success, String returnMessage) {
  this.success = success;
  this.resultMessage = returnMessage;
}

public boolean isSuccess() {
  return this.success;
}

public void setSuccess(boolean success) {
  this.success = success;
}

public String getResultMessage()
{
  return this.resultMessage;
}

public void setResultMessage(String resultMessage) {
  this.resultMessage = resultMessage;
}
}