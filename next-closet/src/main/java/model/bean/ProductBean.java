package model.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ProductBean extends InventoryBean implements Serializable{
	
	private int productId;
	private int categoryId;
	private int gender;
	private String productName;
	private int price;
	private String description;
	private boolean status;
	private String image;
	private Date registrationDate;
	
	public ProductBean() {}

	public ProductBean(int productId, int categoryId, int gender, String productName, int price, String description,
			boolean status, String image, Date registrationDate) {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
		this.gender = gender;
		this.productName = productName;
		this.price = price;
		this.description = description;
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

	public void setCategoryId(int categoryId) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	private List<SizeBean> sizes; // 商品のサイズのリスト

    // sizes のゲッターとセッター
    public List<SizeBean> getSizes() {
        return sizes;
    }

    public void setSizes(List<SizeBean> sizes) {
        this.sizes = sizes;
    }
    
    public void addSize(SizeBean size) {
        if (this.sizes == null) {
            this.sizes = new ArrayList<>();
        }
        this.sizes.add(size);
    }

	
}
