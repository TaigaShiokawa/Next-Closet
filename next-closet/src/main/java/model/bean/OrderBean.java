package model.bean;

import java.sql.Date;

public class OrderBean extends AddressBean{
	private int orderItemId;
	private int productId;
	private int quantity;
	private int sizeId;
	private int userId;
	private int totalAmount;
	private Date orderDate;
	private String deliveryAddress;
	private boolean status;
	
	public OrderBean(int orderItemId, int productId, int quantity, int sizeId, int userId, int totalAmount,
			Date orderDate, String deliveryAddress) {
		super();
		this.orderItemId = orderItemId;
		this.productId = productId;
		this.quantity = quantity;
		this.sizeId = sizeId;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.orderDate = orderDate;
		this.deliveryAddress = deliveryAddress;
	}
	
	public OrderBean() {};

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int ordeeItemId) {
		this.orderItemId = ordeeItemId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSizeId() {
		return sizeId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
