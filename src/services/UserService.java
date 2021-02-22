package services;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import beans.Message;
import beans.Pharmacist;
import beans.User;

public class UserService {
	@Autowired
	private User user,tmpUser;
	@Autowired
	private Message message;
	@Autowired
	private Pharmacist pharmacist;
	private SessionFactory factory;
	private Session session;
	public UserService(User user) {
		this.user = user;
		message = new Message();
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.buildSessionFactory();
		session = factory.getCurrentSession();
	}
	public Message validateAdmin() {
		try {
			session.beginTransaction();
			String HQL = "FROM User WHERE userName=? AND userPass=? AND role=?";
			Query query = session.createQuery(HQL);
			query.setString(0,this.user.getUserName());
			query.setString(1,this.user.getUserPass());
			query.setString(2, this.user.getRole());
			this.tmpUser = (User)query.uniqueResult();
			session.getTransaction().commit();
			if(this.tmpUser==null){
				factory = new Configuration().configure("hibernate.cfg.xml")
						.addAnnotatedClass(User.class)
						.buildSessionFactory();
				session = factory.getCurrentSession();
				session.beginTransaction();
				HQL = "FROM User WHERE userName=?";
				query = session.createQuery(HQL);
				query.setString(0,this.user.getUserName());
				this.user = (User)query.uniqueResult();
				session.getTransaction().commit();
				if(this.user!=null) {
					factory = new Configuration().configure("hibernate.cfg.xml")
							.addAnnotatedClass(User.class)
							.buildSessionFactory();
					session = factory.getCurrentSession();
					session.beginTransaction();
					HQL = "FROM User WHERE userName=?";
					query = session.createQuery(HQL);
					query.setString(0,this.user.getUserName());
					this.tmpUser = (User)query.uniqueResult();
					if(this.user.getInvalidCount()>=5) {
						this.tmpUser.setInvalidCount((byte)(5));
					}
					else {
						this.tmpUser.setInvalidCount((byte)(this.user.getInvalidCount()+1));
					}
					session.getTransaction().commit();
				}
				this.message.setStatus(true);
				this.message.setMessage("Invalid User or Password.!!");
			}
			else {
				if(this.tmpUser.getInvalidCount()>=5) {
					this.message.setStatus(true);
					this.message.setMessage("Due to Try of Unusual Access, Your Account has been blocked Temporarily. Please go through Forget Password Method to unblock it.");
				}
				else {
					factory = new Configuration().configure("hibernate.cfg.xml")
							.addAnnotatedClass(User.class)
							.buildSessionFactory();
					session = factory.getCurrentSession();
					session.beginTransaction();
					HQL = "FROM User WHERE userName=?";
					query = session.createQuery(HQL);
					query.setString(0,this.tmpUser.getUserName());
					this.tmpUser = (User)query.uniqueResult();
					this.tmpUser.setInvalidCount((byte)(0));
					session.getTransaction().commit();
					this.message.setStatus(false);
					this.message.setMessage(this.tmpUser.getRole());
				}
			}
		}
		catch(Exception er) {
			this.message.setStatus(true);
			this.message.setMessage("Internal Error Please Try Again Later.!!!");
		}
		return this.message;
	}
	public Message updateUser(User newUser) {
		try {
			String HQL;
			String userName = this.user.getUserName();
			String userPass = this.user.getUserPass();
			Query query;
			session.beginTransaction();
			HQL = "FROM User WHERE userName=? AND userPass=? AND role=?";
			query = session.createQuery(HQL);
			query.setString(0,this.user.getUserName());
			query.setString(1,this.user.getUserPass());
			query.setString(2, this.user.getRole());
			this.user = (User)query.uniqueResult();
			session.getTransaction().commit();
			
			factory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(User.class)
					.buildSessionFactory();
			session = factory.getCurrentSession();
			session.beginTransaction();
			HQL = "FROM User WHERE email=?";
			query = session.createQuery(HQL);
			query.setString(0,newUser.getEmail());
			this.tmpUser = (User)query.uniqueResult();
			session.getTransaction().commit();
			if(this.tmpUser!=null && !newUser.getEmail().equals(this.user.getEmail())) {
				this.message.setStatus(true);
				this.message.setMessage("Email already exist.!!! Please provide another valid one.!!");
				return this.message;
			}
			factory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(User.class)
					.buildSessionFactory();
			session = factory.getCurrentSession();
			session.beginTransaction();
			HQL = "FROM User WHERE userName=? AND userPass=? AND role=?";
			query = session.createQuery(HQL);
			query.setString(0,this.user.getUserName());
			query.setString(1,this.user.getUserPass());
			query.setString(2, this.user.getRole());
			this.user = (User)query.uniqueResult();
			this.user.setUserName(newUser.getUserName());
			this.user.setUserPass(newUser.getUserPass());
			this.user.setEmail(newUser.getEmail());
			session.getTransaction().commit();
			if(this.user.getRole().equals("Pharmacist")){
				SessionFactory fact = new Configuration().configure("hibernate.cfg.xml")
						.addAnnotatedClass(Pharmacist.class)
						.buildSessionFactory();
				Session sess = fact.getCurrentSession();
				sess.beginTransaction();
				HQL = "FROM Pharmacist WHERE userName=? AND userPass=?";
				query = sess.createQuery(HQL);
				query.setString(0,userName);
				query.setString(1,userPass);
				this.pharmacist = (Pharmacist)query.uniqueResult();
				this.pharmacist.setUserName(newUser.getUserName());
				this.pharmacist.setUserPass(newUser.getUserPass());
				this.pharmacist.setEmail(newUser.getEmail());
				sess.getTransaction().commit();
			}
			this.message.setStatus(false);
			this.message.setMessage("Credentials Changed Successfully.!!");
		}
		catch(Exception er) {
			this.message.setStatus(true);
			this.message.setMessage("Internal Error Please Try Again Later.!!!");
		}
		return this.message;
	}
	public Message recoverUser() {
		try {
			session.beginTransaction();
			String HQL = "FROM User WHERE email=?";
			Query query = session.createQuery(HQL);
			query.setString(0,this.user.getEmail());
			this.tmpUser = (User)query.uniqueResult();
			this.tmpUser.setUserName(this.user.getUserName());
			this.tmpUser.setUserPass(this.user.getUserPass());
			this.tmpUser.setInvalidCount((byte)(0));
			session.getTransaction().commit();
			if(this.tmpUser.getRole().equals("Pharmacist")) {
				SessionFactory fact = new Configuration().configure("hibernate.cfg.xml")
						.addAnnotatedClass(Pharmacist.class)
						.buildSessionFactory();
				Session sess = fact.getCurrentSession();
				sess.beginTransaction();
				HQL = "FROM Pharmacist WHERE email=?";
				query = sess.createQuery(HQL);
				query.setString(0,this.user.getEmail());
				this.pharmacist = (Pharmacist)query.uniqueResult();
				this.pharmacist.setUserName(this.user.getUserName());
				this.pharmacist.setUserPass(this.user.getUserPass());
				sess.getTransaction().commit();
			}
			this.message.setStatus(false);
			this.message.setMessage("Account Recovery Successful.!!");
		}
		catch(Exception er) {
			this.message.setStatus(true);
			this.message.setMessage("Internal Error Please Try Again Later.!!!");
		}
		return this.message;
	}
}
