package model.bean;

import java.io.Serializable;
import java.sql.Date;

public class ProductBean implements Serializable{
	
	private int productId;
	private int categoryId;
	private int gender;
	private String productName;
	private int price;
	private String descruption;
	private boolean status;
	private String image;
	private Date registrationDate;
	
	public ProductBean() {}

	public ProductBean(int productId, int categoryId, int gender, String productName, int price, String descruption,
			boolean status, String image, Date registrationDate) {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryid(int categoryId) {
		this.categoryId = categoryId;
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

	public boolean getStatus() {
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
