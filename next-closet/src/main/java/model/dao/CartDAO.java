package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import connection.DBConnection;
import model.bean.CartItemBean;
import model.bean.ProductBean;

public class CartDAO {
	
	//商品をカートに入れる処理
	public int addCartItem(HttpServletRequest request, int productId, int quantity) 
			throws ClassNotFoundException, SQLException{
		int processingNum = 0;
		String sql = "INSERT INTO cart_items (cart_id, product_id, quantity) VALUES (?, ?, ?)";
		int userId = (int) request.getSession().getAttribute("user_id");
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, userId); //セッションのuser_idをcart_idに保存
			pstmt.setInt(2, productId);
			pstmt.setInt(3, quantity);
			processingNum = pstmt.executeUpdate();
		}
		return processingNum;
	}
	
	//ユーザーの買い物かご一覧を表示する処理
	public List<CartItemBean> getCartItems(int userId) 
			throws ClassNotFoundException, SQLException {
		List<CartItemBean> cartItems = new ArrayList<>();
		
		String sql ="SELECT ci.cart_item_id, ci.quantity, p.product_name, p.price, p.image, s.size_name"
				+ "FROM cart_items ci"
				+ "INNER JOIN products p ON ci.product_id = p.product_id"
				+ "INNER JOIN sizes s ON ci.size_id = s.size_id"
				+ "WHERE ci.user_id = ?";
		
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			try (ResultSet res = pstmt.executeQuery()) {
				while (res.next()) {
					CartItemBean cartItem = new CartItemBean();
					cartItem.setCartItemId(res.getInt("cart_item_id"));
					cartItem.setQuantity(res.getInt("quantity"));
					
					ProductBean product = new ProductBean();
					
					
					
				}
			}
		}
		return cartItems;
	}
	
	
	
	
	
}
