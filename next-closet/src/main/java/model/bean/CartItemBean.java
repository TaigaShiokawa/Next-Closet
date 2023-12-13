package model.bean;

import java.io.Serializable;

public class CartItemBean implements Serializable{
	
	private int cartItemId;
	private int productId;
	private int quantity;
	private int sizeId;
	
	public CartItemBean() {}
	
	public CartItemBean(int cartItemId, int productId, int quantity , int sizeId) {
		this.cartItemId = cartItemId;
		this.productId = productId;
		this.quantity = quantity;
		this.sizeId = sizeId;
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
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

}
