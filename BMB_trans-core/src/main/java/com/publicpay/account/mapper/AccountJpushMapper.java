package com.publicpay.account.mapper;

import com.publicpay.account.beans.bo.AccountJpush;

public interface AccountJpushMapper {
    int insert(AccountJpush record);

    int insertSelective(AccountJpush record);
}