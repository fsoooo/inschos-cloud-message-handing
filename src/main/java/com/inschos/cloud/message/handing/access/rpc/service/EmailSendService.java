package com.inschos.cloud.message.handing.access.rpc.service;

import com.inschos.cloud.message.handing.access.rpc.bean.EmailInfoBean;

public interface EmailSendService {

    int addEmailInfo(EmailInfoBean emailInfoBean);

    int sendEmailStatus(int mail_id);
}
