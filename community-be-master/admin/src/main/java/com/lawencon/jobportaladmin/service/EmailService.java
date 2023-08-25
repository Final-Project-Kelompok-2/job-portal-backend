package com.lawencon.jobportaladmin.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String to, String subject, String message) {

		Thread thread = new Thread() {
			public void run() {
				SimpleMailMessage msg = new SimpleMailMessage();
				msg.setTo(to);
				msg.setSubject(subject);
				msg.setText(message);
				javaMailSender.send(msg);
            }
        };
        
        thread.start();

	}
	
	
	public void sendMailWithAttachment(String to, String subject, String body, byte[] fileToAttach, String fileName){
//		MimeMessagePreparator preparator = new MimeMessagePreparator() {
//			
//			@Override
//	        public void prepare(MimeMessage mimeMessage) throws Exception{
//	            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
//	            mimeMessage.setFrom(new InternetAddress("admin@gmail.com"));
//	            mimeMessage.setSubject(subject);
//	            mimeMessage.setText(body);
//	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//	            helper.addAttachment(fileName +".pdf", new ByteArrayResource(fileToAttach));
//	        }
//	    };
		
	    try {
	    	MimeMessage message = javaMailSender.createMimeMessage();
	    	MimeMessageHelper mime = new MimeMessageHelper(message,true);
	    	mime.setTo(to);
	    	mime.setSubject(subject);
	    	mime.setText(body);
	    	mime.addAttachment(fileName+".pdf", new ByteArrayResource(fileToAttach));
	    	
	    	javaMailSender.send(message);
//	    	javaMailSender.send(preparator);
	    }
	    catch (Exception ex) {
	        ex.printStackTrace();
	        System.err.println(ex.getMessage());
	    }
	}


	
}
