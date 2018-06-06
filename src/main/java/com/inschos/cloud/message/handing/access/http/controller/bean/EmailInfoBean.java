package com.inschos.cloud.message.handing.access.http.controller.bean;

import com.inschos.cloud.message.handing.annotation.CheckParams;
import com.inschos.cloud.message.handing.assist.kit.JsonKit;
import com.inschos.cloud.message.handing.assist.kit.StringKit;

import java.util.List;

public class EmailInfoBean {

    public static class EmailRequest extends BaseRequest {
        //等待发送的邮件
        @CheckParams
        public String bekongs;  //邮件所属
        @CheckParams
        public String source_code;  //邮件来源标识
        @CheckParams
        public int send_type;       //发送方式  1即时 2定时
        @CheckParams
        public String merge_code;   //邮件合并标识 同标识合并
        @CheckParams
        public int type;            //邮件类型 0消息 1验证 2理赔 99其他
        @CheckParams
        public String title;        //邮件标题
        @CheckParams
        public String html;         //邮件内容
        @CheckParams
        public List<String> to_email;     //收件地址 （json）
    }

    public static class EmailStatusRequest extends BaseRequest{
        @CheckParams
        public int mail_id;     //邮件记录
    }

    public static class EmailResponse extends BaseResponse {

    }

    public static String sendStatus(int status) {
        switch (status)
        {
            case 1: return "未发送";

            case 2: return "发送中";

            case 3: return "已发送";

            case 4: return "发送失败";

            default: return "异常状态";
        }
    }



}
