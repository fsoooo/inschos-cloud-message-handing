package com.inschos.cloud.message.handing.access.rpc.service;

import com.inschos.cloud.message.handing.access.rpc.bean.AccountBean;

public interface AccountService {
    AccountBean getAccount(String token);

    AccountBean findByUuid(String uuid);
}
