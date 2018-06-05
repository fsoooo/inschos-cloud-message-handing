package com.inschos.cloud.message.handing.remoting.mail;

import com.inschos.cloud.message.handing.model.EmailInfoListRecord;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by IceAnt on 2018/6/5.
 */
@Component
public class EmailRemote {

    @Async("mailExecutor")
    public void triggerSend(EmailInfoListRecord record){
        // TODO: 2018/6/5

    }
}
