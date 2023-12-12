package model.bean;

import java.io.Serializable;

public class CartItemBean implements Serializable{
	
	private int cartItemId;
	private int cartId;
	private int productId;
	private int quantity;
	
	public CartItemBean() {}
	
	public CartItemBean(int cartItemId, int cartId, int productId, int quantity) {
		this.cartItemId = cartItemId;
		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
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
	
	private ProductBean product;
	private SizeBean size;
	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

	public SizeBean getSize() {
		return size;
	}

	public void setSize(SizeBean size) {
		this.size = size;
	}
	
	
}
