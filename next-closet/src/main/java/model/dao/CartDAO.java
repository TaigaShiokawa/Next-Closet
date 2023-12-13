package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import connection.DBConnection;

import model.bean.CartItemBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import model.bean.CartItemBean;
import model.bean.ProductBean;
import model.bean.SizeBean;



public class CartDAO {
	
	//商品をカートに入れる処理
	public int addCartItem(int userId, int productId, int sizeId, int quantity) 
			throws ClassNotFoundException, SQLException{
		int processingNum = 0;
		String sql = "INSERT INTO cart_items (user_id, product_id, size_id, quantity) VALUES (?, ?, ?, ?)";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, productId);
			pstmt.setInt(3, sizeId);
			pstmt.setInt(4, quantity);
			processingNum = pstmt.executeUpdate();
		}
		return processingNum;
	}

	
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
			

	
	public List<CartItemBean> getCartItems(int userId) 
	        throws ClassNotFoundException, SQLException {
	    List<CartItemBean> cartItems = new ArrayList<>();
	    
	    String sql = "SELECT ci.cart_item_id, ci.quantity, p.product_name, p.price, p.image, s.size_name "
	               + "FROM cart_items ci "
	               + "INNER JOIN products p ON ci.product_id = p.product_id "
	               + "INNER JOIN sizes s ON ci.size_id = s.size_id "
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
	                product.setProductName(res.getString("product_name"));
	                product.setPrice(res.getInt("price"));
	                product.setImage(res.getString("image"));
	                
	                SizeBean size = new SizeBean();
	                size.setSizeName(res.getString("size_name"));
	                
	                cartItem.setProduct(product);
	                cartItem.setSize(size);
	                
	                cartItems.add(cartItem);
	            }
	        }
	    }
	    return cartItems;
	}
	
	

}
