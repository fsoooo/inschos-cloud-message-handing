package com.inschos.cloud.message.handing.data.mapper;

import com.inschos.cloud.message.handing.model.EmailInfoListRecord;
import com.inschos.cloud.message.handing.model.EmailInfoRecord;

public interface EmailInfoRecordMapper {

    //邮件信息入库
    int insert(EmailInfoListRecord emailInfoListRecord);

    int update(EmailInfoListRecord emailInfoListRecord);

}
