package com.inschos.cloud.message.handing.data.dao;

import com.inschos.cloud.message.handing.data.mapper.MsgSmsRecordMapper;
import com.inschos.cloud.message.handing.model.MsgSmsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by IceAnt on 2018/5/4.
 */
@Component
public class MsgSmsRecordDao {

    @Autowired
    private MsgSmsRecordMapper msgSmsRecordMapper;

    public int add(MsgSmsRecord record){
        return record!=null?msgSmsRecordMapper.insert(record):0;
    }

    public int update(MsgSmsRecord record){
        return record!=null?msgSmsRecordMapper.update(record):0;
    }
}
