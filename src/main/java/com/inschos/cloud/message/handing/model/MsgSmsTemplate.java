package com.inschos.cloud.message.handing.model;

import com.inschos.cloud.message.handing.assist.kit.StringKit;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IceAnt on 2018/5/4.
 */
public class MsgSmsTemplate {

    public static final String T_SOURCE_ALIYUN = "ALIYUN";


    public static final String T_TEMPLATE_ALIYUN_VERIFY_CODE = "SMS_133964015";

    /** 主键id*/
    public long id;

    /** 短信签名*/
    public String sign_name;

    /** 模板来源*/
    public String tmp_source;

    /** 短信模板code*/
    public String tmp_code;

    /** 短信模板名称*/
    public String tmp_name;

    /** 模板内容*/
    public String tmp_content;


    /** */
    public long created_at;

    /** */
    public String updated_at;


    public  String getContent( String json){
        String content = null;
        switch (tmp_source){
            case T_SOURCE_ALIYUN:
                getAliContent(json);
                break;
        }
        return content;
    }

    private String getAliContent(String json){
        JSONObject jsonObject = new JSONObject(json);
        StringBuilder content = new StringBuilder();
        if(!StringKit.isEmpty(sign_name)){
            content.append("【"+sign_name+"】");
        }
        String msg = tmp_content;
        Map<String, String> parseParam = parseParam(msg);
        if(!parseParam.isEmpty()){
            parseParam.forEach((k,v)->{
                String value = "";
                if(jsonObject.has(v)){
                    value = jsonObject.getString(v);
                }
                msg.replaceAll(k,value);
            });
        }
        content.append(msg);
        return content.toString();
    }


    private  Map<String,String> parseParam(String tmpContent){
        Map<String,String> map = new HashMap<>();
        if(!StringKit.isEmpty(tmpContent)){
            Pattern pattern = Pattern.compile("\\$\\{([A-z\\-_]+)\\}");
            Matcher mMatcher = pattern.matcher(tmpContent);
            while (mMatcher.find()) {
                map.put(mMatcher.group(0),mMatcher.group(1));
            }
        }
        return map;
    }

}
