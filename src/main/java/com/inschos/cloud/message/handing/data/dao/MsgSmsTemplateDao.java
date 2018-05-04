package com.inschos.cloud.message.handing.data.dao;

import com.inschos.cloud.message.handing.data.mapper.MsgSmsTemplateMapper;
import com.inschos.cloud.message.handing.model.MsgSmsRecord;
import com.inschos.cloud.message.handing.model.MsgSmsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by IceAnt on 2018/5/4.
 */
@Component
public class MsgSmsTemplateDao {

    @Autowired
    private MsgSmsTemplateMapper msgSmsTemplateMapper;

    public MsgSmsTemplate findOneByTmpCode(String tmpCode){
        return tmpCode!=null? msgSmsTemplateMapper.findOneByTmpCode(tmpCode):null;
    }


}
