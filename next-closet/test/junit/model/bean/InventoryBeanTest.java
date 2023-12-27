package junit.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.bean.InventoryBean;

class InventoryBeanTest extends InventoryBean {

	@Test
	void testInventoryBean() {
		
		InventoryBean inventoryBean = new InventoryBean();
		
		//テストデータ
		int inventoryId = 1;
		int productId = 2;
		int sizeId = 3;
		int stockQuantity = 4;
		
		//値をセット
		inventoryBean.setInventoryId(inventoryId);
		inventoryBean.setProductId(productId);
		inventoryBean.setSizeId(sizeId);
		inventoryBean.setStockQuantity(stockQuantity);
		
		//結果
		assertEquals(inventoryId, inventoryBean.getInventoryId());
		assertEquals(productId, inventoryBean.getProductId());
		assertEquals(sizeId, inventoryBean.getSizeId());
		assertEquals(stockQuantity, inventoryBean.getStockQuantity());
	}

}
