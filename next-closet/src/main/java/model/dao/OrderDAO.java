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
	
	//productIdからproductNameを取得
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
	
	//userIdからuserNameを取得
		public String getUserName (int userId)  {
			
			String userName= null;
			String sql = "SELECT product_name FROM users WHERE user_id = ?";
			
			try (Connection con = DBConnection.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)) {
					pstmt.setInt(1, userId);
					ResultSet res = pstmt.executeQuery();
					while (res.next()) {
						userName = res.getString("user_name");
					}	
			} catch (SQLException | ClassNotFoundException e){
			 e.printStackTrace();
		 }
	     
			return userName;
		}
	
	
	//オーダー全て取得
		
	public List<OrderBean> getAllOrderList() throws ClassNotFoundException, SQLException {
		
		List<OrderBean> orderList = new ArrayList<>();
		
		//product_name用のsql文
		String sql = "SELECT * FROM order_items";
		
		
		try (Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet res = pstmt.executeQuery();
		
			while (res.next()) {
				
				 int order_item_id = res.getInt("order_item_id");
				 int product_id = res.getInt("product_id");
				 int quantity = res.getInt("quantity");
				 int size_id = res.getInt("size_id");
				 int user_id = res.getInt("user_id");
				 int total_amount = res.getInt("total_amount");
				 Date order_date = res.getDate("order_date");
				 String delivery_address = res.getString("delivery_address"); 
				
				orderList.add(new OrderBean (order_item_id ,product_id, quantity, size_id, user_id, total_amount, order_date, delivery_address));
			}	
		}
		return orderList;
	}
	
	//特定のオーダー情報取得
public List<OrderBean> getOrderDetailList( int orderItemId) throws ClassNotFoundException, SQLException {
		
		List<OrderBean> orderList = new ArrayList<>();
		
		//product_name用のsql文
		String sql = "SELECT * FROM order_items WHERE order_item_id = ?";
		
		
		try (Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, orderItemId);
			ResultSet res = pstmt.executeQuery();
		
			while (res.next()) {
				
				 int order_item_id = res.getInt("order_item_id");
				 int product_id = res.getInt("product_id");
				 int quantity = res.getInt("quantity");
				 int size_id = res.getInt("size_id");
				 int user_id = res.getInt("user_id");
				 int total_amount = res.getInt("total_amount");
				 Date order_date = res.getDate("order_date");
				 String delivery_address = res.getString("delivery_address"); 
				
				orderList.add(new OrderBean (order_item_id ,product_id, quantity, size_id, user_id, total_amount, order_date, delivery_address));
			}	
		}
		return orderList;
	}

   //productIdからimgの情報を返信
   public String getProductImage(int productId) {
	   
	   String sql = "SELECT image FROM products WHERE product_id = ?";
	   String img = null;
		
		try (Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, productId);
			ResultSet res = pstmt.executeQuery();
			
			while (res.next()) {
				img = res.getString("image");
			}
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		return img;
	   
   }
	
	
	
	
	
	
}



