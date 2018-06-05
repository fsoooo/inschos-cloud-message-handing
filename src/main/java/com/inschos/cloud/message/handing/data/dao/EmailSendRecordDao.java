package com.inschos.cloud.message.handing.data.dao;

import com.inschos.cloud.message.handing.data.mapper.EmailSendRecordMapper;
import com.inschos.cloud.message.handing.model.EmailSendRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailSendRecordDao {
    @Autowired
    private EmailSendRecordMapper emailSendRecordMapper;

    public int addSendRecord(EmailSendRecord emailSendRecord){
        return emailSendRecordMapper.insert(emailSendRecord);
    }
}
