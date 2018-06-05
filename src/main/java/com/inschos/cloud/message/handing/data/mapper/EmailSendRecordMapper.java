package com.inschos.cloud.message.handing.data.mapper;

import com.inschos.cloud.message.handing.model.EmailSendRecord;

public interface EmailSendRecordMapper {

    //添加发送记录
    int insert(EmailSendRecord emailSendRecord);

    int update(EmailSendRecord emailSendRecord);
}
