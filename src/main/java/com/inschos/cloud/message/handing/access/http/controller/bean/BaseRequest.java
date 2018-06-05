package com.inschos.cloud.message.handing.access.http.controller.bean;

import com.inschos.cloud.message.handing.assist.kit.CheckParamsKit;

import java.util.List;

public class BaseRequest {

    public String lastId;
    public String pageNum;
    public String pageSize;

    public final static String FILEID_BUILDCODE = "buildCode";
    public final static String FILEID_PLATFORM = "platform";
    public final static String FILEID_APICODE = "apiCode";
    public final static String FILEID_ACCESS_TOKEN = "token";

    public final static String PLATFORM_ANDROID = "android";
    public final static String PLATFORM_IOS = "ios";
    public final static String PLATFORM_WEB = "web";

}
