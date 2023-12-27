package junit.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.bean.ProductBean;

class ProductBeanTest extends ProductBean {

	@Test
	void testProductBean() {
		
		ProductBean productBean = new ProductBean();
		
		//テストデータ
		int productId = 1;
		int categoryId = 2;
		int gender = 1;
		String productName = "テスト";
		int price= 10000;
		String description = "テストテストテスト";
		boolean status = true;
		String image = "https://placehold.jp/150x150.png";
		
		//値をセット
		productBean.setProductId(productId);
		productBean.setCategoryId(categoryId);
		productBean.setGender(gender);
		productBean.setProductName(productName);
		productBean.setPrice(price);
		productBean.setDescription(description);
		productBean.setStatus(status);
		productBean.setImage(image);
		
		//結果
		assertEquals(productId, productBean.getProductId());
		assertEquals(categoryId, productBean.getCategoryId());
		assertEquals(gender, productBean.getGender());
		assertEquals(productName, productBean.getProductName());
		assertEquals(price, productBean.getPrice());
		assertEquals(description, productBean.getDescription());
		assertEquals(status, productBean.isStatus());
		assertEquals(image, productBean.getImage());
		
	}

}
