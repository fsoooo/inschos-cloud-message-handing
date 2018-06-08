package com.inschos.cloud.message.handing.access.rpc.service.impl;

import com.inschos.cloud.message.handing.access.http.controller.action.EmailAction;
import com.inschos.cloud.message.handing.access.rpc.bean.EmailInfoBean;
import com.inschos.cloud.message.handing.access.rpc.service.EmailSendService;
import com.inschos.cloud.message.handing.assist.kit.JsonKit;
import com.inschos.cloud.message.handing.assist.kit.StringKit;
import com.inschos.cloud.message.handing.data.dao.EmailInfoRecordDao;
import com.inschos.cloud.message.handing.model.EmailInfoListRecord;
import com.inschos.cloud.message.handing.remoting.mail.EmailRemote;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSendServiceImpl implements EmailSendService {
    private static final Logger logger = Logger.getLogger(EmailAction.class);

    @Autowired
    private EmailInfoRecordDao emailInfoRecordDao;

    @Autowired
    private EmailRemote emailRemote;

    public int addEmailInfo(EmailInfoBean emailInfoBean){
        //获取当前时间戳(毫秒值)
        long date = System.currentTimeMillis();

        //赋值数据
        EmailInfoListRecord emailInfoListRecord = new EmailInfoListRecord();

        emailInfoListRecord.bekongs = emailInfoBean.bekongs;
        emailInfoListRecord.source_code = emailInfoBean.source_code;
        emailInfoListRecord.send_type = emailInfoBean.send_type;        //发送方式 1即时 2定时
        emailInfoListRecord.merge_code = emailInfoBean.merge_code;
        emailInfoListRecord.type = emailInfoBean.type;                  //邮件类型 0消息 1验证 2理赔 99其他
        emailInfoListRecord.status = 1;                           //发送状态 1 未发送  2发送中  3已发送 4发送失败
        emailInfoListRecord.title = emailInfoBean.title;
        emailInfoListRecord.html = emailInfoBean.html;

        if (emailInfoBean.to_email != null &&  !emailInfoBean.to_email.isEmpty()) {
            for (String s : emailInfoBean.to_email) {
                if (!StringKit.isEmail(s)){
                    return 0;
                }
            }
            emailInfoListRecord.to_email = JsonKit.bean2Json(emailInfoBean.to_email);
        } else {
            return 0;
        }

        emailInfoListRecord.created_at = date;
        emailInfoListRecord.updated_at = date;
        logger.info(emailInfoBean.to_email);
        long result = emailInfoRecordDao.addEmailInfo(emailInfoListRecord);

        logger.info(emailInfoListRecord);

        if (result > 0){
            try {
                emailRemote.triggerSend(emailInfoListRecord);
            } catch (javax.mail.MessagingException e) {
                e.printStackTrace();
            }
            return (int) emailInfoListRecord.id;
        } else {
            return 0;
        }
    }


    public int sendEmailStatus(int mail_id){

        EmailInfoListRecord emailInfoListRecord = emailInfoRecordDao.findOneMailInfo(mail_id);

        if (emailInfoListRecord != null) {
               return (int) emailInfoListRecord.status;
        }
        return 0;
    }
}
