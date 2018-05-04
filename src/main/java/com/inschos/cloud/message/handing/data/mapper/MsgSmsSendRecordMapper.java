package com.inschos.cloud.message.handing.data.mapper;

import com.inschos.cloud.message.handing.model.MsgSmsSendRecord;

/**
 * Created by IceAnt on 2018/5/4.
 */
public interface MsgSmsSendRecordMapper {

    int insert(MsgSmsSendRecord record);

    int update(MsgSmsSendRecord record);

    MsgSmsSendRecord findOne(long id);
}
