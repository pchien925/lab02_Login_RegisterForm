package lab02.pc.models;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Random;

public class Email {
	public String getRandomCode() {
		Random rd = new Random();
		int number = rd.nextInt(9999999);
		return String.format("%06d", number);
	}

	public boolean sendMail(User user) {
		boolean test = false;
		String toMail = user.getEmail();
		String fromMail = "phamchien9254@gmail.com";
		String password = "oqvc caqw eboj zbwg";

		try {
			Properties pr = configMail(new Properties());

			Session session = Session.getInstance(pr, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromMail, password);
				}
			});

			Message mess = new MimeMessage(session);
			mess.setHeader("Content-Type", "text/plain; charset=UTF-8");

			mess.setFrom(new InternetAddress(fromMail));

			mess.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
			mess.setSubject("Comfirm Code");

			mess.setText("Code to active account is " + user.getCode());

			Transport.send(mess);

			test = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return test;
	}

	public Properties configMail(Properties pr) {
		pr.setProperty("mail.smtp.host", "smtp.gmail.com");
		pr.setProperty("mail.smtp.port", "587");
		pr.setProperty("mail.smtp.auth", "true");
		pr.setProperty("mail.smtp.starttls.enable", "true");
		pr.put("mail.smtp.soketFactory.port", "587");
		pr.put("mail.smtp.soketFactory.class", "javax.net.ssl.SSLSocketFactory");

		return pr;
	}

//	public static void main(String[] args) {
//		try {
//			User user = new User();
//			user.setEmail("pcchien250904@gmail.com");
//
//			// Sending email without attachment
//
//			boolean it;
//			Email em = new Email();
//			it = em.sendMail(user); 
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
