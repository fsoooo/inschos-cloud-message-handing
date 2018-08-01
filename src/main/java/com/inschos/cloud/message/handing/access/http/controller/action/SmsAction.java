package com.inschos.cloud.message.handing.access.http.controller.action;

import com.inschos.cloud.message.handing.access.http.controller.bean.BaseResponse;
import com.inschos.cloud.message.handing.access.http.controller.bean.SmsBean;
import com.inschos.cloud.message.handing.assist.kit.HttpKit;
import com.inschos.cloud.message.handing.assist.kit.JsonKit;
import com.inschos.cloud.message.handing.assist.kit.TimeKit;
import com.inschos.cloud.message.handing.data.dao.MsgSmsRecordDao;
import com.inschos.cloud.message.handing.data.dao.MsgSmsTemplateDao;
import com.inschos.cloud.message.handing.model.MsgSmsRecord;
import com.inschos.cloud.message.handing.model.MsgSmsTemplate;
import com.inschos.cloud.message.handing.remoting.aliyun.sms.AliyunSmsRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SmsAction extends BaseAction {

    @Autowired
    private MsgSmsRecordDao msgSmsRecordDao;
    @Autowired
    private MsgSmsTemplateDao msgSmsTemplateDao;
    @Autowired
    private AliyunSmsRemote aliyunSmsRemote;

    public boolean sendVerifyCode(HttpServletRequest httpServletRequest) {
        SmsBean.Request request = JsonKit.json2Bean(HttpKit.readRequestBody(httpServletRequest),  SmsBean.Request.class);
        if (request == null) {
            return false;
        }
        if (request.fromCode == null || request.phone == null || request.code == null) {
            return false;
        }
        MsgSmsTemplate template = msgSmsTemplateDao.findOneByTmpCode(MsgSmsTemplate.T_TEMPLATE_ALIYUN_VERIFY_CODE);
        int result = 0;
        if (template != null) {
            MsgSmsRecord smsRecord = new MsgSmsRecord();
            smsRecord.created_at = smsRecord.updated_at = TimeKit.currentTimeMillis();
            smsRecord.source_code = request.fromCode;
            smsRecord.send_type = MsgSmsRecord.SEND_TYPE_NOW;
            smsRecord.send_phone = request.phone;
            smsRecord.send_content = "{\"code\":\"" + request.code + "\"}";
            smsRecord.content = template.getContent(smsRecord.send_content);
            smsRecord.tmp_code = template.tmp_code;
            smsRecord.status = MsgSmsRecord.STATUS_WAITING;
            result = msgSmsRecordDao.add(smsRecord);
            if (result > 0) {
                aliyunSmsRemote.sendSms(smsRecord.id, smsRecord.send_content, request.phone);
            }
        }
        return result > 0;
    }


    public boolean sendAgentInvite(HttpServletRequest httpServletRequest) {
        SmsBean.Request request = JsonKit.json2Bean(HttpKit.readRequestBody(httpServletRequest),  SmsBean.Request.class);
        if (request == null) {
            return false;
        }
        if (request.fromCode == null || request.phone == null || request.name == null || request.code == null) {
            return false;
        }
        MsgSmsTemplate template = msgSmsTemplateDao.findOneByTmpCode(MsgSmsTemplate.T_TEMPLATE_ALIYUN_AGENT_INVITE_CODE);
        int result = 0;
        if (template != null) {
            MsgSmsRecord smsRecord = new MsgSmsRecord();
            smsRecord.created_at = smsRecord.updated_at = TimeKit.currentTimeMillis();
            smsRecord.source_code = request.fromCode;
            smsRecord.send_type = MsgSmsRecord.SEND_TYPE_NOW;
            smsRecord.send_phone = request.phone;
            smsRecord.send_content = "{\"name\":\"" + request.name + "\",\"code\":\"" + request.code + "\"}";
            smsRecord.content = template.getContent(smsRecord.send_content);
            smsRecord.tmp_code = template.tmp_code;
            smsRecord.status = MsgSmsRecord.STATUS_WAITING;
            result = msgSmsRecordDao.add(smsRecord);
            if (result > 0) {
                aliyunSmsRemote.sendSms(smsRecord.id, smsRecord.send_content, request.phone);
            }
        }
        return result > 0;
    }


    public String sendWinningActivity(HttpServletRequest httpServletRequest) {
        SmsBean.Request request = JsonKit.json2Bean(HttpKit.readRequestBody(httpServletRequest),  SmsBean.Request.class);
        if (request == null) {
            return "1";
        }
        if (request.fromCode == null || request.phone == null || request.content == null) {
            return "2";
        }
        MsgSmsTemplate template = msgSmsTemplateDao.findOneByTmpCode(MsgSmsTemplate.T_TEMPLATE_ALIYUN_WINNING_CODE);
        int result = 0;
        if (template != null) {
            MsgSmsRecord smsRecord = new MsgSmsRecord();
            smsRecord.created_at = smsRecord.updated_at = TimeKit.currentTimeMillis();
            smsRecord.source_code = request.fromCode;
            smsRecord.send_type = MsgSmsRecord.SEND_TYPE_NOW;
            smsRecord.send_phone = request.phone;
            smsRecord.send_content = "{\"content\":\"" + request.content + "\"}";
            smsRecord.content = template.getContent(smsRecord.send_content);
            smsRecord.tmp_code = template.tmp_code;
            smsRecord.status = MsgSmsRecord.STATUS_WAITING;
            result = msgSmsRecordDao.add(smsRecord);
            if (result > 0) {
                aliyunSmsRemote.sendSms(smsRecord.id, smsRecord.send_content, request.phone);
            }
        }
        return "3";
    }

}
