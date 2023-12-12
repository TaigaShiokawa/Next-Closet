package model.bean;

import java.io.Serializable;

public class CartItemBean implements Serializable{
	
	private int cartItemId;
	private int userId;
	private int productId;
	private int quantity;
	
	public CartItemBean() {}
	
	public CartItemBean(int cartItemId, int userId, int productId, int quantity) {
		this.cartItemId = cartItemId;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
	
	@Override
    public String toString() {
        return "CartItemBean{" +
                "cartItemId=" + cartItemId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", product=" + (product != null ? product.toString() : "null") +
                ", size=" + (size != null ? size.toString() : "null") +
                '}';
    }
	
}
