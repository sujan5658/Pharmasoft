package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import beans.Message;
import beans.Pharmacist;
import beans.User;

public class PharmacistService {
	@Autowired
	private Pharmacist pharmacist,tmpPharmacist;
	@Autowired
	private Message message;
	@Autowired
	private User user;
	private SessionFactory factory;
	private Session session;
	public PharmacistService(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
		this.message = new Message();
		this.user = new User();
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Pharmacist.class)
				.buildSessionFactory();
		session = factory.getCurrentSession();
	}
	public Message addPharmacist() {
		try {
			session.beginTransaction();
			String HQL = "FROM Pharmacist WHERE userName=? AND retiredStatus=0";
			Query query = session.createQuery(HQL);
			query.setString(0,this.pharmacist.getUserName());
			tmpPharmacist = (Pharmacist)query.uniqueResult();
			session.getTransaction().commit();
			if(tmpPharmacist!=null) {
				this.message.setStatus(true);
				this.message.setMessage("The Username Already Exist Please Enter Unique One.!!!");
				return this.message;
			}
			else {
				factory = new Configuration().configure("hibernate.cfg.xml")
						.addAnnotatedClass(Pharmacist.class)
						.buildSessionFactory();
				session = factory.getCurrentSession();
				session.beginTransaction();
				HQL = "FROM Pharmacist WHERE email=? AND retiredStatus=0";
				query = session.createQuery(HQL);
				query.setString(0,this.pharmacist.getEmail());
				tmpPharmacist = (Pharmacist)query.uniqueResult();
				session.getTransaction().commit();
				if(tmpPharmacist!=null) {
					this.message.setStatus(true);
					this.message.setMessage("Email already exist.!!! Please Check the Pharmacist List..!!");
					return this.message;
				}
			}
			factory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(Pharmacist.class)
					.buildSessionFactory();
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(this.pharmacist);
			session.getTransaction().commit();
			
			SessionFactory fact = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(User.class)
					.buildSessionFactory();
			Session sess = fact.getCurrentSession();
			sess.beginTransaction();
			this.user.setUserName(this.pharmacist.getUserName());
			this.user.setUserPass(this.pharmacist.getUserPass());
			this.user.setEmail(this.pharmacist.getEmail());
			this.user.setRole("Pharmacist");
			sess.save(this.user);
			sess.getTransaction().commit();
			this.message.setStatus(false);
			this.message.setMessage("Pharmacist Addition Succeed.");
		}
		catch(Exception er) {
			System.out.println("Erro : "+er.getMessage());
			this.message.setStatus(true);
			this.message.setMessage("Internal Error.!!! Try Again Later");
		}
		return this.message;
	}
	public List<Pharmacist> getPharmacist() {
		List<Pharmacist> pharmacists = new ArrayList<Pharmacist>();
		try {
			session.beginTransaction();
			pharmacists = session.createQuery("FROM Pharmacist ORDER BY id DESC WHERE retiredStatus=0").list();
			session.getTransaction().commit();
		}
		catch(Exception er) {
			pharmacists = null;
		}
		return pharmacists;
	}
	public Pharmacist getSinglePharmacist() {
		try {
			session.beginTransaction();
			this.pharmacist = (Pharmacist)session.get(Pharmacist.class,this.pharmacist.getId());
			session.getTransaction().commit();
		}
		catch(Exception er) {
			this.pharmacist = null;
		}
		return this.pharmacist;
	}
	public Message updatePharmacist() {
		try {
			session.beginTransaction();
			tmpPharmacist = (Pharmacist)session.get(Pharmacist.class,this.pharmacist.getId());
			tmpPharmacist.setFullName(this.pharmacist.getFullName());
			tmpPharmacist.setContactAddress(this.pharmacist.getContactAddress());
			tmpPharmacist.setContactNumber(this.pharmacist.getContactNumber());
			tmpPharmacist.setEmail(this.pharmacist.getEmail());
			tmpPharmacist.setGender(this.pharmacist.getGender());
			tmpPharmacist.setRegisteredDate(this.pharmacist.getRegisteredDate());
			session.getTransaction().commit();
			this.message.setStatus(false);
			this.message.setMessage("Pharmacist Update Successful.");
		}
		catch(Exception er) {
			System.out.println("Error : "+er.getMessage());
			this.message.setStatus(true);
			this.message.setMessage("Internal Error.!!! Update Failed.!!!");
		}
		return this.message;
	}
	public Message deletePharmacist() {
		try {
			session.beginTransaction();
			tmpPharmacist = (Pharmacist)session.get(Pharmacist.class,this.pharmacist.getId());
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(formatter.format(date));
			tmpPharmacist.setRetiredStatus(true);
			tmpPharmacist.setRetiredDate(formatter.format(date).toString());
			session.getTransaction().commit();
			this.message.setStatus(false);
			this.message.setMessage("Pharmacist Deletion Successful.");
		}
		catch(Exception er) {
			this.message.setStatus(true);
			this.message.setMessage("Internal Error.!!! Deletion Failed.!!!");
		}
		return this.message;
	}
}
