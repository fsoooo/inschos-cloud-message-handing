package com.inschos.cloud.message.handing.access.rpc.service.impl;

import com.inschos.cloud.message.handing.access.rpc.service.SmsSendService;
import com.inschos.cloud.message.handing.data.dao.MsgSmsRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IceAnt on 2018/5/4.
 */
@Service
public class SmsSendServiceImpl implements SmsSendService {

    @Autowired
    private MsgSmsRecordDao msgSmsRecordDao;

    @Override
    public void sendVerifyCode(String phone, String code) {

    }
}
