package junit.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.bean.CategoryBean;

class CategoryBeanTest {

	@Test
	void testCategoryBean() {
		CategoryBean categoryBean = new CategoryBean();
		
		//テストデータ
		int categoryId = 1;
		String categoryName = "テスト";
		
		//値をセット
		categoryBean.setCategoryId(categoryId);
		categoryBean.setCategoryName(categoryName);
		
		assertEquals(categoryId, categoryBean.getCategoryId());
		assertEquals(categoryName, categoryBean.getCategoryName());
	}

}
