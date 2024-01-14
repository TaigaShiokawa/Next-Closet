package junit.model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.bean.AdminBean;
import model.bean.ProductBean;
import model.bean.UserBean;
import model.dao.SearchDAO;

public class SearchDAOTest {
	
	SearchDAO searchDAO = new SearchDAO();

	@Test // DBによって値が変わります。下記テストはDBに該当する値がある場合
	void testTrueSearchProductList() {
		
		List <ProductBean> list =  new ArrayList<>();
		try {
			list = searchDAO.searchProductList("tops");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int categoryId = 0;
		
		for( ProductBean product : list) {
			categoryId += product.getProductId();	
		}
		
		assertNotEquals(0, categoryId);
		
	}
	
	@Test // DBによって値が変わります。下記テストはDBに該当する値がない場合
	void testFalseSearchProductList() {
		
		List <ProductBean> list =  new ArrayList<>();
		try {
			list = searchDAO.searchProductList("存在しない商品名");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int categoryId = 0;
		
		for( ProductBean product : list) {
			categoryId += product.getProductId();	
		}
		
		assertEquals(0, categoryId);
		
	}
	
	@Test // 販売中の商品検索 DBによって値が変わります。下記テストはDBに該当する値がある場合
	void testTrueSearchStatusProductList() {
		
		List <ProductBean> list =  new ArrayList<>();
		try {
			list = searchDAO.searchStatusProductList("tops");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int categoryId = 0;
		
		for( ProductBean product : list) {
			categoryId += product.getProductId();	
		}
		
		assertNotEquals(0, categoryId);
		
	}
	
	@Test // 全ステータスの商品検索 DBによって値が変わります。下記テストはDBに該当する値がない場合
	void testFalseSearchStatusProductList() {
		
		List <ProductBean> list =  new ArrayList<>();
		try {
			list = searchDAO.searchStatusProductList("存在しない商品名");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int categoryId = 0;
		
		for( ProductBean product : list) {
			categoryId += product.getProductId();	
		}
		
		assertEquals(0, categoryId);
		
	}
	
	@Test // ユーザー検索 DBによって値が変わります。下記テストはDBに該当する値がある場合
	void testTrueSearchStatusUserList() {
		
		List <UserBean> list =  new ArrayList<>();
		try {
			list = searchDAO.searchStatusUserList("山田");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int userId = 0;
		
		for( UserBean user : list) {
			userId += user.getUserId();	
		}
		
		assertNotEquals(0, userId);
		
	}
	
	@Test // ユーザー検索 DBによって値が変わります。下記テストはDBに該当する値がある場合
	void testFalseSearchStatusUserList() {
		
		List <UserBean> list =  new ArrayList<>();
		try {
			list = searchDAO.searchStatusUserList("存在しないユーザー名");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int userId = 0;
		
		for( UserBean user : list) {
			userId += user.getUserId();	
		}
		
		assertEquals(0, userId);
		
	}
	
	@Test // ユーザー検索 DBによって値が変わります。下記テストはDBに該当する値がある場合
	void testTrueSearchStatusAdminList() {
		
		List <AdminBean> list =  new ArrayList<>();
		try {
			list = searchDAO.searchStatusAdminList("山田");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int adminId = 0;
		
		for( AdminBean admin : list) {
			adminId += admin.getAdminId();	
		}
		
		assertNotEquals(0, adminId);
		
	}
	
	@Test // ユーザー検索 DBによって値が変わります。下記テストはDBに該当する値がある場合
	void testFalseSearchStatusAdminList() {
		
		List <AdminBean> list =  new ArrayList<>();
		try {
			list = searchDAO.searchStatusAdminList("存在しない管理者名");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int adminId = 0;
		
		for( AdminBean admin : list) {
			adminId += admin.getAdminId();	
		}
		
		assertEquals(0, adminId);
		
	}
	
	
	

}
