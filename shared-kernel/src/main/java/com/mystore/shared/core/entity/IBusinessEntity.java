package com.mystore.shared.core.entity;

import com.mystore.shared.core.exception.ValidationBusinessException;

public interface IBusinessEntity {
    IBusinessEntity validate() throws ValidationBusinessException;

}
