package com.inschos.cloud.message.handing.access.rpc.service;

/**
 * Created by IceAnt on 2018/5/4.
 */

public interface SmsSendService {

    boolean sendVerifyCode(String fromCode,String phone, String code);

}
