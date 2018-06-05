package com.inschos.cloud.message.handing.model;

public class EmailSendRecord {

    public long id;             //记录主键id

    public long mail_id;        //邮件所属

    public int send_status;     //发送状态 1 未发送  2发送中  3已发送 4发送失败

    public String rec_msg;      //描述

    public long created_at;

    public long updated_at;
}
