package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.DBConnection;

public class AdminProductDAO {
	
	//商品の新規登録
	public int addProduct(int categoryId, int gender, String productName, String description, int price, String image) 
	        throws ClassNotFoundException, SQLException {
	    int productId = 0; // 変更: 生成されたキーを保存するための変数
	    String sql = "INSERT INTO products (category_id, gender, product_name, description, price, image) VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection con = DBConnection.getConnection(); 
	            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { // 変更: キーを返すように指定
	        
	        pstmt.setInt(1, categoryId);
	        pstmt.setInt(2, gender);
	        pstmt.setString(3, productName);
	        pstmt.setString(4, description);
	        pstmt.setInt(5, price);
	        pstmt.setString(6, image);
	        
	        int affectedRows = pstmt.executeUpdate();
	        if (affectedRows > 0) { // 変更: 実際に行が挿入されたか確認
	            try (ResultSet rs = pstmt.getGeneratedKeys()) {
	                if (rs.next()) {
	                    productId = rs.getInt(1); // 変更: 生成されたキーを取得
	                }
	            }
	        }
	    }
	    return productId; // 変更: 生成された商品IDを返す
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
}
