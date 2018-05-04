package com.inschos.cloud.message.handing.model;

/**
 * Created by IceAnt on 2018/5/4.
 */
public class MsgSmsRecord {
    /** 主键id*/
    public long id;

    /** 来源标识*/
    public String source_code;

    /**  1即时 2定时发送*/
    public int send_type;

    /** 状态 1 未发送  2发送中  3发送成功 4发送失败 */
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
    public String updated_at;

}
