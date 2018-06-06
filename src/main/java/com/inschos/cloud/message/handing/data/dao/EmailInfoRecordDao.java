package com.inschos.cloud.message.handing.data.dao;

import com.inschos.cloud.message.handing.data.mapper.EmailInfoRecordMapper;
import com.inschos.cloud.message.handing.model.EmailInfoListRecord;
import com.inschos.cloud.message.handing.model.EmailInfoRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailInfoRecordDao {
    @Autowired
    private EmailInfoRecordMapper emailInfoRecordMapper;

    public int addEmailInfo(EmailInfoListRecord emailInfoListRecord){
        return emailInfoRecordMapper.insert(emailInfoListRecord);
    }

    public int updateEmailInfo(EmailInfoListRecord emailInfoListRecord){
        return emailInfoRecordMapper.update(emailInfoListRecord);
    }

    public EmailInfoListRecord findOneMailInfo(int mail_id){
        return emailInfoRecordMapper.findOne(mail_id);
    }

}
