package com.inschos.cloud.message.handing.access.http.controller.request;

import com.inschos.cloud.message.handing.access.http.controller.action.EmailAction;
import com.inschos.cloud.message.handing.access.http.controller.bean.ActionBean;
import com.inschos.cloud.message.handing.annotation.GetActionBeanAnnotation;
import com.inschos.cloud.message.handing.assist.kit.HttpKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailAction emailAction;

    @GetActionBeanAnnotation(isCheckAccess = false)
    @RequestMapping("/send")
    @ResponseBody
    public String sendEmail(ActionBean actionBean){
        return emailAction.addEmailInfo(actionBean);
    }

}
