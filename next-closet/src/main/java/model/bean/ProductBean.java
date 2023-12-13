package model.bean;

import java.io.Serializable;
import java.sql.Date;

public class ProductBean implements Serializable{
	
	private int productId;
	private int categoryid;
	private int gender;
	private String productName;
	private int price;
	private String descruption;
	private boolean status;
	private String image;
	private Date registrationDate;
	
	public ProductBean() {}

	public ProductBean(int productId, int categoryid, int gender, String productName, int price, String descruption,
			boolean status, String image, Date registrationDate) {
		super();
		this.productId = productId;
		this.categoryid = categoryid;
		this.gender = gender;
		this.productName = productName;
		this.price = price;
		this.descruption = descruption;
		this.status = status;
		this.image = image;
		this.registrationDate = registrationDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescruption() {
		return descruption;
	}

	public void setDescruption(String descruption) {
		this.descruption = descruption;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	@Override
    public String toString() {
        return "ProductBean{" +
               "productName='" + productName + '\'' +
               ", price=" + price +
               ", image='" + image + '\'' +
               '}';
    }
	
}
