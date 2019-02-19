package com.wu.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;


//邮件发送的工具类
public class MailUtil {
	 /**
     * 实现信件的发送
     * 
     */
	   public boolean sendMail(String host,String from,String username,  
	    String password,String to,String subject,String code,String content) {  

		    try {
				HtmlEmail email = new HtmlEmail();    
				email.setAuthentication(username, password);  
				email.setHostName(host);  
				email.addTo(to, from);  
				email.setFrom(from);  
				email.setSubject(subject);  
				//注意，发送内容时，后面这段会让中文正常显示，否则乱码  
				email.setCharset(code);  
				email.setHtmlMsg(content); /*邮件内容*/  
				//发送  
				email.send();  
				return true;
			} catch (EmailException e) {
				e.printStackTrace();
				return false;
			}  

   }  
}
