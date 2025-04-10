package com.travel.Utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

///JavaMail API,
@Component
public class EmailUtil 
{
	public void sendMessage(String from,String to,String subject ,String body)
	{
		//simple smptp protocol is used 
		 String host = "smtp.gmail.com"; // SMTP server (e.g., Gmail: smtp.gmail.com)
	     final String username = "pmeghana1310@gmail.com"; // Your email
	     final String password = "lfwo bthg bbgj dwti"; // Your email password

	    
	     Properties properties = new Properties();
	     properties.put("mail.smtp.host", host);
	     properties.put("mail.smtp.port", "587"); // Default port for SMTP
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        Session session = Session.getInstance(properties, new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	        	return new PasswordAuthentication(username, password);
	         }
	     });
	     try {
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(username));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         message.setSubject(subject);
	         message.setText(body);
	         Transport.send(message);


	         System.out.println("Email sent successfully!");
	     } catch (MessagingException e) {
	         e.printStackTrace();
	     }
	}
	
}