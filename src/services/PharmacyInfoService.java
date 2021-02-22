package services;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import beans.Message;
import beans.PharmacyInfo;

public class PharmacyInfoService {
	@Autowired
	private PharmacyInfo pinfo,pharmaInfo;
	@Autowired
	private Message message;
	private SessionFactory factory;
	private Session session;
	public PharmacyInfoService(PharmacyInfo pinfo) {
		this.pinfo = pinfo;
		this.message = new Message();
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(PharmacyInfo.class)
				.buildSessionFactory();
		session = factory.getCurrentSession();
	}
	public PharmacyInfo getPharmacyInfo() {
		try {
			session.beginTransaction();
			Query query = session.createQuery("FROM PharmacyInfo WHERE id = 1");
			this.pinfo = (PharmacyInfo)query.uniqueResult();
			session.getTransaction().commit();
		}
		catch(Exception er) {
			System.out.println("Error : "+er.getMessage());
			this.pinfo = null;
		}
		return this.pinfo;
	}
	public Message updatePharmacyInfo() {
		try {
			session.beginTransaction();
			this.pharmaInfo = (PharmacyInfo)session.get(PharmacyInfo.class,1);
			this.pharmaInfo.setPharmacyName(this.pinfo.getPharmacyName());
			this.pharmaInfo.setPharmacyAddress(this.pinfo.getPharmacyAddress());
			this.pharmaInfo.setRegisteredDate(this.pinfo.getRegisteredDate());
			this.pharmaInfo.setPanNo(this.pinfo.getPanNo());
			this.pharmaInfo.setTelephone(this.pinfo.getTelephone());
			session.getTransaction().commit();
			System.out.println(this.pharmaInfo);
			this.message.setStatus(false);
			this.message.setMessage("Update Info Succeed.");
		}
		catch(Exception er) {
			this.message.setStatus(true);
			this.message.setMessage("Internal Error.!!!");
		}
		return this.message;
	}
}
