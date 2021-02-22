package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(name="tbl_bill")
public class Bill {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@NotNull(message="Empty Bill No")
	@Column(name="bill_no")
	private long billNo;
	@Column(name="billed_date")
	private String billedDate;
	@Size(min=3,max=50,message="Doctor's Name must be 3 to 50 char.!!")
	@Column(name="doctor_name")
	private String doctorName;
	@Size(min=3,max=50,message="Patient's Name must be 3 to 50 char.!!")
	@Column(name="patient_name")
	private String patientName;
	@Size(min=3,max=100,message="Doctor Name must be 3 char.!!")
	@Column(name="patient_address")
	private String patientAddress;
	@NotNull(message="Select the Gender.!!")
	@Column(name="gender")
	private char gender;
	@Digits(integer=10,fraction=0,message="Contact No must be 10 digits.!!")
	@Min(value=1000000000,message="Contact No must be 10 digits")
	@Column(name="contact_no")
	private long contactNo;
	//@DecimalMin(value="0.0",message="Must be atleast 0.0")
	@Column(name="discount")
	private float discount;
	//@DecimalMin(value="0.0",message="Must be atleast 0.0")
	@Column(name="grand_total")
	private float grandTotal;
	@NotNull(message="Medicines Empty..!!")
	@Column(name="medicines")
	private String medicines;
	@NotNull(message="Quantities Empty..!!")
	@Column(name="quantities")
	private String quantities;
	@Column(name="rates")
	@NotNull(message="Rates Empty..!!")
	private String rates;
	@Column(name="totals")
	@NotNull(message="Totals Empty..!!")
	private String totals;
	@Size(min=3,max=50,message="Pharmacist Name Must between 3 to 50 character")
	@Column(name="soldby")
	private String seller;
	@Column(name="file")
	private byte[] fileData;
	@Column(name="file_name")
	private String fileName;
	public Bill() {
		this.id = 1;
		this.billNo = 190;
		this.doctorName = "";
		this.patientName = "";
		this.patientAddress = "";
		this.gender = 'M';
		this.contactNo = 0;
		this.discount = 0.0f;
		this.grandTotal = 0.0f;
		this.medicines = "";
		this.quantities = "";
		this.rates = "";
		this.totals = "";
		this.seller = "";
		this.billedDate="";
	}
	public Bill(String billedDate,int id, long billNo, String doctorName, String patientName, String patientAddress, char gender,
			long contactNo, float discount, float grandTotal, String medicines, String quantities, String rates,
			String totals, String seller) {
		this.id = id;
		this.billNo = billNo;
		this.doctorName = doctorName;
		this.patientName = patientName;
		this.patientAddress = patientAddress;
		this.gender = gender;
		this.contactNo = contactNo;
		this.discount = discount;
		this.grandTotal = grandTotal;
		this.medicines = medicines;
		this.quantities = quantities;
		this.rates = rates;
		this.totals = totals;
		this.seller = seller;
		this.billedDate = billedDate;
	}
	public Bill(Bill bil) {
		this.id = bil.id;
		this.billNo = bil.billNo;
		this.doctorName = bil.doctorName;
		this.patientName = bil.patientName;
		this.patientAddress = bil.patientAddress;
		this.gender = bil.gender;
		this.contactNo = bil.contactNo;
		this.discount = bil.discount;
		this.grandTotal = bil.grandTotal;
		this.medicines = bil.medicines;
		this.quantities = bil.quantities;
		this.rates = bil.rates;
		this.totals = bil.totals;
		this.seller = bil.seller;
		this.billedDate = bil.billedDate;
	}
	public void setBilledDate(String date) {
		this.billedDate = date;
	}
	public String getBilledDate() {
		return billedDate;
	}
	public int getId() {
		return id;
	}
	public long getBillNo() {
		return billNo;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public String getPatientName() {
		return patientName;
	}
	public String getPatientAddress() {
		return patientAddress;
	}
	public char getGender() {
		return gender;
	}
	public long getContactNo() {
		return contactNo;
	}
	public float getDiscount() {
		return discount;
	}
	public float getGrandTotal() {
		return grandTotal;
	}
	public String getMedicines() {
		return medicines;
	}
	public String getQuantities() {
		return quantities;
	}
	public String getRates() {
		return rates;
	}
	public String getTotals() {
		return totals;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setBillNo(long billNo) {
		this.billNo = billNo;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}
	public void setMedicines(String medicines) {
		this.medicines = medicines;
	}
	public void setQuantities(String quantities) {
		this.quantities = quantities;
	}
	public void setRates(String rates) {
		this.rates = rates;
	}
	public void setTotals(String totals) {
		this.totals = totals;
	}
	
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "Bill [billDate="+billedDate+",id=" + id + ", billNo=" + billNo + ", doctorName=" + doctorName + ", patientName=" + patientName
				+ ", patientAddress=" + patientAddress + ", gender=" + gender + ", contactNo=" + contactNo
				+ ", discount=" + discount + ", grandTotal=" + grandTotal + ", medicines=" + medicines + ", quantities="
				+ quantities + ", rates=" + rates + ", totals=" + totals + ", soldBy=" + seller + ",fileName="+fileName+"]";
	}
}
