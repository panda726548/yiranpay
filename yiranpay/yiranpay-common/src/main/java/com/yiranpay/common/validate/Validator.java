package com.yiranpay.common.validate;

import com.yiranpay.common.exception.ValidationException;

public abstract interface Validator
{
  public abstract void validate(Object paramObject)
    throws ValidationException;
}
