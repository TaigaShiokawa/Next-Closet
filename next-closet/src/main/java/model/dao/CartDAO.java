package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import connection.DBConnection;

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
}
