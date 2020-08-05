package com.yiranpay.common.exception;

public class ValidationException extends Exception
{
  private static final long serialVersionUID = -2902025037137372147L;

  public ValidationException()
  {
  }

  public ValidationException(String message)
  {
    super(message);
  }

  public ValidationException(Throwable cause)
  {
    super(cause);
  }

  public ValidationException(String message, Throwable cause)
  {
    super(message, cause);
  }
}