package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
@Table(name="tbl_pharmacist")
public class Pharmacist {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Size(min=3,message="Full Name must be atleast 3 character.!!!")
	@Column(name="full_name")
	private String fullName;
	@Size(min=5,message="Full Name must be atleast 5 character.!!!")
	@Column(name="contact_address")
	private String contactAddress;
	@Digits(integer=10,fraction=0,message="Contact No must be 10 digits.!!")
	@Min(value=1000000000,message="Contact No must be 10 digits")
	@Column(name="contact_no")
	private long contactNumber;
	@Column(name="gender")
	private char gender;
	@Size(min=3,message="User Name must be atleast 3 character.!!!")
	@Column(name="user_name")
	private String userName;
	@Size(min=7,message="Password must be atleast 7 character.!!!")
	@Column(name="user_pass")
	private String userPass;
	@Email(message="Invalid Email Address")
	@NotNull(message="Email is Empty.!!")
	@Column(name="email")
	private String email;
	@NotNull(message="Registered Date Empty.!!")
	@Pattern(regexp = "^(?:[0-9]{2})?[0-9]{2}-[0-3]?[0-9]-[0-3]?[0-9]$",message="must match YYYY-MM-DD")
	@Column(name="registered_date")
	private String registeredDate;
	@Column(name="retired_date")
	private String retiredDate;
	@Column(name="retired_status")
	private boolean retiredStatus;
	public Pharmacist() {
		this.id = 1;
		this.fullName = "";
		this.contactAddress = "";
		this.contactNumber = 123456789;
		this.gender = 'M';
		this.userName = "";
		this.userPass = "";
		this.email = "";
		this.registeredDate = "";
		this.retiredDate = "1111-11-11";
		this.retiredStatus = false;
	}
	public Pharmacist(int id, String fullName, String contactAddress, long contactNumber, char gender,
			String userName, String userPass, String email, String registeredDate, String retiredDate,
			boolean retiredStatus) {
		this.id = id;
		this.fullName = fullName;
		this.contactAddress = contactAddress;
		this.contactNumber = contactNumber;
		this.gender = gender;
		this.userName = userName;
		this.userPass = userPass;
		this.email = email;
		this.registeredDate = registeredDate;
		this.retiredDate = retiredDate;
		this.retiredStatus = retiredStatus;
	}
	public Pharmacist(Pharmacist p) {
		this.id = p.id;
		this.fullName = p.fullName;
		this.contactAddress = p.contactAddress;
		this.contactNumber = p.contactNumber;
		this.gender = p.gender;
		this.userName = p.userName;
		this.userPass = p.userPass;
		this.email = p.email;
		this.registeredDate = p.registeredDate;
		this.retiredDate = p.retiredDate;
		this.retiredStatus = p.retiredStatus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}
	public String getRetiredDate() {
		return retiredDate;
	}
	public void setRetiredDate(String retiredDate) {
		this.retiredDate = retiredDate;
	}
	public boolean isRetiredStatus() {
		return retiredStatus;
	}
	public void setRetiredStatus(boolean retiredStatus) {
		this.retiredStatus = retiredStatus;
	}
	@Override
	public String toString() {
		return "Pharmacist [id=" + id + ", fullName=" + fullName + ", contactAddress=" + contactAddress
				+ ", contactNumber=" + contactNumber + ", gender=" + gender + ", userName=" + userName + ", userPass="
				+ userPass + ", email=" + email + ", registeredDate=" + registeredDate + ", retiredDate=" + retiredDate
				+ ", retiredStatus=" + retiredStatus + "]";
	}
}
