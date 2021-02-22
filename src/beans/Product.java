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
@Table(name="tbl_product")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="generic_name")
	@Size(min=3,message="Generic name must contain atleast 3 character.!!!")
	private String genericName;
	@Column(name="brand_name")
	@Size(min=3,message="Brand name must contain atleast 3 character.!!!")
	private String brandName;
	@Column(name="company_name")
	@Size(min=3,message="Company name must contain atleast 3 character.!!!")
	private String companyName;
	@Column(name="distributor")
	@Size(min=3,message="Distributor must contain atleast 3 character.!!!")
	private String distributor;
	@Column(name="manufacture_date")
	@NotNull(message="Date is Empty.!!")
	@Pattern(regexp = "^(?:[0-9]{2})?[0-9]{2}-[0-3]?[0-9]-[0-3]?[0-9]$",message="must match YYYY-MM-DD")
	private String manufactureDate;
	@Column(name="expire_date")
	@NotNull(message="Date is Empty.!!")
	@Pattern(regexp = "^(?:[0-9]{2})?[0-9]{2}-[0-3]?[0-9]-[0-3]?[0-9]$",message="must match YYYY-MM-DD")
	private String expireDate;
	@Column(name="cost_price")
	@NotNull(message="Cost Price is Empty.!!")
	private double costPrice;
	@Column(name="selling_price")
	@NotNull(message="Selling Price is Empty.!!")
	private double sellingPrice;
	@Column(name="quantity")
	@NotNull(message="Quantity is Empty.!!")
	private int quantity;
	@Column(name="delete_status")
	private boolean deleteStatus;
	public Product() {
		this.genericName = "";
		this.brandName = "";
		this.companyName = "";
		this.distributor = "";
		this.manufactureDate = "YYYY-MM-DD";
		this.expireDate = "YYYY-MM-DD";
		this.costPrice = 0;
		this.sellingPrice = 0;
		this.quantity = 0;
		this.deleteStatus = false;
	}
	public Product(String genericName, String brandName, String companyName, String distributor, String manufactureDate,
			String expireDate, double costPrice, double sellingPrice, int quantity, boolean deleteStatus) {
		this.genericName = genericName;
		this.brandName = brandName;
		this.companyName = companyName;
		this.distributor = distributor;
		this.manufactureDate = manufactureDate;
		this.expireDate = expireDate;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
		this.quantity = quantity;
		this.deleteStatus = deleteStatus;
	}
	public Product(Product p) {
		this.genericName = p.genericName;
		this.brandName = p.brandName;
		this.companyName = p.companyName;
		this.distributor = p.distributor;
		this.manufactureDate = p.manufactureDate;
		this.expireDate = p.expireDate;
		this.costPrice = p.costPrice;
		this.sellingPrice = p.sellingPrice;
		this.quantity = p.quantity;
		this.deleteStatus = p.deleteStatus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGenericName() {
		return genericName;
	}
	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDistributor() {
		return distributor;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	public String getManufactureDate() {
		return manufactureDate;
	}
	public void setManufactureDate(String manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	@Override
	public String toString() {
		return "Product [genericName=" + genericName + ", brandName=" + brandName + ", companyName=" + companyName
				+ ", distributor=" + distributor + ", manufactureDate=" + manufactureDate + ", expireDate=" + expireDate
				+ ", costPrice=" + costPrice + ", sellingPrice=" + sellingPrice + ", quantity=" + quantity
				+ ", deleteStatus=" + deleteStatus + "]";
	}
}
