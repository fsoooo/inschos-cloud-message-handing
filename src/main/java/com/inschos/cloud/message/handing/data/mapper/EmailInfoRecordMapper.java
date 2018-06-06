package com.inschos.cloud.message.handing.data.mapper;

import com.inschos.cloud.message.handing.model.EmailInfoListRecord;

public interface EmailInfoRecordMapper {

    //邮件信息入库
    int insert(EmailInfoListRecord emailInfoListRecord);

    int update(EmailInfoListRecord emailInfoListRecord);

    EmailInfoListRecord findOne(int mail_id);

}
