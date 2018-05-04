package com.inschos.cloud.message.handing.model;

/**
 * Created by IceAnt on 2018/5/4.
 */
public class MsgSmsRecord {

    public final static int STATUS_WAITING = 1;

    public final static int STATUS_SENDING = 2;

    public final static int STATUS_BE_SENT = 3;

    public final static int STATUS_FAILED = 4;

    public final static int SEND_TYPE_NOW = 1;

    public final static int SEND_TYPE_FIXED = 2;

    /** 主键id*/
    public long id;

    /** 来源标识*/
    public String source_code;

    public String send_phone;

    /**  1即时 2定时发送*/
    public int send_type;

    /** 状态 1 未发送  2发送中  3已发送 4发送失败 */
    public int status;

    /** 短信模板*/
    public String tmp_code;

    /** 发送内容*/
    public String send_content;


    /** 发送内容*/
    public String content;

    /** */
    public long created_at;

    /** */
    public long updated_at;

}
