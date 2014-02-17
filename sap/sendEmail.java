package edu.uci.ics.crawler4j.crawler;
 
import java.sql.SQLException;
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class sendEmail {
	
	/*
	 * Sends email to account
	 * Throws exception if runtime clause is null
	 */
 
	public static void sendmail() throws ClassNotFoundException, SQLException {
 
		// account details
		final String username = "testinternship2012@gmail.com";
		final String password = "internship2012";
 
		// email host settings
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
			// Creates message and sets message variables and sends email
		try {
			String flaggedList = sqlitejdbc.dbOutput();

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("testinternship2012@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Output from Jboss community forums:" + flaggedList);
 
			Transport.send(message);
 
			System.out.println("Done: Email sent");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	
	
		
	}
}