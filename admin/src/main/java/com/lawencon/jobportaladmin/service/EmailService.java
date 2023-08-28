package com.lawencon.jobportaladmin.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.lawencon.jobportaladmin.model.Applicant;
import com.lawencon.jobportaladmin.model.Assesment;
import com.lawencon.jobportaladmin.model.CandidateUser;
import com.lawencon.jobportaladmin.model.Interview;
import com.lawencon.jobportaladmin.model.OfferingLetter;
import com.lawencon.jobportaladmin.model.User;

@Service
public class EmailService {

	private static final String JOBROAD_LOGO_IMAGE = "templates/images/jobroad.png";
	private static final String JOBROAD_ILLUSTRATION_IMAGE = "templates/images/illustration_png-03.png";
	private static final String PNG_MIME = "image/png";

	private final Environment environment;

	private final JavaMailSender javaMailSender;

	private final TemplateEngine htmlTemplateEngine;

	public EmailService(Environment environment, JavaMailSender javaMailSender, TemplateEngine htmlTemplateEngine) {
		this.environment = environment;
		this.javaMailSender = javaMailSender;
		this.htmlTemplateEngine = htmlTemplateEngine;
	}

	public void sendEmail(String to, String subject, String message)
			throws MessagingException, UnsupportedEncodingException {

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

	public void sendEmailNewUser( User user, String subject, String message){

		Thread thread= new Thread() {
			public void run() {
				
				try {
					String loginUrl = "http://localhost:4200/login";

					final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
					final MimeMessageHelper email;
					email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

					email.setTo(user.getUserEmail());
					email.setSubject(subject);

					final Context ctx = new Context(LocaleContextHolder.getLocale());
					ctx.setVariable("name", user.getProfile().getFullName());
					ctx.setVariable("email", user.getUserEmail());
					ctx.setVariable("body", message);
					ctx.setVariable("jobroadLogo", JOBROAD_LOGO_IMAGE);
					ctx.setVariable("url", loginUrl);

					final String htmlContent = htmlTemplateEngine.process("new-user-email", ctx);
					email.setText(htmlContent, true);

					ClassPathResource clr = new ClassPathResource(JOBROAD_LOGO_IMAGE);
					email.addInline("jobroadLogo", clr, PNG_MIME);

					javaMailSender.send(mimeMessage);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				
				
			}
		};
		
		thread.start();
		

	}

	public void sendEmailAssessment(String emailSubject, CandidateUser candidate, Assesment assesment,
			Applicant applicant) {

		Thread thread= new Thread() {
			public void run() {
				
				try {
					final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
					final MimeMessageHelper email;
					email = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					email.setTo(candidate.getUserEmail());
					email.setSubject(emailSubject);
					final Context ctx = new Context(LocaleContextHolder.getLocale());
					ctx.setVariable("salutation", candidate.getCandidateProfile().getSalutation());
					ctx.setVariable("name", candidate.getCandidateProfile().getFullname());
					ctx.setVariable("jobName", applicant.getJob().getJobName());
					ctx.setVariable("company", applicant.getJob().getCompany().getCompanyName());
					ctx.setVariable("location", assesment.getAssesmentLocation());
					ctx.setVariable("date", assesment.getAssesmentDate());
					ctx.setVariable("jobroadLogo", JOBROAD_LOGO_IMAGE);

					final String htmlContent = htmlTemplateEngine.process("assesment-email-template", ctx);
					email.setText(htmlContent, true);

					ClassPathResource clr = new ClassPathResource(JOBROAD_LOGO_IMAGE);
					email.addInline("jobroadLogo", clr, PNG_MIME);
					javaMailSender.send(mimeMessage);
					
					
				} catch (MessagingException e) {
					e.printStackTrace();
				}

			}
		};
		
		thread.start();
	}

	public void sendEmailInterview(String emailSubject, CandidateUser candidate, Interview interview,
			Applicant applicant) throws MessagingException, UnsupportedEncodingException {

		Thread thread = new Thread() {
			public void run() {
				try {
					final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
					final MimeMessageHelper email;
					email = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					email.setTo(candidate.getUserEmail());
					email.setSubject(emailSubject);

					final Context ctx = new Context(LocaleContextHolder.getLocale());
					ctx.setVariable("salutation", candidate.getCandidateProfile().getSalutation());
					ctx.setVariable("name", candidate.getCandidateProfile().getFullname());
					ctx.setVariable("jobName", applicant.getJob().getJobName());
					ctx.setVariable("company", applicant.getJob().getCompany().getCompanyName());
					ctx.setVariable("location", interview.getInterviewLocation());
					ctx.setVariable("date", interview.getInterviewDate());
					ctx.setVariable("jobroadLogo", JOBROAD_LOGO_IMAGE);

					final String htmlContent = htmlTemplateEngine.process("interview-email-template", ctx);
					email.setText(htmlContent, true);

					ClassPathResource clr = new ClassPathResource(JOBROAD_LOGO_IMAGE);
					email.addInline("jobroadLogo", clr, PNG_MIME);

					javaMailSender.send(mimeMessage);
					
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		};
		
		thread.start();
		
	}

	public void sendEmailOfferingLetter(String emailSubject, CandidateUser candidate, OfferingLetter offeringLetter,
			Applicant applicant, byte[] fileToAttach, String fileName) throws MessagingException, UnsupportedEncodingException {

		Thread thread = new Thread() {
			public void run () {
				try {
					final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
					final MimeMessageHelper email;
					email = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					email.setTo(candidate.getUserEmail());
					email.setSubject(emailSubject);
					email.addAttachment(fileName + ".pdf", new ByteArrayResource(fileToAttach));

					final Context ctx = new Context(LocaleContextHolder.getLocale());
					ctx.setVariable("salutation", candidate.getCandidateProfile().getSalutation());
					ctx.setVariable("name", candidate.getCandidateProfile().getFullname());
					ctx.setVariable("jobName", applicant.getJob().getJobName());
					ctx.setVariable("company", applicant.getJob().getCompany().getCompanyName());
					ctx.setVariable("salary", offeringLetter.getSalary());
					ctx.setVariable("address", offeringLetter.getAddress());
					ctx.setVariable("jobroadLogo", JOBROAD_LOGO_IMAGE);

					final String htmlContent = htmlTemplateEngine.process("offerletter-email-template", ctx);
					email.setText(htmlContent, true);

					ClassPathResource clr = new ClassPathResource(JOBROAD_LOGO_IMAGE);
					email.addInline("jobroadLogo", clr, PNG_MIME);

					javaMailSender.send(mimeMessage);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
		
	}

	public void sendEmailThymeLeaf(String title, String to, String subject, String message)
			throws MessagingException, UnsupportedEncodingException {

		final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		final MimeMessageHelper email;
		email = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		email.setTo(to);
		email.setSubject(subject);

		final Context ctx = new Context(LocaleContextHolder.getLocale());
		ctx.setVariable("title", title);
		ctx.setVariable("body", message);
		ctx.setVariable("jobroadLogo", JOBROAD_LOGO_IMAGE);

		final String htmlContent = this.htmlTemplateEngine.process("assesment-email", ctx);
		email.setText(htmlContent, true);

		ClassPathResource clr = new ClassPathResource(JOBROAD_LOGO_IMAGE);
		email.addInline("jobroadLogo", clr, PNG_MIME);

		javaMailSender.send(mimeMessage);
	}

	public void sendMailWithAttachment(String to, String subject, String body, byte[] fileToAttach, String fileName) {
		
		Thread thread = new Thread() {
			public void run() {
				try {
					MimeMessage message = javaMailSender.createMimeMessage();
					MimeMessageHelper mime = new MimeMessageHelper(message, true);
					mime.setTo(to);
					mime.setSubject(subject);
					mime.setText(body);
					mime.addAttachment(fileName + ".pdf", new ByteArrayResource(fileToAttach));

					javaMailSender.send(message);
				} catch (Exception ex) {
					ex.printStackTrace();
					System.err.println(ex.getMessage());
				}
			}
		};

		thread.start();
		
		
	}

}
