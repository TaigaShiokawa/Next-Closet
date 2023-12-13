package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
