package junit.model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.bean.CategoryBean;
import model.dao.CategoryDAO;

public class CategoryDAOTest {
	
	CategoryDAO categoryDAO = new CategoryDAO();

	@Test
	void testGetCategoryList() {
		
		// DBには 1 tops , 2 bottoms , 3 shoes を格納
		List <CategoryBean> list =  new ArrayList<>();
		try {
			list = categoryDAO.getCategoryList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String categoryName = "";
		int categoryId = 0;
		
		for( CategoryBean category : list) {
			categoryName += category.getCategoryName();
			categoryId += category.getCategoryId();	
		}
		
		assertEquals("topsbottomsshoes", categoryName);
		assertEquals(6, categoryId);
		
	}
	
	@Test // 同じ名前のカテゴリー名がなければtrue
	void testTrueGetCategory() {
		boolean category =  true;
		try {
			category = categoryDAO.getCategory("categoryTest");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(category, true);
	}

	@Test
	void testFalseGetCategory() {
		boolean category =  true;
		try {
			category = categoryDAO.getCategory("tops");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(category, false);
	}
	
	@Test // このカテゴリーの商品がなければtrue
	void testGetNoProductCategory() {
		boolean category =  true;
		try {
			category = categoryDAO.getNoProductCategory(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(category, true);
	}

	@Test
	void testTrueFalseGetFalseCategory() {
		boolean category =  true;
		try {
			category = categoryDAO.getNoProductCategory(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(category, false);
	}
	
// AddCategoryメソッドとDeleteCategoryは実際にDBに登録が必要になり、
// 毎回入力する値も違うため現在はコメントアウトしています。
	
//
//	@Test
//	void testAddCategory() {
//		int processingNum = 0;
//		try {
//			processingNum = categoryDAO.addCategory("追加テスト");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		assertEquals(1,processingNum);
//	}
//	
//	@Test //AddCategoryで登録したカテゴリーのcateroryIDをセットする
//	void testDeleteCategory() {
//		int processingNum = 0;
//		try {
//			processingNum = categoryDAO.deleteCategory(4);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		assertEquals(1,processingNum);
//	}

}
