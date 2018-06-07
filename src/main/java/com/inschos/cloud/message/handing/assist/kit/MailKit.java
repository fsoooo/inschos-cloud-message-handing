package com.inschos.cloud.message.handing.assist.kit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class MailKit {
    private static Logger log = LoggerFactory.getLogger(MailKit.class);

    public static boolean sendMessage(String to, String title,
                                      String messageText) throws MessagingException {
        return sendMessage(new String[]{to},title,messageText);
    }

    public static boolean sendMessage(List<String> list, String title,
                                      String messageText) throws MessagingException {

        if (list == null || list.isEmpty()) {
            return false;
        }

        String[] to = list.toArray(new String[1]);

        return sendMessage(to,title,messageText);
    }

    public static boolean sendMessage(String[] to, String title,
                                      String messageText) throws MessagingException {
        String smtpHost = ConstantKit.MAIL_DEFAULT_SMTP_HOST;
        String from = ConstantKit.MAIL_DEFAULT_FROM;
        String fromUserPassword = ConstantKit.MAIL_DEFAULT_FROM_PASSWD;
        String messageType = "text/html;charset=utf-8";
        String nick = ConstantKit.MAIL_DEFAULT_FROM_NICK;
        int port = ConstantKit.MAIL_DEFAULT_SMTP_PORT;

        return sendMessage(smtpHost,port,from,fromUserPassword,to,title,messageText,messageType,nick);
    }

    public static boolean sendMessage(String smtpHost, int port, String from,
                                      String fromUserPassword, String[] to, String title,
                                      String messageText, String messageType, String nick) throws MessagingException {
        // 第一步：配置javax.mail.Session对象

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.starttls.enable","true");//使用 STARTTLS安全连接

        props.put("mail.smtp.port", port);             //google使用465或587端口
        props.put("mail.smtp.auth", "true");        // 使用验证
//        props.put("mail.debug", "true");
//        props.put("mail.transport.protocol", "smtp");


        Session mailSession = Session.getInstance(props,new MailAuthenticator(from,fromUserPassword));

        // 第二步：编写消息
        if (log.isDebugEnabled()){
            log.debug("编写消息from——to:" + from + "——" + to);
        }
        try {
            nick=javax.mail.internet.MimeUtility.encodeText(nick);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error("",e);
        }

        boolean falg = false;

        try {

            MimeMessage message = new MimeMessage(mailSession);
            InternetAddress fromAddress = new InternetAddress(nick+" <"+from+">");
            message.setFrom(fromAddress);

            if (to != null && to.length >0) {
                for (String s : to) {
                    if (StringKit.isEmail(s)) {
                        InternetAddress toAddress = new InternetAddress(s);
                        message.addRecipient(RecipientType.TO, toAddress);
                    }
                }
            }

            Address[] addresses = message.getRecipients(RecipientType.TO);

            if (addresses == null || addresses.length == 0) {
                return false;
            }

            message.setSentDate(Calendar.getInstance().getTime());
            message.setSubject(title);
            message.setContent(messageText, messageType);

            // 第三步：发送消息
            Transport transport = mailSession.getTransport("smtp");

            transport.connect(smtpHost,port,from, fromUserPassword);

            transport.send(message, addresses);

            falg = true;

        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return falg;
    }

    public static class MailAuthenticator extends Authenticator{
        String userName="";
        String password="";
        public MailAuthenticator(){

        }

        public MailAuthenticator(String userName,String password){
            this.userName=userName;
            this.password=password;
        }

        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(userName, password);
        }
    }
}
