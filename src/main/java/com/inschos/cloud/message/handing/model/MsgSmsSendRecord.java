package com.inschos.cloud.message.handing.model;

/**
 * Created by IceAnt on 2018/5/4.
 */
public class MsgSmsSendRecord {

    /** 主键id*/
    public long id;

    /** 短信记录id*/
    public long sms_id;

    /** 状态  1 未发送  2发送中  3发送成功 4发送失败 */
    public int send_status;

    /** */
    public long created_at;

    /** */
    public String updated_at;


}
