package services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import beans.Mail;
import beans.User;

public class MailService {
	@Autowired
	private Mail mail;
	@Autowired
	private User user;
	private String host;
	private String port;
	private Date date;
	@Autowired
	private beans.Message message;

	public MailService(Mail mail) {
		this.mail = mail;
		this.host = "smtp.gmail.com";
		this.port = "587";
		this.message = new beans.Message();
	}

	public beans.Message sendMail() {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(User.class)
					.buildSessionFactory();
			org.hibernate.classic.Session session = factory.getCurrentSession();
			session.beginTransaction();
			String HQL = "FROM User WHERE email=?";
			Query query = session.createQuery(HQL);
			query.setString(0,this.mail.getReceiverAddress());
			this.user = (User)query.uniqueResult();
			session.getTransaction().commit();
			if(this.user==null) {
				this.message.setStatus(true);
				this.message.setMessage("Sorry Email Not Found..!!!");
				return this.message;
			}
		}
		catch(Exception er) {
			this.message.setStatus(true);
			this.message.setMessage("Query Error... Try Again Later.!!!");
			return this.message;
		}
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mail.getSenderAddress(),mail.getSenderPass());
			}
		};

		Session session = Session.getInstance(properties, auth);
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(this.mail.getSenderAddress()));
			InternetAddress[] toAddresses = { new InternetAddress(this.mail.getReceiverAddress())};
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(this.mail.getSubject());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			msg.setSentDate(new Date());
			msg.setText(this.mail.getMessage());
			Transport.send(msg);
			this.message.setStatus(false);
			this.message.setMessage("Check Your Email");
		}
		catch(Exception er) {
			System.out.println("Error : "+er.getMessage());
			System.out.println("Address : "+mail.getSenderAddress()+"  PAssword : "+mail.getSenderPass());
			this.message.setStatus(true);
			this.message.setMessage("Internal Error Try Again Later.!!!");
		}
		return this.message;
	}
}
