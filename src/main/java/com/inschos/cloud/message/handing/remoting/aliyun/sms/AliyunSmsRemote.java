package com.inschos.cloud.message.handing.remoting.aliyun.sms;

import com.inschos.cloud.message.handing.extend.aliyun.sms.AliyunSms;
import com.inschos.cloud.message.handing.model.MsgSmsTemplate;
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



    private final String T_VERIFY_CODE = "SMS_133964015";

    private AliyunSms aliyunSms;

    private AliyunSms getClient(){
        if(aliyunSms==null){
            aliyunSms = new AliyunSms(accessKey,secretKey);
        }
        return aliyunSms;
    }
    
    @Async
    public void sendSms(MsgSmsTemplate template, String msg, String phone){
        boolean flag = false;
        if(template!=null){
            flag = aliyunSms.sendSms(template.sign_name,template.tmp_code,msg,null,phone);
        }
        if(flag){
            // TODO: 2018/5/4 成功 
        }
    }



}
