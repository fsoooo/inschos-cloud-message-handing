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
	@Value("${mail.host}")
	private String _mail_host;
	@Value("${mail.port}")
	private int _mail_port;
	@Value("${mail.nick_name}")
	private String _nick_name;
	@Value("${mail.username}")
	private String _mail_username;
	@Value("${mail.encryption}")
	private String _mail_encryption;
	@Value("${mail.password}")
	private String _mail_password;


	@PostConstruct
	public void init() {
		IS_PRODUCT = this._is_product;

		MAIL_DEFAULT_SMTP_HOST = this._mail_host;

		MAIL_DEFAULT_FROM = this._mail_username;

		MAIL_DEFAULT_FROM_NICK = this._nick_name;

		MAIL_DEFAULT_FROM_PASSWD = this._mail_password;

		MAIL_DEFAULT_SMTP_PORT = this._mail_port;
	}




}
