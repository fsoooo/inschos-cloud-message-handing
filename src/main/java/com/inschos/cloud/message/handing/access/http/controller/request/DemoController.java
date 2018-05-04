package com.inschos.cloud.message.handing.access.http.controller.request;


import com.inschos.cloud.message.handing.access.http.controller.action.DemoAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IceAnt on 2018/5/2.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoAction demoAction;

    @RequestMapping("/find/**")
    @ResponseBody
    public String find(HttpServletRequest request){


        return demoAction.find("1");
    }
}
