package com.inschos.cloud.message.handing.assist.kit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by IceAnt on 2017/7/7.
 */
@Component
public class ConstantKit {

	public static boolean IS_PRODUCT = false;

	public static int API_CODE=2;

	public static String MAIL_DEFAULT_SMTP_HOST;
	public static String MAIL_DEFAULT_FROM;
	public static String MAIL_DEFAULT_FROM_PASSWD;
	public static String MAIL_DEFAULT_FROM_NICK;
	public static int MAIL_DEFAULT_SMTP_PORT;


	// 解决静态变量不能注解的问题

	@Value("${IS_PRODUCT}")
	private boolean _is_product = true;


	@PostConstruct
	public void init() {
		IS_PRODUCT = this._is_product;

	}

}
