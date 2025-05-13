package com.egeo.components.user.controller.thread;

import com.egeo.exception.BusinessException;
import com.egeo.utils.SendMail;
import com.egeo.utils.SpringContextTool;

public class SendMailThread implements Runnable {
	private String mail;
	private String platformNmae;
	private String content;
	public SendMailThread() {
	}
	public SendMailThread(
			String mail,String platformNmae,String content) {
		this.mail = mail;
		this.platformNmae = platformNmae;
		this.content = content;
	}

	@Override
	public void run() {
		try {
			SendMail sendMail = SpringContextTool.getBean(SendMail.class);
			sendMail.sendMail(mail, platformNmae + "--邮箱身份验证", content);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage() + mail + "发送邮箱失败");
		}

	}

}
