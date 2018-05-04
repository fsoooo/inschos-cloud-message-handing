package com.inschos.cloud.message.handing.data.mapper;

import com.inschos.cloud.message.handing.model.MsgSmsSendRecord;
import com.inschos.cloud.message.handing.model.MsgSmsTemplate;

/**
 * Created by IceAnt on 2018/5/4.
 */
public interface MsgSmsTemplateMapper {

    MsgSmsTemplate findOneByTmpCode(String tmpCode);
}
