package com.inschos.cloud.message.handing.access.http.controller.request;

import com.inschos.cloud.message.handing.access.http.controller.action.EmailAction;
import com.inschos.cloud.message.handing.access.http.controller.action.SmsAction;
import com.inschos.cloud.message.handing.access.http.controller.bean.ActionBean;
import com.inschos.cloud.message.handing.annotation.GetActionBeanAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/web/sms")
public class SmsController {

    @Autowired
    private SmsAction smsAction;

    @RequestMapping("/sendVerifyCode")
    @ResponseBody
    public Boolean sendVerifyCode(HttpServletRequest request){
        return smsAction.sendVerifyCode(request);
    }

    @RequestMapping("/sendAgentInvite")
    @ResponseBody
    public Boolean sendAgentInvite(HttpServletRequest request){
        return smsAction.sendAgentInvite(request);
    }

    @RequestMapping("/sendWinningActivity")
    @ResponseBody
    public String sendWinningActivity(HttpServletRequest request){
        return smsAction.sendWinningActivity(request);
    }
}
