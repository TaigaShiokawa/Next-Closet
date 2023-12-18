package model.bean;

import java.io.Serializable;


public class SizeBean implements Serializable{
	
	private int sizeId;
	private String sizeName;
	
	public SizeBean() {}
	
	public SizeBean(int sizeId, String sizeName) {
		this.sizeId = sizeId;
		this.sizeName = sizeName;
	}

	public int getSizeId() {
		return sizeId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	
	
	private int stockQuantity; // 在庫数量

    // stockQuantity のゲッターとセッター
    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

	
	@Override
    public String toString() {
        return "SizeBean{" +
               "sizeName='" + sizeName + '\'' +
               '}';
    }

}
