package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import model.bean.ProductBean;

public class SearchDAO {
	
	//　商品をproduct_nameの文字列で検索する機能
	public List<ProductBean> searchProductList(String searchName) 
			throws ClassNotFoundException, SQLException {
		
		List<ProductBean> productList = new ArrayList<>();
		
		//product_name用のsql文
		String sql = "SELECT * FROM products WHERE status = 1 AND product_name LIKE ?";
		
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
		}
		return productList;
		
	}
		
		//　商品を販売ステータス関係なくproduct_nameの文字列で検索する機能
		public List<ProductBean> searchStatusProductList(String searchName) throws ClassNotFoundException, SQLException {
			
			List<ProductBean> productList = new ArrayList<>();
			
			//product_name用のsql文
			String sql = "SELECT * FROM products WHERE product_name LIKE ?";
			
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
			}
			return productList;
		}
	}

