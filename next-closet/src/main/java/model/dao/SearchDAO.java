package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import connection.DBConnection;
import model.bean.AdminBean;
import model.bean.ProductBean;
import model.bean.UserBean;

public class SearchDAO {
	
	//　商品をproduct_nameの文字列で検索する機能
	public List<ProductBean> searchProductList(String searchName) 
			throws ClassNotFoundException, SQLException {
		List<ProductBean> productList = new ArrayList<>(); //検索の結果、複数ヒットした場合のためにListで格納
		String sql = "SELECT * FROM products WHERE status = 1 AND product_name LIKE ?"; //product_name用のsql文
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, "%" + searchName + "%");
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				int product_id = res.getInt("product_id");
				int category_id = res.getInt("category_id");
				int gender = res.getInt("gender");
				String product_name = res.getString("product_name");
				int price = res.getInt("price");
				String description = res.getString("description");
				boolean status = res.getBoolean("status");
				String image = res.getString("image");
				Date registration_date = res.getDate("registration_date");
				productList.add(new ProductBean (product_id, category_id, gender, product_name, price, description, status, image, registration_date));
			}	 
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                               ", SQLステート: " + e.getSQLState() + 
                               ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                               ", メッセージ: " + e.getMessage() + 
                               ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
		return productList;
	}
		
	//　商品を販売ステータス関係なくproduct_nameの文字列で検索する機能
	public List<ProductBean> searchStatusProductList(String searchName)
			throws ClassNotFoundException, SQLException {
		List<ProductBean> productList = new ArrayList<>();
		String sql = "SELECT * FROM products WHERE product_name LIKE ?"; //product_name用のsql文
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, "%" + searchName + "%");
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				int product_id = res.getInt("product_id");
				int category_id = res.getInt("category_id");
				int gender = res.getInt("gender");
				String product_name = res.getString("product_name");
				int price = res.getInt("price");
				String description = res.getString("description");
				boolean status = res.getBoolean("status");
				String image = res.getString("image");
				Date registration_date = res.getDate("registration_date");
				
				productList.add(new ProductBean (product_id, category_id, gender, product_name, price, description, status, image, registration_date));
			} 
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                               ", SQLステート: " + e.getSQLState() + 
                               ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                               ", メッセージ: " + e.getMessage() + 
                               ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
		return productList;
	}
		
	// ユーザーの検索機能
	public List< UserBean > searchStatusUserList(String searchName) 
			throws ClassNotFoundException, SQLException {
		 List< UserBean > list = new  ArrayList <UserBean>();
		String sql = "SELECT * FROM users WHERE user_name LIKE ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) { 
			pstmt.setString(1, "%" + searchName + "%");
			ResultSet res = pstmt.executeQuery();
			while (res.next()){ 
				int user_id = res.getInt("user_id");
		        String user_name = res.getString("user_name");
		        String kana_name = res.getString("kana_name");
		        String email = res.getString("email");
		        String hash_pass = res.getString("hash_pass");
		        Date registration_date = res.getDate("registration_date");
		        String tel_number = res.getString("tel_number");
		        boolean status = res.getBoolean("user_status");
		        
		        list.add(new UserBean (user_id, user_name,  kana_name, email, hash_pass , registration_date ,tel_number , status ));
		    }
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                               ", SQLステート: " + e.getSQLState() + 
                               ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                               ", メッセージ: " + e.getMessage() + 
                               ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
		return list;
	}
		
	// 検索した管理者の情報取得
	public List< AdminBean > searchStatusAdminList(String searchName) 
			throws ClassNotFoundException, SQLException {
		List< AdminBean > list = new  ArrayList <AdminBean>();
		String sql = "SELECT * FROM admins WHERE admin_name LIKE ? ";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) { 
			pstmt.setString(1, "%" + searchName + "%");
			ResultSet res = pstmt.executeQuery();
			while (res.next()){ 
				int admin_id = res.getInt("admin_id");
		        String admin_name = res.getString("admin_name");
		        String kana_name = res.getString("admin_kana_name");
		        String email = res.getString("email");
		        String hash_pass = res.getString("hash_pass");
		        boolean status = res.getBoolean("admin_status");
		        Date registration_date = res.getDate("registration_date"); 
		        
		        list.add(new AdminBean (admin_id, admin_name,  kana_name, email, hash_pass , status , registration_date ));
		    }
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                               ", SQLステート: " + e.getSQLState() + 
                               ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                               ", メッセージ: " + e.getMessage() + 
                               ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
		return list;
	}
		
}

