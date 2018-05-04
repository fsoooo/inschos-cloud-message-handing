package com.inschos.cloud.message.handing.data.dao;

import com.inschos.cloud.message.handing.data.mapper.MsgSmsSendRecordMapper;
import com.inschos.cloud.message.handing.model.MsgSmsSendRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MsgSmsSendRecordDao {
    @Autowired
    private MsgSmsSendRecordMapper msgSmsSendRecordMapper;

    public int add(MsgSmsSendRecord record){
        return record!=null?msgSmsSendRecordMapper.insert(record):0;
    }
}
