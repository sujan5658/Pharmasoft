package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tbl_user")
public class User {
	@Column(name="email")
	private String email;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Size(min=3,max=30,message="User name must be between 3 to 30 character.!!!")
	@Column(name="user_name")
	private String userName;
	@NotEmpty(message="Password Empty.!!!")
	@Column(name="user_pass")
	private String userPass;
	@NotEmpty(message="Role is Empty..!!")
	@Size(min=6,message="Invalid Role.!!!!")
	@Column(name="role")
	private String role;
	@Column(name="invalid_count")
	private byte invalidCount;
	public User() {
		this.userName = "";
		this.userPass = "";
		this.email = "";
		this.role = "";
	}
	public User(String userName, String password, String email, String role) {
		this.userName = userName;
		this.userPass = password;
		this.email = email;
		this.role = role;
	}
	public User(User usr) {
		this.userName = usr.userName;
		this.userPass = usr.userPass;
		this.email = usr.email;
		this.role = usr.role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public void setUserPass(String password) {
		this.userPass = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public byte getInvalidCount() {
		return invalidCount;
	}
	public void setInvalidCount(byte invalidCount) {
		this.invalidCount = invalidCount;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", userPass=" + userPass + ", email=" + email + ", role=" + role + "]";
	}
}
