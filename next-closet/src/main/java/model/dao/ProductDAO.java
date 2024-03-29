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
import model.bean.CartItemBean;
import model.bean.CategoryBean;
import model.bean.ProductBean;

public class ProductDAO {
	
	//販売中の商品一覧表示(productsテーブル)
	public List <ProductBean>  allProductList()
			throws SQLException , ClassNotFoundException {
		List <ProductBean> productList = new ArrayList<>();
		String sql = "SELECT * FROM products WHERE status = 1 LIMIT 16 OFFSET 0";
        try (Connection con = DBConnection.getConnection();
               PreparedStatement statement = con.prepareStatement(sql);
           ResultSet res = statement.executeQuery()) {       
		   while(res.next()) { 
			   int product_id = res.getInt("product_id");
		       int category_id = res.getInt("category_id");
		       int gender = res.getInt("gender");	
		       String product_name = res.getString("product_name");
		       int price = res.getInt("price");
		       String description = res.getString("description");
		       boolean status = res.getBoolean("status");
		       String image = res.getString("image");
		       Date registration_date = res.getDate("registration_date");
		       
		       productList.add(new ProductBean (product_id, category_id,  gender, product_name, price , description , status , image , registration_date));
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
        return productList;
	}
	
	//商品の販売ステータス関係なしの一覧表示(productsテーブル)
	public List <ProductBean> allStatusProductList()
			throws SQLException , ClassNotFoundException{
		List <ProductBean> productList = new ArrayList<>();
		String sql = "SELECT * FROM products";
	    try (Connection con = DBConnection.getConnection();
	    		PreparedStatement statement = con.prepareStatement(sql);
	    	ResultSet res = statement.executeQuery()) {
	    	while(res.next()) {
	    		int product_id = res.getInt("product_id");
			    int category_id = res.getInt("category_id");
			    int gender = res.getInt("gender");	
			    String product_name = res.getString("product_name");
			    int price = res.getInt("price");
			    String description = res.getString("description");
			    boolean status = res.getBoolean("status");
			    String image = res.getString("image");
			    Date registration_date = res.getDate("registration_date");
			            	
			    productList.add(new ProductBean (product_id, category_id,  gender, product_name, price , description , status , image , registration_date));
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
	    return productList;
	}
	
	//商品一覧表示(productsテーブル) 販売中のカテゴリー別の商品一覧
	public List <ProductBean>  allCategoryProductList(int categoryId)
			throws SQLException , ClassNotFoundException {
		List <ProductBean> productList = new ArrayList<>();
		String sql = "SELECT * FROM products WHERE status = 1 AND category_id = ?";
	    try (Connection con = DBConnection.getConnection();
	    		PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setInt(1, categoryId);
	        ResultSet res = pstmt.executeQuery();	         
			while(res.next()) { 
				int product_id = res.getInt("product_id");
			    int category_id = res.getInt("category_id");
			    int gender = res.getInt("gender");	
			    String product_name = res.getString("product_name");
			    int price = res.getInt("price");
			    String description = res.getString("description");
			    boolean status = res.getBoolean("status");
			    String image = res.getString("image");
			    Date registration_date = res.getDate("registration_date");
			    
			    productList.add(new ProductBean (product_id, category_id,  gender, product_name, price , description , status , image , registration_date));
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
		return productList;
	}
		
	//商品一覧表示(productsテーブル) カテゴリー別の商品一覧
	public List <ProductBean>  allStatusCategoryProductList(int categoryId)
			throws SQLException , ClassNotFoundException {
		List <ProductBean> productList = new ArrayList<>();
		String sql = "SELECT * FROM products WHERE category_id = ?";
	    try (Connection con = DBConnection.getConnection();
	    		 PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, categoryId);
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
				
				productList.add(new ProductBean (product_id, category_id,  gender, product_name, price , description , status , image , registration_date));
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
		return productList;
	}
	
	//性別(gender)ごとの販売中カテゴリー別商品一覧
	public List <ProductBean>  categoryProductList(int categoryId , int genderNo)
			throws SQLException , ClassNotFoundException {
		List <ProductBean> productList = new ArrayList<>();
		String sql = "SELECT * FROM products WHERE status = 1 AND category_id = ? AND gender = ?";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, categoryId);
			pstmt.setInt(2, genderNo);
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
		        
		        productList.add(new ProductBean (product_id, category_id,  gender, product_name, price , description , status , image , registration_date));
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
		return productList;
	}
	
	//gender(性別)ごとの全ての販売ステータスのカテゴリー別商品一覧
	public List <ProductBean>  categoryStatusProductList(int categoryId , int genderNo)
			throws SQLException , ClassNotFoundException {
		List <ProductBean> productList = new ArrayList<>();
		String sql = "SELECT * FROM products WHERE  category_id = ? AND gender = ?";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {	      
			pstmt.setInt(1, categoryId);
			pstmt.setInt(2, genderNo);
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
			    
			    productList.add(new ProductBean (product_id, category_id,  gender, product_name, price , description , status , image , registration_date));
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
		return productList;
	}
	
	//商品詳細用（一つの商品の情報を持ってくる）
	public List <ProductBean>  detailProductList(int productId)
			throws SQLException , ClassNotFoundException {
		List <ProductBean> productList = new ArrayList<>();
		String sql = "SELECT * FROM products WHERE product_id = ? ";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, productId);
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
		        
		        productList.add(new ProductBean (product_id, category_id,  gender, product_name, price , description , status , image , registration_date));
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
		return productList;
	}
	
	//カテゴリーのリスト
	public List <CategoryBean> categoryList()
			throws SQLException , ClassNotFoundException {
		String sql = "SELECT * FROM categories ";
        List <CategoryBean> categoryList = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
        		PreparedStatement statement = con.prepareStatement(sql);
            ResultSet res = statement.executeQuery()) {
        	while (res.next()) { 
        		int category_id = res.getInt("category_id");
		        String category_name = res.getString("category_name");
		        
		        categoryList.add(new CategoryBean( category_id , category_name));
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
	    return categoryList;
    }

	//商品の在庫（単品）の在庫数とストック数を比較する
	public  int getStockItem(int productId , int  sizeId)
			throws ClassNotFoundException, SQLException {
		String sql = "SELECT stock_quantity FROM inventory WHERE product_id = ? AND size_id = ?";
		int inventory = -1;
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, productId);
			pstmt.setInt(2, sizeId);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				inventory = res.getInt("stock_quantity");
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
		return inventory;
	}
		
	//カート内の全ての商品の在庫と購入数を比較する
	public boolean cartProductStock(int userId)
			throws SQLException , ClassNotFoundException {
		CartDAO dao = new CartDAO();
		List <CartItemBean> cartList = dao.getAllCartItems(userId);
		boolean stock = true;
		int product_id = -1;
		int size_id = -1;
		int quantity = -1;
		int stock_quantity = -1;
		String sql = "";
		for ( CartItemBean list : cartList ) {
			size_id = list.getSizeId();
			product_id = list.getProduct().getProductId();
			quantity = list.getQuantity();
			System.out.println(size_id + " = size_id");
			System.out.println(product_id + " = product_id");
			sql = "SELECT stock_quantity FROM inventory WHERE size_id = ? AND product_id = ?";
			try (Connection con = DBConnection.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setInt(1, size_id);
				pstmt.setInt(2, product_id);
				ResultSet res = pstmt.executeQuery();
				System.out.println("スレッドdesu");
				while(res.next()) { 
					stock_quantity  = res.getInt("stock_quantity");
					int a = stock_quantity  - quantity;
					System.out.println(a + "desu");
					if( a < 0  ) {
						stock = false;
						System.out.println(stock + "結果");
						return stock;
					} 
				}
				if( stock_quantity  - quantity < 0  ) {
					stock = false;
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
		} 
		return stock;
	}
		
	//オーダーに購入情報を送る
	public void addOrderItem(int productId , int quantity , int size_id , int user_id , int total_amount)
			throws SQLException , ClassNotFoundException {
		String sql = "INSERT INTO next_closet_db.order_items ( product_id , quantity , size_id ,user_id ,total_amount ) VALUES ( ? , ?, ?  , ? , ? ) "; 
	    try (Connection con = DBConnection.getConnection();
	   			PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setInt(1, productId);
		   	pstmt.setInt(2, quantity);
		   	pstmt.setInt(3, size_id);
		   	pstmt.setInt(4, user_id);
		   	pstmt.setInt(5, total_amount);
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
		
	//今の在庫数を持ってくる
	public int nowStockQuantity( int product_id , int size_id )
			throws SQLException , ClassNotFoundException {
		String sql = "SELECT stock_quantity FROM next_closet_db.inventory WHERE product_id = ? AND size_id = ?";
		int stock = -1;
		try (Connection con = DBConnection.getConnection();
	   			 PreparedStatement pstmt = con.prepareStatement(sql)) {
		   	pstmt.setInt(1, product_id);
		   	pstmt.setInt(2, size_id);
		   	ResultSet res = pstmt.executeQuery(); 
		   	while(res.next()) {
		   		stock = res.getInt("stock_quantity");
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
	    return  stock;
	}
		
	//購入商品の在庫を減したり増やしたりする（購入時や商品数追加時）
	public void changeStock( int product_id , int size_id , int stockQuantity ) 
			throws SQLException , ClassNotFoundException {
		String sql = "UPDATE inventory SET stock_quantity = ?  WHERE product_id = ? AND size_id = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setInt(1, stockQuantity);
		   	pstmt.setInt(2, product_id);
		   	pstmt.setInt(3, size_id);
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
		
	//オーダーに登録
	public void orderRegistration( int product_id ,  int size_id , int quantity , int user_id ,int total_amount , String delivery_address)
			throws SQLException , ClassNotFoundException {
		String sql = "INSERT INTO order_items  ( product_id , quantity , size_id ,user_id , total_amount , delivery_address) VALUES ( ? , ? , ? , ? , ? , ?)";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, product_id);
			pstmt.setInt(2, quantity);
			pstmt.setInt(3, size_id);
			pstmt.setInt(4, user_id);
			pstmt.setInt(5, total_amount);
			pstmt.setString(6, delivery_address);
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
				
	//商品一覧 2ページ目移行
	public List<ProductBean> getPagenation() 
			throws ClassNotFoundException, SQLException {
		List<ProductBean> productList = new ArrayList<>();
		String sql = "SELECT * FROM products WHERE status = 1 LIMIT 16 OFFSET 16"; //LIMIT → 行数 OFFSET → 開始位置
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
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
			    
			    productList.add(new ProductBean (product_id, category_id,  gender, product_name, price , description , status , image , registration_date));
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
		return productList;	
	}
	
	//商品一覧 3ページ目移行
	public List<ProductBean> getPagenationLast() 
			throws ClassNotFoundException, SQLException {
		List<ProductBean> productList = new ArrayList<>();
		String sql = "SELECT * FROM products WHERE status = 1 LIMIT 16 OFFSET 32"; //LIMIT → 行数 OFFSET → 開始位置
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
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
				
				productList.add(new ProductBean (product_id, category_id,  gender, product_name, price , description , status , image , registration_date));
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
		return productList;	
	}
	
}