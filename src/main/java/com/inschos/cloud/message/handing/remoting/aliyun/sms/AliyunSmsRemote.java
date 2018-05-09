package com.inschos.cloud.message.handing.remoting.aliyun.sms;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.inschos.cloud.message.handing.assist.kit.L;
import com.inschos.cloud.message.handing.assist.kit.StringKit;
import com.inschos.cloud.message.handing.assist.kit.TimeKit;
import com.inschos.cloud.message.handing.data.dao.MsgSmsRecordDao;
import com.inschos.cloud.message.handing.data.dao.MsgSmsSendRecordDao;
import com.inschos.cloud.message.handing.data.dao.MsgSmsTemplateDao;
import com.inschos.cloud.message.handing.extend.aliyun.sms.AliyunSms;
import com.inschos.cloud.message.handing.model.MsgSmsRecord;
import com.inschos.cloud.message.handing.model.MsgSmsSendRecord;
import com.inschos.cloud.message.handing.model.MsgSmsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by IceAnt on 2018/4/16.
 */
@Component
public class AliyunSmsRemote {

    @Value("${sms.aliyun.access_key}")
    private String accessKey;
    @Value("${sms.aliyun.access_secret}")
    private String secretKey;

    @Autowired
    private MsgSmsRecordDao msgSmsRecordDao;
    @Autowired
    private MsgSmsTemplateDao msgSmsTemplateDao;
    @Autowired
    private MsgSmsSendRecordDao msgSmsSendRecordDao;


    private AliyunSms aliyunSms;

    private AliyunSms getClient(){
        if(aliyunSms==null){
            aliyunSms = new AliyunSms(accessKey,secretKey);
        }
        return aliyunSms;
    }
    
    @Async
    public void sendSms(long smsId, String msg, String phone){
        MsgSmsRecord smsRecord = msgSmsRecordDao.findOne(smsId);
        if(smsRecord!=null){
            MsgSmsTemplate template = msgSmsTemplateDao.findOneByTmpCode(smsRecord.tmp_code);
            if(template!=null){
                MsgSmsSendRecord smsSendRecord = new MsgSmsSendRecord();
                smsSendRecord.created_at = TimeKit.currentTimeMillis();
                SendSmsResponse response = getClient().sendSms(template.sign_name, template.tmp_code, msg, null, phone);
                if(response!=null && response.getCode() != null){
                    if(response.getCode().equals("OK")){
                        smsSendRecord.send_status = MsgSmsRecord.STATUS_BE_SENT;
                        smsSendRecord.describe = "发送成功";
                    }else{
                        smsSendRecord.send_status = MsgSmsRecord.STATUS_FAILED;
                        String resMsg = response.getMessage();
                        L.log.error("send message failed: code={} | message={} ",response.getCode(), resMsg);
                        smsSendRecord.describe = StringKit.isEmpty(resMsg)?"发送失败":resMsg;
                    }
                }
                smsSendRecord.updated_at = TimeKit.currentTimeMillis();
                smsSendRecord.sms_id = smsId;
                int result = msgSmsSendRecordDao.add(smsSendRecord);

                smsRecord.updated_at = TimeKit.currentTimeMillis();
                smsRecord.status = smsSendRecord.send_status;
                msgSmsRecordDao.update(smsRecord);
            }
        }

    }

}
