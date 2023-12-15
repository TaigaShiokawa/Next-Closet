package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import model.bean.ProductBean;

public class AdminProductDAO {
	
	//商品の新規登録
	public int addProduct(int categoryId, int gender, String productName, String description, int price, String image) 
	        throws ClassNotFoundException, SQLException {
	    int productId = 0; // 生成されたキーを保存するための変数
	    String sql = "INSERT INTO products (category_id, gender, product_name, description, price, image) VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection con = DBConnection.getConnection(); 
	            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { // キーを返すように指定
	        
	        pstmt.setInt(1, categoryId);
	        pstmt.setInt(2, gender);
	        pstmt.setString(3, productName);
	        pstmt.setString(4, description);
	        pstmt.setInt(5, price);
	        pstmt.setString(6, image);
	        
	        int affectedRows = pstmt.executeUpdate();
	        if (affectedRows > 0) { // 実際に行が挿入されたか確認
	            try (ResultSet rs = pstmt.getGeneratedKeys()) {
	                if (rs.next()) {
	                    productId = rs.getInt(1); // 生成されたキーを取得
	                }
	            }
	        }
	    }
	    return productId; // 生成された商品IDを返す
	}

	
	//カテゴリーIDの取得
	public int getCategoryId(String categoryName) 
	        throws ClassNotFoundException, SQLException {
	    String sql = "SELECT category_id FROM categories WHERE category_name = ?";
	    try (Connection con = DBConnection.getConnection(); 
	            PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1, categoryName);
	        ResultSet res = pstmt.executeQuery();
	        if (res.next()) {
	            return res.getInt("category_id");
	        } else {
	            // カテゴリが見つからなかった場合は-1を返し、IDを無効とする
	            return -1;
	        }
	    }
	}
	
	//サイズIDの取得
	public int getSizeId(String sizeName) 
			throws ClassNotFoundException, SQLException {
		String sql = "SELECT size_id FROM sizes WHERE size_name = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, sizeName);
			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				return res.getInt("size_id");
			} else {
				// サイズが見つからなかった場合は-1を返し、IDを無効とする
				return -1;
			}
		}
	}
	
	//商品の在庫
	public int setProductInventory(int productId, int sizeId, int stockQuantity) 
			throws ClassNotFoundException, SQLException{
		int processingNum = 0;
		String sql = "INSERT INTO inventory (product_id, size_id, stock_quantity) VALUES (?, ?, ?)";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, productId);
			pstmt.setInt(2, sizeId);
			pstmt.setInt(3, stockQuantity);
			
			processingNum = pstmt.executeUpdate();
		}
		return processingNum;
	}
	
	//商品詳細管理者用　（一つの商品の情報と商品の在庫情報を持ってくる）
	public List <ProductBean>  detailAdminProductList(int productId ) 
			throws SQLException , ClassNotFoundException{
   		List <ProductBean> productList = new ArrayList<>();
				       
		String sql = "SELECT p.*, i.inventory_id, i.size_id, i.stock_quantity "
				   + "FROM products p "
				   + "LEFT JOIN inventory i ON p.product_id = i.product_id "
				   + "WHERE p.product_id = ?";
				
		 try(Connection con = DBConnection.getConnection();  //データベースに接続する
			 PreparedStatement pstmt = con.prepareStatement(sql)){ //引数で指定されたSQLをデータベースで実行するメソッド
					 
			 pstmt.setInt(1, productId);
					 
	      	 ResultSet res = pstmt.executeQuery(); //引数で指定されたSQLをデータベースで実行するメソッド 
			       	 
		     while (res.next()){ 
				int product_id = res.getInt("product_id");
				int category_id = res.getInt("category_id");
				int gender = res.getInt("gender");	
				String product_name = res.getString("product_name");
				int price = res.getInt("price");
				String description = res.getString("description");
				boolean status = res.getBoolean("status");
				String image = res.getString("image");
				Date registration_date = res.getDate("registration_date");
						
				int inventoryId =res.getInt("inventory_id");
				int sizeId = res.getInt("size_id");
				int stockQuantity = res.getInt("stock_quantity");
						
				ProductBean product = new ProductBean(product_id, category_id,  gender, product_name, price , description , status , image , registration_date);
				product.setInventoryId(inventoryId);
				product.setSizeId(sizeId);
				product.setStockQuantity(stockQuantity);
						
				productList.add(product);
			}
		}	
		return productList;		
	}
}
