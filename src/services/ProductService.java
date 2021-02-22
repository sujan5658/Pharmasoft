package services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import beans.Message;
import beans.Product;

public class ProductService {
	@Autowired
	private Product product,tmpProduct;
	@Autowired
	private Message message;
	private SessionFactory factory;
	private Session session;
	private List<String> genericNames;
	private List<String> brandNames;
	private List<String> companyNames;
	public ProductService(Product product) {
		this.product = product;
		this.message = new Message();
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Product.class)
				.buildSessionFactory();
		session = factory.getCurrentSession();
	}
	public Message addProduct() {
		try {
			session.beginTransaction();
			session.save(this.product);
			session.getTransaction().commit();
			this.message.setStatus(false);
			this.message.setMessage("Product Addition Successful.");
		}
		catch(Exception er) {
			System.out.println("Error : "+er.getMessage());
			this.message.setStatus(true);
			this.message.setMessage("Internal Error Please Try Again Later.!!!");
		}
		return this.message;
	}
	public List<Product> getProductsInfo(){
		int i,j;
		List<Product> pinfos = new ArrayList<Product>();
		this.genericNames = new ArrayList<String>();
		this.brandNames = new ArrayList<String>();
		this.companyNames = new ArrayList<String>();
		boolean flag=false;
		try {
			session.beginTransaction();
			pinfos = session.createQuery("FROM Product WHERE deleteStatus=0 ORDER BY genericName ASC").list();
			session.getTransaction().commit();
		}
		catch(Exception er) {
			pinfos = null;
		}
		for(i=0;i<pinfos.size();i++) {
			if(this.genericNames.size()==0) {
				this.genericNames.add(pinfos.get(i).getGenericName());
				this.brandNames.add(pinfos.get(i).getBrandName());
				this.companyNames.add(pinfos.get(i).getCompanyName());
			}
			else {
				for(j=0;j<this.genericNames.size();j++) {
					if(pinfos.get(i).getGenericName().equals(this.genericNames.get(j))) {
						flag = true;
						break;
					}
				}
				if(flag==false){
					this.genericNames.add(pinfos.get(i).getGenericName());
				}
				flag=false;
				for(j=0;j<this.brandNames.size();j++) {
					if(pinfos.get(i).getBrandName().equals(this.brandNames.get(j))) {
						flag=true;
						break;
					}
				}
				if(flag==false){
					this.brandNames.add(pinfos.get(i).getBrandName());
				}
				flag=false;
				for(j=0;j<this.companyNames.size();j++) {
					if(pinfos.get(i).getCompanyName().equals(this.companyNames.get(j))) {
						flag =true;
						break;
					}
				}
				if(flag==false){
					this.companyNames.add(pinfos.get(i).getCompanyName());
				}
				flag=false;
			}
		}
		return pinfos;
	}
	public List<String> getGenericNames(){
		return this.genericNames;
	}
	public List<String> getBrandNames(){
		return this.brandNames;
	}
	public List<String> getComapnyNames(){
		return this.companyNames;
	}
	public List<Product> getSearchedProducts(){
		List<Product> pinfos = new ArrayList<Product>();
		try {
			session.beginTransaction();
			if(this.product.getBrandName().equals("None") && this.product.getCompanyName().equals("None")) {
				String HQL = "From Product WHERE genericName=?";
				Query query = session.createQuery(HQL);
				query.setString(0,this.product.getGenericName());
				pinfos = query.list();
			}
			else if(this.product.getCompanyName().equals("None")) {
				String HQL = "From Product WHERE genericName=? AND brandName=?";
				Query query = session.createQuery(HQL);
				query.setString(0,this.product.getGenericName());
				query.setString(1,this.product.getBrandName());
				pinfos = query.list();
			}
			else if(this.product.getBrandName().equals("None")){
				String HQL = "From Product WHERE genericName=? AND companyName=?";
				Query query = session.createQuery(HQL);
				query.setString(0,this.product.getGenericName());
				query.setString(1,this.product.getCompanyName());
				pinfos = query.list();
			}
			else {
				String HQL = "From Product WHERE genericName=? AND brandName=? AND companyName=?";
				Query query = session.createQuery(HQL);
				query.setString(0,this.product.getGenericName());
				query.setString(1,this.product.getBrandName());
				query.setString(2,this.product.getCompanyName());
				pinfos = query.list();
			}
			session.getTransaction().commit();
		}
		catch(Exception er) {
			pinfos = null;
			System.out.println("Error : "+er.getMessage());
		}
		return pinfos;
	}
	public Product getSingleProduct() {
		try {
			session.beginTransaction();
			this.product = (Product)session.get(Product.class,this.product.getId());
			session.getTransaction().commit();
		}
		catch(Exception er) {
			this.product = null;
		}
		return this.product;
	}
	public Message updateProduct() {
		try {
			session.beginTransaction();
			tmpProduct = (Product)session.get(Product.class,this.product.getId());
			tmpProduct.setGenericName(this.product.getGenericName());
			tmpProduct.setBrandName(this.product.getBrandName());
			tmpProduct.setCompanyName(this.product.getCompanyName());
			tmpProduct.setDistributor(this.product.getDistributor());
			tmpProduct.setManufactureDate(this.product.getManufactureDate());
			tmpProduct.setExpireDate(this.product.getExpireDate());
			tmpProduct.setCostPrice(this.product.getCostPrice());
			tmpProduct.setSellingPrice(this.product.getSellingPrice());
			tmpProduct.setQuantity(this.product.getQuantity());
			
			session.getTransaction().commit();
			this.message.setStatus(false);
			this.message.setMessage("Product Update Successful.");
		}
		catch(Exception er) {
			this.message.setStatus(true);
			this.message.setMessage("Internal Error Please Try Again Later.!!!");
		}
		return this.message;
	}
	public Message deleteProduct() {
		try {
			session.beginTransaction();
			tmpProduct = (Product)session.get(Product.class,this.product.getId());
			tmpProduct.setDeleteStatus(true);
			session.getTransaction().commit();
			this.message.setStatus(false);
			this.message.setMessage("Product Deletion Successful.");
		}
		catch(Exception er) {
			this.message.setStatus(true);
			this.message.setMessage("Internal Error Please Try Again Later.!!!");
		}
		return this.message;
	}
	public Message returnProduct() {
		try {
			session.beginTransaction();
			String HQL = "FROM Product WHERE genericName=? AND brandName=? AND companyName=?";
			Query query = session.createQuery(HQL);
			query.setString(0,this.product.getGenericName());
			query.setString(1,this.product.getBrandName());
			query.setString(2,this.product.getCompanyName());
			tmpProduct = (Product)query.uniqueResult();
			if(tmpProduct==null) {
				this.message.setStatus(true);
				this.message.setMessage("No Such Product Specification Found.!! Please Make a Search First.!!");
			}
			else {
				tmpProduct.setQuantity(tmpProduct.getQuantity()-this.product.getQuantity());
				session.getTransaction().commit();
				this.message.setStatus(false);
				this.message.setMessage(this.product.getQuantity()+" Piece "+this.product.getBrandName()+" is RETURNED..!!");
			}
		}
		catch(Exception er) {
			this.message.setStatus(true);
			this.message.setMessage("Internal Error Please Try Again Later.!!!");
		}
		return this.message;
	}
}
