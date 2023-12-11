package model.bean;

import java.io.Serializable;
import java.sql.Date;

public class ProductBean implements Serializable{
	
	private int product_id;
	private int category_id;
	private int gender;
	private String product_name;
	private int price;
	private String descruption;
	private boolean status;
	private String image;
	private Date registration_date;
	
	public ProductBean() {}
	
	public ProductBean(int product_id, int category_id, int gender, String product_name, int price, String descruption,
			boolean status, String image, Date registration_date) {

		this.product_id = product_id;
		this.category_id = category_id;
		this.gender = gender;
		this.product_name = product_name;
		this.price = price;
		this.descruption = descruption;
		this.status = status;
		this.image = image;
		this.registration_date = registration_date;
	}

	public int getProduct_id() {
		return product_id;
	}
	
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	public int getCategory_id() {
		return category_id;
	}
	
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	
	public int getGender() {
		return gender;
	}
	
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public String getProduct_name() {
		return product_name;
	}
	
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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
	
	public Date getRegistration_date() {
		return registration_date;
	}
	
	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}
	
	
	
}
