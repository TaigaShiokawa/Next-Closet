package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import model.bean.OrderBean;

public class OrderDAO {
	
	//　購入通知をとってくる
	public List<OrderBean> orderList( int userId ) throws ClassNotFoundException, SQLException {
		
		List<OrderBean> orderList = new ArrayList<>();
		
		//product_name用のsql文
		String sql = "SELECT * FROM order_items WHERE user_id = ?";
		int order_item_id = 0;
		int product_id = 0;
		int quantity = 0;
		int size_id = 0;
		int total_amount = 0;
		Date order_date = null;
		String delivery_address = null;
		
		try (Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			ResultSet res = pstmt.executeQuery();
		
			while (res.next()) {
				
				order_item_id = res.getInt("order_item_id");
				product_id = res.getInt("product_id");
				quantity = res.getInt("quantity");
				size_id = res.getInt("size_id");
				total_amount = res.getInt("total_amount");
				order_date = res.getDate("order_date");
				delivery_address = res.getString("delivery_address");
				
				orderList.add(new OrderBean (order_item_id ,product_id, quantity, size_id, userId, total_amount, order_date, delivery_address));
			}	
		}
		return orderList;
	}
	
	public String getProductName (int productId)  {
		
		String productName= null;
		String sql = "SELECT product_name FROM products WHERE product_id = ?";
		
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setInt(1, productId);
				ResultSet res = pstmt.executeQuery();
				while (res.next()) {
					productName = res.getString("product_name");
				}	
		} catch (SQLException | ClassNotFoundException e){
		 e.printStackTrace();
	 }
     
		return productName;
	}
}


