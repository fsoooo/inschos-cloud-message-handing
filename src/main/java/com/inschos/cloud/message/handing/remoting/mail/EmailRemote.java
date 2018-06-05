package com.inschos.cloud.message.handing.remoting.mail;

import com.fasterxml.jackson.core.type.TypeReference;
import com.inschos.cloud.message.handing.assist.kit.JsonKit;
import com.inschos.cloud.message.handing.assist.kit.L;
import com.inschos.cloud.message.handing.data.dao.EmailInfoRecordDao;
import com.inschos.cloud.message.handing.data.dao.EmailSendRecordDao;
import com.inschos.cloud.message.handing.model.EmailInfoListRecord;
import com.inschos.cloud.message.handing.model.EmailInfoRecord;
import com.inschos.cloud.message.handing.model.EmailSendRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.inschos.cloud.message.handing.assist.kit.MailKit;
import javax.mail.MessagingException;
import java.util.List;

/**
 * Created by IceAnt on 2018/6/5.
 */
@Component
public class EmailRemote {
    @Autowired
    EmailInfoRecordDao emailInfoRecordDao;

    @Autowired
    EmailSendRecordDao emailSendRecordDao;

    @Async("mailExecutor")
    public void triggerSend(EmailInfoListRecord record) throws MessagingException {
        //TODO 发送邮件 未实现邮件发送状态返回
        List<String> list = JsonKit.json2Bean(record.to_email, new TypeReference<List<String>>() {});
        if (list != null && !list.isEmpty()) {
            for (String s : list) {
                 MailKit.sendMessage(s, record.title, record.html);
            }
        }

        long date = System.currentTimeMillis();

        //添加发送记录
        EmailSendRecord emailSendRecord = new EmailSendRecord();

        emailSendRecord.mail_id = record.id;
        emailSendRecord.send_status = 3;//发送状态 1 未发送  2发送中  3已发送 4发送失败
        emailSendRecord.rec_msg = "发送成功";
        emailSendRecord.created_at = date;
        emailSendRecord.updated_at = date;

        int log_result = emailSendRecordDao.addSendRecord(emailSendRecord);

        //TODO 修改邮件主表状态
        EmailInfoListRecord emailInfoListRecord = new EmailInfoListRecord();
        emailInfoListRecord.id = record.id;
        emailInfoListRecord.status = 3;
        emailInfoListRecord.updated_at = date;

        int info_result = emailInfoRecordDao.updateEmailInfo(emailInfoListRecord);

    }



















}
