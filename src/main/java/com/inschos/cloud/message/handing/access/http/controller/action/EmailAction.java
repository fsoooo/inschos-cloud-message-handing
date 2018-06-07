package com.inschos.cloud.message.handing.access.http.controller.action;

import com.inschos.cloud.message.handing.access.http.controller.bean.ActionBean;
import com.inschos.cloud.message.handing.access.http.controller.bean.BaseResponse;
import com.inschos.cloud.message.handing.access.http.controller.bean.EmailInfoBean;
import com.inschos.cloud.message.handing.assist.kit.CheckParamsKit;
import com.inschos.cloud.message.handing.assist.kit.JsonKit;
import com.inschos.cloud.message.handing.assist.kit.StringKit;
import com.inschos.cloud.message.handing.data.dao.EmailInfoRecordDao;
import com.inschos.cloud.message.handing.model.EmailInfoListRecord;
import com.inschos.cloud.message.handing.remoting.mail.EmailRemote;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.List;

@Component
public class EmailAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(EmailAction.class);

    @Autowired
    private EmailInfoRecordDao emailInfoRecordDao;
    @Autowired
    private EmailRemote emailRemote;

    public String addEmailInfo(ActionBean actionBean){
        //获取接口传参
        EmailInfoBean.EmailRequest request =  requst2Bean(actionBean.body, EmailInfoBean.EmailRequest.class);
        EmailInfoBean.EmailResponse response = new EmailInfoBean.EmailResponse();

        List<CheckParamsKit.Entry<String, String>> entries = checkParams(request);
        if (entries != null) {
            return json(BaseResponse.CODE_PARAM_ERROR,entries,response);
        }

        //获取当前时间戳(毫秒值)
        long date = System.currentTimeMillis();

        //赋值数据
        EmailInfoListRecord emailInfoListRecord = new EmailInfoListRecord();

        emailInfoListRecord.bekongs = request.bekongs;
        emailInfoListRecord.source_code = request.source_code;
        emailInfoListRecord.send_type = request.send_type;        //发送方式 1即时 2定时
        emailInfoListRecord.merge_code = request.merge_code;
        emailInfoListRecord.type = request.type;                  //邮件类型 0消息 1验证 2理赔 99其他
        emailInfoListRecord.status = 1;                           //发送状态 1 未发送  2发送中  3已发送 4发送失败
        emailInfoListRecord.title = request.title;
        emailInfoListRecord.html = request.html;

        if (request.to_email != null &&  !request.to_email.isEmpty()) {
            for (String s : request.to_email) {
                if (!StringKit.isEmail(s)){
                    return json(BaseResponse.CODE_PARAM_ERROR, "Unlawful mail address",response);
                }
            }
            emailInfoListRecord.to_email = JsonKit.bean2Json(request.to_email);
        } else {
            return json(BaseResponse.CODE_PARAM_ERROR, "Unlawful mail address",response);
        }

        emailInfoListRecord.created_at = date;
        emailInfoListRecord.updated_at = date;
        logger.info(request.to_email);
        long result = emailInfoRecordDao.addEmailInfo(emailInfoListRecord);

        logger.info(emailInfoListRecord);

        if (result > 0){
            try {
                emailRemote.triggerSend(emailInfoListRecord);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return json(BaseResponse.CODE_SUCCESS,emailInfoListRecord.id + "",response);
        } else {
            return json(BaseResponse.CODE_PARAM_ERROR, "Failure of mail data insert database",response);
        }
    }

    /**
     * 查询邮件发送状态
     * @return
     */
    public String sendEmailStatus(ActionBean actionBean){

        EmailInfoBean.EmailStatusRequest request =  requst2Bean(actionBean.body, EmailInfoBean.EmailStatusRequest.class);
        EmailInfoBean.EmailResponse response = new EmailInfoBean.EmailResponse();

        if (request.mail_id == 0) {
            return json(BaseResponse.CODE_PARAM_ERROR,"Mail_id is an invalid parameter",response);
        }
        EmailInfoListRecord emailInfoListRecord = emailInfoRecordDao.findOneMailInfo(request.mail_id);

        if (emailInfoListRecord != null) {
            return json(BaseResponse.CODE_SUCCESS,EmailInfoBean.sendStatus(emailInfoListRecord.status),response);
        } else {
            return json(BaseResponse.CODE_PARAM_ERROR, "Mail records do not exist",response);
        }
    }
}
