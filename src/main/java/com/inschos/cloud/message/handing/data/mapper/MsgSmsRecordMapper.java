package com.inschos.cloud.message.handing.data.mapper;

import com.inschos.cloud.message.handing.model.MsgSmsRecord;

/**
 * Created by IceAnt on 2018/5/4.
 */
public interface MsgSmsRecordMapper {

    int insert(MsgSmsRecord record);

    int update(MsgSmsRecord record);

    MsgSmsRecord findOne(long id);
}
