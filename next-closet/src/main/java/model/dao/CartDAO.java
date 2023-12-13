package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
<<<<<<< Updated upstream
import java.sql.SQLException;
=======
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
>>>>>>> Stashed changes

import javax.servlet.http.HttpServletRequest;

import connection.DBConnection;
<<<<<<< Updated upstream
=======
import model.bean.CartItemBean;
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
=======
	
	//一つの商品のみカートから購入するメソッド
		public int cartItem( int cart_item_id ) throws ClassNotFoundException, SQLException{
			int cartItemId = 0;
			CartItemBean cartItem = new CartItemBean();
			String sql = "SELECT * FROM cart_items WHERE cart_item_id = ?";
			try (Connection con = DBConnection.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setInt(1, cart_item_id); //セッションのuser_idをcart_idに保存
				
				ResultSet res = pstmt.executeQuery();
				while(res.next()) {
					cartItem.setCartItemId(res.getInt("cart_item_id"));
					cartItem.setProductId(res.getInt("product_id"));
					cartItem.setQuantity(res.getInt("quantity"));
					cartItem.setSizeId(res.getInt("size_id"));
				}
			}
			return cartItemId;
		}
		

		//一つの商品のみカートから購入するメソッド
			public List <CartItemBean> cartAllItem(HttpServletRequest request ) throws ClassNotFoundException, SQLException{
				int userId = (int) request.getSession().getAttribute("user_id");
				 List <CartItemBean> cartList = new ArrayList<>();
			
				
				String sql = "SELECT * FROM cart_items WHERE user_id = ?";
				try (Connection con = DBConnection.getConnection();
						PreparedStatement pstmt = con.prepareStatement(sql)) {
					pstmt.setInt(1, userId); //セッションのuser_idをcart_idに保存
					
					ResultSet res = pstmt.executeQuery();
					while(res.next()) {
						int cart_item_id = res.getInt("cart_item_id");
						int product_id = res.getInt("product_id");
						int quantity = res.getInt("quantity");
						int size_id= res.getInt("size_id");
		
						cartList.add(new CartItemBean ( cart_item_id , product_id,  quantity , size_id ));
					}
				}
				return cartList;
			}
			
>>>>>>> Stashed changes
}
