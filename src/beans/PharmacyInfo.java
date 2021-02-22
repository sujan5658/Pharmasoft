package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
@Table(name="tbl_pharmacy_info")
public class PharmacyInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Size(min=3,message="Name must contain atlest 3 character.")
	@Column(name="pharmacy_name")
	private String pharmacyName;
	@Size(min=3,message="Address must contain atlest 3 character.")
	@Column(name="address")
	private String pharmacyAddress;
	@NotNull(message="Date is Empty.")
	@Pattern(regexp = "^(?:[0-9]{2})?[0-9]{2}-[0-3]?[0-9]-[0-3]?[0-9]$",message="must match YYYY/MM/DD")
	@Column(name="registered_date")
	private String registeredDate;
	@NotNull(message="Pan Number is Empty.")
	@Column(name="pan_no")
	private int panNo;
	@NotNull(message="Telephone No is Empty.")
	@Column(name="telephone")
	private long telephone;
	public PharmacyInfo() {
		this.pharmacyName = "";
		this.pharmacyAddress = "";
		this.registeredDate = "";
		this.panNo = 0;
		this.telephone = 0;
	}
	public PharmacyInfo(String pharmacyName, String pharmacyAddress, String registeredDate, int panNo, long telephone) {
		this.pharmacyName = pharmacyName;
		this.pharmacyAddress = pharmacyAddress;
		this.registeredDate = registeredDate;
		this.panNo = panNo;
		this.telephone = telephone;
	}
	public PharmacyInfo(PharmacyInfo phmcy) {
		this.pharmacyName = phmcy.pharmacyName;
		this.pharmacyAddress = phmcy.pharmacyAddress;
		this.registeredDate = phmcy.registeredDate;
		this.panNo = phmcy.panNo;
		this.telephone = phmcy.telephone;
	}
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	public String getPharmacyAddress() {
		return pharmacyAddress;
	}
	public void setPharmacyAddress(String pharmacyAddress) {
		this.pharmacyAddress = pharmacyAddress;
	}
	public String getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}
	public int getPanNo() {
		return panNo;
	}
	public void setPanNo(int panNo) {
		this.panNo = panNo;
	}
	public long getTelephone() {
		return telephone;
	}
	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}
	@Override
	public String toString() {
		return "PharmacyInfo [pharmacyName=" + pharmacyName + ", pharmacyAddress=" + pharmacyAddress
				+ ", registeredDate=" + registeredDate + ", panNo=" + panNo + ", telephone=" + telephone + "]";
	}
}
