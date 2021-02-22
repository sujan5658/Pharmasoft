package controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import beans.Bill;
import beans.Mail;
import beans.Message;
import beans.Pharmacist;
import beans.PharmacyInfo;
import beans.Product;
import beans.User;
import services.BillService;
import services.MailService;
import services.PharmacistService;
import services.PharmacyInfoService;
import services.ProductService;
import services.UserService;

@Controller
public class HomeController {
	@Autowired
	private User user,newUser;
	@Autowired
	private Message message;
	@Autowired
	private Product product;
	@Autowired
	private PharmacyInfo pinfo;
	@Autowired
	private Pharmacist pharmacist;
	
	//For Recovery System
	private HttpSession session;
	@Autowired
	private Mail mail;
	
	@RequestMapping("/")
	public ModelAndView LoginPage() {
		return new ModelAndView("login","user",user);
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView loginProcess(@Valid @ModelAttribute("user") User user,BindingResult result,HttpServletRequest request,HttpServletResponse response,Model model) {
		if(result.hasErrors()) {
			return new ModelAndView("login");
		}
		else {
			this.message = new UserService(user).validateAdmin();
			if(this.message.isStatus()) {
				return new ModelAndView("login","message",message);
			}
			else {
				if(this.message.getMessage().equals("Administrator")) {
					HttpSession session = request.getSession();
					session.setAttribute("userName","ValidUser");
					model.addAttribute("pharmaInfo",new PharmacyInfoService(pinfo).getPharmacyInfo());
					model.addAttribute("pharmaUpdate",this.pinfo);
					return new ModelAndView("adminHome","page","adminHome");
				}
				else {
					HttpSession session = request.getSession();
					session.setAttribute("userName",user.getUserName());
					this.pinfo = new PharmacyInfo();
					model.addAttribute("pharmaInfo",new PharmacyInfoService(this.pinfo).getPharmacyInfo());
					model.addAttribute("bill",new BillService().getBill());
					this.product = new Product();
					model.addAttribute("medicines",new ProductService(this.product).getProductsInfo());
					this.pharmacist = new Pharmacist();
					model.addAttribute("pharmacists",new PharmacistService(this.pharmacist).getPharmacist());
					return new ModelAndView("pharmacistHome","page","pdashboard");
				}
			}	
		}
	}
	@RequestMapping(value="/dashboard")
	public ModelAndView homePage(Model model) {
		model.addAttribute("pharmaInfo",new PharmacyInfoService(pinfo).getPharmacyInfo());
		model.addAttribute("pharmaUpdate",this.pinfo);
		return new ModelAndView("adminHome","page","adminHome");
	}
	@RequestMapping(value="/updatePharmacy",method=RequestMethod.POST)
	public ModelAndView updatePharmacyInfo(@Valid @ModelAttribute("pharmaUpdate") PharmacyInfo pinfo,BindingResult result,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("pharmaInfo",new PharmacyInfoService(pinfo).getPharmacyInfo());
			return new ModelAndView("adminHome","page","adminHome");
		}
		else {
			model.addAttribute("updateMessage",new PharmacyInfoService(pinfo).updatePharmacyInfo());
			model.addAttribute("pharmaInfo",new PharmacyInfoService(pinfo).getPharmacyInfo());
			return new ModelAndView("adminHome","page","adminHome");
		}
	}
	@RequestMapping(value="/addProduct")
	public ModelAndView homePageWithProductAddition(Model model) {
		this.product = new Product();
		model.addAttribute("product",this.product);
		return new ModelAndView("adminHome","page","addProduct");
	}
	@RequestMapping(value="/productAddition",method=RequestMethod.POST)
	public ModelAndView addProductProcess(@Valid @ModelAttribute("product") Product product,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return new ModelAndView("adminHome","page","addProduct");
		}
		else {
			model.addAttribute("message",new ProductService(product).addProduct());
			return new ModelAndView("adminHome","page","addProduct");
		}
	}
	@RequestMapping(value="/returnProduct")
	public ModelAndView homePageWithProductReturn(Model model) {
		this.product = new Product();
		ProductService pservice = new ProductService(this.product);
		pservice.getProductsInfo();
		model.addAttribute("genericNames",pservice.getGenericNames());
		model.addAttribute("brandNames",pservice.getBrandNames());
		model.addAttribute("companyNames",pservice.getComapnyNames());
		return new ModelAndView("adminHome","page","returnProduct");
	}
	@RequestMapping(value="/productList")
	public ModelAndView homePageWithProductList(Model model) {
		this.product = new Product();
		ProductService pservice = new ProductService(this.product);
		pservice.getProductsInfo();
		model.addAttribute("genericNames",pservice.getGenericNames());
		model.addAttribute("brandNames",pservice.getBrandNames());
		model.addAttribute("companyNames",pservice.getComapnyNames());
		model.addAttribute("productList",new ProductService(this.product).getProductsInfo());
		return new ModelAndView("adminHome","page","productList");
	}
	@RequestMapping(value="/searchProduct")
	public ModelAndView searchingProductProcess(@RequestParam("genericName") String genericName,@RequestParam("brandName") String brandName,@RequestParam("companyName") String companyName,@RequestParam("page") String page,Model model) {
		this.product = new Product();
		this.product.setGenericName(genericName);
		this.product.setBrandName(brandName);
		this.product.setCompanyName(companyName);
		model.addAttribute("searchedProducts",new ProductService(this.product).getSearchedProducts());
		ProductService pservice = new ProductService(this.product);
		pservice.getProductsInfo();
		model.addAttribute("genericNames",pservice.getGenericNames());
		model.addAttribute("brandNames",pservice.getBrandNames());
		model.addAttribute("companyNames",pservice.getComapnyNames());
		model.addAttribute("productList",new ProductService(this.product).getProductsInfo());
		return new ModelAndView("adminHome","page",page);
	}
	@RequestMapping(value="/productReturn",method=RequestMethod.POST)
	public ModelAndView returnProductProcess(@RequestParam("genericName") String genericName,@RequestParam("brandName") String brandName,@RequestParam("companyName") String companyName,@RequestParam("quantity") String quantity,Model model) {
		this.product = new Product();
		this.product.setGenericName(genericName);
		this.product.setBrandName(brandName);
		this.product.setCompanyName(companyName);
		this.product.setQuantity(Integer.parseInt(quantity));
		model.addAttribute("message",new ProductService(this.product).returnProduct());
		model.addAttribute("productList",new ProductService(this.product).getProductsInfo());
		return new ModelAndView("adminHome","page","returnProduct");
	}
	@RequestMapping(value="/updateProduct")
	public ModelAndView homePageWithProductUpdate(@RequestParam("id") String id ,Model model) {
		this.product.setId(Integer.parseInt(id));
		model.addAttribute("product",new ProductService(this.product).getSingleProduct());
		return new ModelAndView("adminHome","page","updateProduct");
	}
	@RequestMapping(value="/productUpdate",method=RequestMethod.POST)
	public ModelAndView updateProductProcess(@Valid @ModelAttribute("product") Product product,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return new ModelAndView("adminHome","page","updateProduct");
		}
		else {
			model.addAttribute("message",new ProductService(product).updateProduct());
			return new ModelAndView("adminHome","page","updateProduct");
		}
	}
	@RequestMapping(value="/deleteProduct")
	public ModelAndView productDeletionProcess(@RequestParam("id") String id ,Model model) {
		this.product.setId(Integer.parseInt(id));
		model.addAttribute("message",new ProductService(this.product).deleteProduct());
		model.addAttribute("productList",new ProductService(this.product).getProductsInfo());
		return new ModelAndView("adminHome","page","productList");
	}
	//Pharmacist Control Starts
	@RequestMapping(value="/addPharmacist")
	public ModelAndView homePageWithPharmacistAddition(Model model) {
		this.pharmacist = new Pharmacist();
		model.addAttribute("pharmacist",this.pharmacist);
		return new ModelAndView("adminHome","page","addPharmacist");
	}
	@RequestMapping(value="/updatePharmacist")
	public ModelAndView PharmacistUpdatePage(@RequestParam("id") String id,Model model) {
		this.pharmacist = new Pharmacist();
		this.pharmacist.setId(Integer.parseInt(id));
		this.pharmacist = new PharmacistService(this.pharmacist).getSinglePharmacist();
		if(this.pharmacist==null) {
			model.addAttribute("pharmacists",new PharmacistService(this.pharmacist).getPharmacist());
			return new ModelAndView("adminHome","page","pharmacistList");
		}
		else {
			model.addAttribute("updatePharmacist",this.pharmacist);
			return new ModelAndView("adminHome","page","updatePharmacist");
		}
	}
	@RequestMapping(value="/pharmacistUpdate")
	public ModelAndView updatePharmacistProcess(@Valid @ModelAttribute("updatePharmacist") Pharmacist pharmacist,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return new ModelAndView("adminHome","page","updatePharmacist");
		}
		else {
			model.addAttribute("message",new PharmacistService(pharmacist).updatePharmacist());
			return new ModelAndView("adminHome","page","updatePharmacist");
		}
	}
	@RequestMapping(value="/pharmacistAddition",method=RequestMethod.POST)
	public ModelAndView addPharmacistProcess(@Valid @ModelAttribute("pharmacist") Pharmacist pharmacist,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return new ModelAndView("adminHome","page","addPharmacist");
		}
		else {
			model.addAttribute("message",new PharmacistService(pharmacist).addPharmacist());
			return new ModelAndView("adminHome","page","addPharmacist");
		}
	}
	@RequestMapping(value="/deletePharmacist")
	public ModelAndView pharmacistDeletionProcess(@RequestParam("id") String id,Model model) {
		this.pharmacist.setId(Integer.parseInt(id));
		model.addAttribute("message",new PharmacistService(this.pharmacist).deletePharmacist());
		model.addAttribute("pharmacists",new PharmacistService(this.pharmacist).getPharmacist());
		return new ModelAndView("adminHome","page","pharmacistList");
	}
	@RequestMapping(value="/pharmacistList")
	public ModelAndView homePageWithPharmacistList(Model model) {
		model.addAttribute("pharmacists",new PharmacistService(this.pharmacist).getPharmacist());
		return new ModelAndView("adminHome","page","pharmacistList");
	}
	//Pharmacist Control Ends
	@RequestMapping(value="/changeCredentials")
	public ModelAndView homePageWithCredentialsChange(){
		return new ModelAndView("adminHome","page","changeCredentials");
	}
	@RequestMapping(value="/credentialsChange",method=RequestMethod.POST)
	public ModelAndView changeCredentialsProcess(@RequestParam("oldUserName") String oldUser,@RequestParam("newUserName") String newUser,@RequestParam("newEmail") String newEmail,@RequestParam("oldPass") String oldPass,@RequestParam("newPass") String newPass,Model model) {
		this.user.setUserName(oldUser);
		this.user.setUserPass(oldPass);
		this.user.setRole("Administrator");
		this.message = new UserService(this.user).validateAdmin();
		if(this.message.isStatus() && this.message.getMessage().equals("Invalid User or Password.!!")) {
			this.message.setMessage("Invalid Old User or Password..!!");
			model.addAttribute("message",this.message);
		}
		else {
			this.newUser = new User();
			this.newUser.setUserName(newUser);
			this.newUser.setUserPass(newPass);
			this.newUser.setEmail(newEmail);
			model.addAttribute("message",new UserService(this.user).updateUser(this.newUser));
		}
		return new ModelAndView("adminHome","page","changeCredentials");
	}
	@RequestMapping(value="/logout")
	public ModelAndView logoutPage(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("login","user",user);
	}
	
	// Recover System Started
	@RequestMapping("/recoverAccount")
	public ModelAndView accountRecoveryMethod(HttpServletRequest request,@RequestParam("email") String email,Model model) {
		this.mail.setReceiverAddress(email);
		this.mail.setSubject("Validation Code");
		session = request.getSession();
		int key = (int)(Math.random()*99999+10000);
		session.setAttribute("KeyVerificationCodeAccountRecovery",key);
		this.mail.setMessage(key+" is Verification Code to recover your account. If you didn't request verification code then just neglet this email.");
		if(new MailService(this.mail).sendMail().getMessage().equals("Sorry Email Not Found..!!!")||new MailService(this.mail).sendMail().getMessage().equals("Internal Error Try Again Later.!!!")) {
			model.addAttribute("user",new User());
			return new ModelAndView("login","message",new MailService(this.mail).sendMail());
		}
		else {
			session.setAttribute("email",email);
			return new ModelAndView("reset","message",new MailService(this.mail).sendMail());
		}
	}
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	public ModelAndView validateCodeProcess(HttpServletRequest request,@RequestParam("validCode") String code,Model model) {
		session = request.getSession();
		if(code.equals(session.getAttribute("KeyVerificationCodeAccountRecovery").toString())) {
			session.removeAttribute("KeyVerificationCodeAccountRecovery");
			return new ModelAndView("resetPass");
		}
		else {
			System.out.println("invalid case");
			session.removeAttribute("KeyVerificationCodeAccountRecovery");
			session.invalidate();
			model.addAttribute("user",new User());
			this.message = new Message();
			this.message.setStatus(true);
			this.message.setMessage("Invalid Verification Code.!!!");
			return new ModelAndView("login","message",this.message);
		}
	}
	@RequestMapping(value="/recoverPass",method=RequestMethod.POST)
	public ModelAndView resetUserAndPass(HttpServletRequest request,@RequestParam("userName") String userName,@RequestParam("userPass") String userPass,Model model) {
		session = request.getSession();
		this.user.setEmail(session.getAttribute("email").toString());
		session.removeAttribute("email");
		session.invalidate();
		this.user.setUserName(userName);
		this.user.setUserPass(userPass);
		model.addAttribute("user",new User());
		return new ModelAndView("login","message",new UserService(this.user).recoverUser());
	}
	
	//Pharmacist Page Controller Starts
	
	@RequestMapping(value="/pdashboard")
	public ModelAndView pharmacistHomePage(Model model) {
		this.product = new Product();
		model.addAttribute("medicines",new ProductService(this.product).getProductsInfo());
		model.addAttribute("pharmaInfo",new PharmacyInfoService(this.pinfo).getPharmacyInfo());	
		model.addAttribute("bill",new BillService().getBill());
		this.pharmacist = new Pharmacist();
		model.addAttribute("pharmacists",new PharmacistService(this.pharmacist).getPharmacist());
		return new ModelAndView("pharmacistHome","page","pdashboard");
	}
	@RequestMapping(value="/pbilling",method=RequestMethod.POST)
	public ModelAndView billingProcess(HttpServletRequest request,@Valid @ModelAttribute("bill") Bill bill,BindingResult result,Model model) {
		this.product = new Product();
		model.addAttribute("medicines",new ProductService(this.product).getProductsInfo());
		model.addAttribute("pharmaInfo",new PharmacyInfoService(this.pinfo).getPharmacyInfo());	
		this.pharmacist = new Pharmacist();
		model.addAttribute("pharmacists",new PharmacistService(this.pharmacist).getPharmacist());
		if(result.hasErrors()) {
			return new ModelAndView("pharmacistHome","page","pdashboard");
		}
		else {
			model.addAttribute("message",new BillService(bill).storeBill(request));
			model.addAttribute("bill",new BillService().getBill());
			return new ModelAndView("pharmacistHome","page","pdashboard");
		}
	}
	@RequestMapping(value="/pcredentialsChange")
	public ModelAndView pharmacistCredentialPage() {
		return new ModelAndView("pharmacistHome","page","pcredentials");
	}
	@RequestMapping(value="/pcredentialsChange",method=RequestMethod.POST)
	public ModelAndView pharmacistCredentialsChangeProcess(@RequestParam("oldUserName") String oldUser,@RequestParam("newUserName") String newUser,@RequestParam("newEmail") String newEmail,@RequestParam("oldPass") String oldPass,@RequestParam("newPass") String newPass,Model model) {
		if(oldUser.equals("") || newUser.equals("") || newEmail.equals("") || newPass.equals("") || oldPass.equals("")){
			this.message.setStatus(true);
			this.message.setMessage("All Fields Required..!!");
			model.addAttribute("message",this.message);
		}
		else {
			this.user.setUserName(oldUser);
			this.user.setUserPass(oldPass);
			this.user.setRole("Pharmacist");
			this.message = new UserService(this.user).validateAdmin();
			if(this.message.isStatus() && this.message.getMessage().equals("Invalid User or Password.!!")) {
				this.message.setMessage("Invalid Old User or Password..!!");
				model.addAttribute("message",this.message);
			}
			else {
				this.newUser = new User();
				this.newUser.setUserName(newUser);
				this.newUser.setUserPass(newPass);
				this.newUser.setEmail(newEmail);
				model.addAttribute("message",new UserService(this.user).updateUser(this.newUser));
			}
		}
		return new ModelAndView("pharmacistHome","page","pcredentials");
	}
	@RequestMapping(value="/viewBill")
	public ModelAndView billViewingPage(Model model) {
		model.addAttribute("bills",new BillService(new Bill()).getBills());
		return new ModelAndView("pharmacistHome","page","viewBill");
	}
	@RequestMapping(value="/singleBillView")
	public ModelAndView viewSingleBill(HttpServletRequest request,@RequestParam("billName") String bill,Model model) {
		new BillService().viewBill(request,bill);
		model.addAttribute("bills",new BillService(new Bill()).getBills());
		return new ModelAndView("pharmacistHome","page","viewBill");
	}
}
