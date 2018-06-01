package com.inschos.cloud.message.handing.access.rpc.service.impl;

import com.inschos.cloud.message.handing.access.rpc.service.SmsSendService;
import com.inschos.cloud.message.handing.assist.kit.TimeKit;
import com.inschos.cloud.message.handing.data.dao.MsgSmsRecordDao;
import com.inschos.cloud.message.handing.data.dao.MsgSmsTemplateDao;
import com.inschos.cloud.message.handing.model.MsgSmsRecord;
import com.inschos.cloud.message.handing.model.MsgSmsTemplate;
import com.inschos.cloud.message.handing.remoting.aliyun.sms.AliyunSmsRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IceAnt on 2018/5/4.
 */
@Service
public class SmsSendServiceImpl implements SmsSendService {

    @Autowired
    private MsgSmsRecordDao msgSmsRecordDao;
    @Autowired
    private MsgSmsTemplateDao msgSmsTemplateDao;
    @Autowired
    private AliyunSmsRemote aliyunSmsRemote;

    @Override
    public boolean sendVerifyCode(String fromCode,String phone, String code) {
        MsgSmsTemplate template = msgSmsTemplateDao.findOneByTmpCode(MsgSmsTemplate.T_TEMPLATE_ALIYUN_VERIFY_CODE);
        int result = 0;
        if(template!=null){
            MsgSmsRecord smsRecord = new MsgSmsRecord();
            smsRecord.created_at = smsRecord.updated_at = TimeKit.currentTimeMillis();
            smsRecord.source_code = fromCode;
            smsRecord.send_type = MsgSmsRecord.SEND_TYPE_NOW;
            smsRecord.send_phone = phone;
            smsRecord.send_content = "{\"code\":\""+code+"\"}";
            smsRecord.content = template.getContent(smsRecord.send_content);
            smsRecord.tmp_code = template.tmp_code;
            smsRecord.status = MsgSmsRecord.STATUS_WAITING;
            result = msgSmsRecordDao.add(smsRecord);
            if(result>0){
                aliyunSmsRemote.sendSms(smsRecord.id,smsRecord.send_content,phone);
            }
        }
        return result>0;
    }

    @Override
    public boolean sendAgentInvite(String fromCode,String phone, String name, String code) {

        MsgSmsTemplate template = msgSmsTemplateDao.findOneByTmpCode(MsgSmsTemplate.T_TEMPLATE_ALIYUN_AGENT_INVITE_CODE);
        int result = 0;
        if(template!=null){
            MsgSmsRecord smsRecord = new MsgSmsRecord();
            smsRecord.created_at = smsRecord.updated_at = TimeKit.currentTimeMillis();
            smsRecord.source_code = fromCode;
            smsRecord.send_type = MsgSmsRecord.SEND_TYPE_NOW;
            smsRecord.send_phone = phone;
            smsRecord.send_content = "{\"name\":\""+name+"\",\"code\":\""+code+"\"}";
            smsRecord.content = template.getContent(smsRecord.send_content);
            smsRecord.tmp_code = template.tmp_code;
            smsRecord.status = MsgSmsRecord.STATUS_WAITING;
            result = msgSmsRecordDao.add(smsRecord);
            if(result>0){
                aliyunSmsRemote.sendSms(smsRecord.id,smsRecord.send_content,phone);
            }
        }
        return result>0;
    }


}
