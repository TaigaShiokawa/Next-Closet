package model.bean;

import java.io.Serializable;

public class CartBean implements Serializable{
	
	private int cartId;
	private int userId;
	
	public CartBean() {}
	
	public CartBean(int cartId, int userId) {
		this.cartId = cartId;
		this.userId = userId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCart_id(int cartId) {
		this.cartId = cartId;
	}

	public int getUser_id() {
		return userId;
	}

	public void setUser_id(int userId) {
		this.userId = userId;
	}
	
	

}
