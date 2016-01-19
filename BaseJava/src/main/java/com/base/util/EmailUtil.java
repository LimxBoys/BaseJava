package com.base.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailUtil {
	/**
	 * 发送邮件的方法
	 * @param to 目标邮箱地址
	 * @param mailTitle 所发送的邮件的标题
	 * @param mailContent 所发送的邮件的内容
	 */
	public void sendemail(String to, String email_title, String email_content) {
		JavaMailSender mailSender=SpringContextUtil.getBean("mailSender");
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setTo(to);//设置目标邮箱地址
		mail.setFrom("justtry1@126.com");
		mail.setSubject(email_title);//设置所发送的邮件的标题
		mail.setText(email_content);//设置所发送的邮件的内容
		mailSender.send(mail);//发送
	}
	/**
	 * 发送带html内容的邮件的方法
	 * @param to 目标邮箱地址
	 * @param mailTitle 所发送的邮件的标题
	 * @param mailContent 所发送的邮件的内容
	 * @throws MessagingException 
	 */
	public static void sendhtmlemail(String to, String email_title,
			String email_content){
		JavaMailSender mailSender=SpringContextUtil.getBean("mailSender");
		MimeMessage mm=mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mm, true, "UTF-8");
			helper.setTo(to);//设置目标邮箱地址
			helper.setFrom("limx_test@sina.com");//设置发送者,这里还可以另起Email别名，不用和xml里的username一致
			helper.setSubject(email_title);//设置所发送的邮件的标题
			helper.setText(email_content, true);//设置所发送的邮件的内容
			mailSender.send(mm);//发送
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
