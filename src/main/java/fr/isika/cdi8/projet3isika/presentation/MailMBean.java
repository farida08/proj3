package fr.isika.cdi8.projet3isika.presentation;

import java.util.List;
import java.util.Properties;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import fr.isika.cdi8.projet3isika.entities.common.Adresse;
import fr.isika.cdi8.projet3isika.entities.demandes_client.Client;
import fr.isika.cdi8.projet3isika.idao.ClientIDao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ManagedBean
@SessionScoped
public class MailMBean {

	private String nom;
	private String mail;
	private String sujet;
	private String messageFormulaire;
	
	public String sendEmail() throws Exception {
		final String username = "ida08f@gmail.com";
		final String password = "isika2021";
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("khebbache.farida@gmail.com"));
			message.setSubject(sujet);
			message.setText(messageFormulaire
			);
			Transport.send(message);
			System.out.println("Done");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "mail.xhtml";
	}
	
}
