package com.inschos.cloud.message.handing.access.http.controller.bean;

import com.inschos.cloud.message.handing.annotation.CheckParams;

import java.util.List;

public class SmsBean {

    public static class Request  {
        public String phone;
        public String fromCode;
        public String code;
        public String name;
        public String content;
    }
}
