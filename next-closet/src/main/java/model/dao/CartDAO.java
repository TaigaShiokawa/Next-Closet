package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                               ", SQLステート: " + e.getSQLState() + 
                               ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                               ", メッセージ: " + e.getMessage() + 
                               ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
		return processingNum;
	}
	
	//一つの商品のみカートから購入するメソッド
	public CartItemBean getCartItem( int cart_item_id )
			throws ClassNotFoundException, SQLException{
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
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                               ", SQLステート: " + e.getSQLState() + 
                               ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                               ", メッセージ: " + e.getMessage() + 
                               ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
		return cartItem;
	}
	
	//全ての商品のみカートから購入するメソッド
	public List <CartItemBean> cartAllItem(HttpServletRequest request )
			throws ClassNotFoundException, SQLException{
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
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                               ", SQLステート: " + e.getSQLState() + 
                               ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                               ", メッセージ: " + e.getMessage() + 
                               ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
		return cartList;
	}
	
	//ユーザーIDを基に商品の詳細情報を取得
	public List<CartItemBean> getCartItems(int userId) 
	        throws ClassNotFoundException, SQLException {
	    List<CartItemBean> cartItems = new ArrayList<>();
	    String sql = "SELECT ci.cart_item_id, ci.quantity, p.product_name, p.product_id, p.price, p.image, p.status, s.size_name "
	               + "FROM cart_items ci "
	               + "INNER JOIN products p ON ci.product_id = p.product_id "
	               + "INNER JOIN sizes s ON ci.size_id = s.size_id "
	               + "WHERE ci.user_id = ?";
	    try (Connection con = DBConnection.getConnection();
	    		PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setInt(1, userId);
	        try (ResultSet res = pstmt.executeQuery()) {
	            while (res.next()) {
	            	boolean status = res.getBoolean("status");
	            	
	            	if(status) { //販売中商品だったらリストに追加する
	            		
	            		//下記、3つのインスタンス化しているオブジェクトは、Beanファイルでそれぞれを継承させたら少し短くなるかも
		                CartItemBean cartItem = new CartItemBean();
		                cartItem.setCartItemId(res.getInt("cart_item_id"));
		                cartItem.setQuantity(res.getInt("quantity"));
		                cartItem.setSizeName(res.getString("size_name"));
		                
		                ProductBean product = new ProductBean();
		                product.setProductName(res.getString("product_name"));
		                product.setProductId(res.getInt("product_id"));
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
	    } catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                               ", SQLステート: " + e.getSQLState() + 
                               ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                               ", メッセージ: " + e.getMessage() + 
                               ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
	    return cartItems;
	}
	
	//商品を削除する
	public void destroyCartItem(int cartItemId) 
			throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM cart_items WHERE cart_item_id = ?"; 
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {	
		pstmt.setInt(1, cartItemId);
		pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                               ", SQLステート: " + e.getSQLState() + 
                               ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                               ", メッセージ: " + e.getMessage() + 
                               ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
	}
	
	//カート内の商品の数量を変更する
	public void UpdateCarItemQuantity(int cart_item_id, int quantity)
			throws ClassNotFoundException, SQLException {
		String sql = "UPDATE cart_items SET quantity = ? WHERE cart_item_id = ?";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, cart_item_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                               ", SQLステート: " + e.getSQLState() + 
                               ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                               ", メッセージ: " + e.getMessage() + 
                               ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
	}
	
	public void destroyAllCartItem(int userId) 
			throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM cart_items WHERE user_id = ?";
		
		try (Connection con = DBConnection.getConnection();
		     PreparedStatement pstmt = con.prepareStatement(sql)) {	
			
		pstmt.setInt(1, userId);
		pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                               ", SQLステート: " + e.getSQLState() + 
                               ", エラーコード: " + e.getErrorCode());
		} catch (Exception e) {
			System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                               ", メッセージ: " + e.getMessage() + 
                               ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
	}
	
	//オーダーに登録する前にカートおの情報をとってくるユーザーIDを基に商品の詳細情報を取得
	public List<CartItemBean> getAllCartItems(int userId) 
		        throws ClassNotFoundException, SQLException {
		List<CartItemBean> cartItems = new ArrayList<>();
		String sql = "SELECT ci.cart_item_id, ci.quantity, p.product_id, p.product_name, p.price, p.image, s.size_id "
		           + "FROM cart_items ci "
		           + "INNER JOIN products p ON ci.product_id = p.product_id "
		           + "INNER JOIN sizes s ON ci.size_id = s.size_id "
		           + "WHERE ci.user_id = ?";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
		    pstmt.setInt(1, userId);
		    try (ResultSet res = pstmt.executeQuery()) {
		    while (res.next()) {
		    	//下記、3つのインスタンス化しているオブジェクトは、Beanファイルでそれぞれを継承させたら少し短くなるかも
		        CartItemBean cartItem = new CartItemBean();
		        cartItem.setCartItemId(res.getInt("cart_item_id"));
		        cartItem.setQuantity(res.getInt("quantity"));
		                
		        ProductBean product = new ProductBean();
		        product.setProductId(res.getInt("product_id"));
		        product.setProductName(res.getString("product_name"));
		        product.setPrice(res.getInt("price"));
		        product.setImage(res.getString("image"));
		                
		        cartItem.setSizeId(res.getInt("size_id"));
		                
		        cartItem.setProduct(product);
		                
		        cartItems.add(cartItem);
		        }
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
		return cartItems;
	}

}
