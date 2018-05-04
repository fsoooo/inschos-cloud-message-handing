package com.inschos.cloud.message.handing.extend.aliyun.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.inschos.cloud.message.handing.assist.kit.L;
import com.inschos.cloud.message.handing.assist.kit.StringKit;

/**
 * Created by IceAnt on 2018/5/4.
 */
public class AliyunSms {

    private String SMS_ACCESS_KEY_ID;

    private String SMS_ACCESS_KEY_SECRET;


    public AliyunSms(String accessKey,String accessSecret){
        this.SMS_ACCESS_KEY_ID = accessKey;
        this.SMS_ACCESS_KEY_SECRET = accessSecret;
    }

    /**
     * 创建Sms端
     */
    private  IAcsClient createSmsClient() {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", SMS_ACCESS_KEY_ID,
                SMS_ACCESS_KEY_SECRET);
        try {
            // "Dysmsapi"  短信API产品名称（短信产品名固定，无需修改）
            // "dysmsapi.aliyuncs.com" 短信API产品域名（接口地址固定，无需修改）
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        } catch (ClientException e) {
            e.printStackTrace();

        }
        return new DefaultAcsClient(profile);
    }

    private  SendSmsResponse sendMsg(String signName,String templateCode, String params, String  outId, String ... args){
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        String phoneNumbers = StringKit.join(args,",");

        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phoneNumbers);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam(params);
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        if(!StringKit.isEmpty(outId)){
            request.setOutId(outId);
        }
        //请求失败这里会抛ClientException异常


        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = createSmsClient().getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return sendSmsResponse;

    }

    /**
     * 发送短信
     */
    public  boolean sendSms(String signName,String templateCode, String params, String  outId, String ... phones) {
        boolean result = false;
        SendSmsResponse response = sendMsg(signName,templateCode, params, null, phones);
        if(response!=null && response.getCode() != null){
            if(response.getCode().equals("OK")){
                result = true;
            }else{
                L.log.error("send message failed: code={} | message={} ",response.getCode(),response.getMessage());
            }
        }
        return result;

    }


}
