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
import model.bean.OrderBean;

public class OrderDAO {
	
	//購入通知を取得する
	public List<OrderBean> orderList( int userId )
			throws ClassNotFoundException, SQLException {
		List<OrderBean> orderList = new ArrayList<>();
		String sql ="SELECT next_closet_db.oi.* , p.status FROM order_items oi  INNER JOIN products p ON p.product_id = oi.product_id WHERE user_id = ? ORDER BY order_date DESC";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				OrderBean order = new OrderBean();
				order.setOrderItemId(res.getInt("order_item_id"));
				order.setProductId(res.getInt("product_id"));
				order.setQuantity(res.getInt("quantity"));
				order.setSizeId(res.getInt("size_id"));
				order.setTotalAmount(res.getInt("total_amount"));
				order.setOrderDate(res.getDate("order_date"));
				order.setDeliveryAddress(res.getString("delivery_address"));
				order.setStatus(res.getBoolean("status"));
				orderList.add(order);
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
		return orderList;
	}
	
	//productIdからproductNameを取得
	public String getProductName (int productId) {
		String productName= null;
		String sql = "SELECT product_name FROM products WHERE product_id = ?";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, productId);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				productName = res.getString("product_name");
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
		return productName;
	}
	
	//userIdからuserNameを取得
	public String getUserName (int userId) {
		String userName= null;
		String sql = "SELECT user_name FROM users WHERE user_id = ?";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				userName = res.getString("user_name");
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
		return userName;
	}
	
	//オーダー全て取得
	public List<OrderBean> getAllOrderList()
			throws ClassNotFoundException, SQLException {
		List<OrderBean> orderList = new ArrayList<>();
		String sql = "SELECT * FROM order_items ORDER BY order_date DESC"; //product_name用のsql文
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
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                               ", SQLステート: " + e.getSQLState() + 
                               ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                               ", メッセージ: " + e.getMessage() + 
                               ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
		return orderList;
	}
	
	//特定のオーダー情報取得
	public List<OrderBean> getOrderDetailList( int orderItemId)
			throws ClassNotFoundException, SQLException {
		List<OrderBean> orderList = new ArrayList<>();
		String sql = "SELECT * FROM order_items WHERE order_item_id = ?"; //product_name用のsql文
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
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                               ", SQLステート: " + e.getSQLState() + 
                               ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                               ", メッセージ: " + e.getMessage() + 
                               ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
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
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                               ", SQLステート: " + e.getSQLState() + 
                               ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                               ", メッセージ: " + e.getMessage() + 
                               ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
		return img;
    }

}