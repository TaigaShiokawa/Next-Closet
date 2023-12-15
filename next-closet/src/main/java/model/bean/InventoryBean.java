package model.bean;

public class InventoryBean {
	
	private int inventoryId;
	private int productId;
	private int sizeId;
	private int stockQuantity;
	
	public InventoryBean() {}
	
	public InventoryBean(int inventoryId, int productId, int sizeId, int stockQuantity) {
		this.inventoryId = inventoryId;
		this.productId = productId;
		this.sizeId = sizeId;
		this.stockQuantity = stockQuantity;
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSizeId() {
		return sizeId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	

}
