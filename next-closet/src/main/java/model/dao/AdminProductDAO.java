package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connection.DBConnection;
import model.bean.ProductBean;
import model.bean.SizeBean;

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
	public List<ProductBean> detailAdminProductList(int productId) throws SQLException, ClassNotFoundException {
	    Map<Integer, ProductBean> productMap = new HashMap<>();
	    List<ProductBean> productList = new ArrayList<>();

	    String sql = "SELECT p.*, i.inventory_id, i.size_id, s.size_name, i.stock_quantity "
	               + "FROM products p "
	               + "LEFT JOIN inventory i ON p.product_id = i.product_id "
	               + "LEFT JOIN sizes s ON i.size_id = s.size_id "
	               + "WHERE p.product_id = ?";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setInt(1, productId);
	        ResultSet res = pstmt.executeQuery();

	        while (res.next()) {
	            int prodId = res.getInt("product_id");
	            ProductBean product = productMap.get(prodId);
	            if (product == null) {
	                product = new ProductBean();
	                // 商品情報の設定
	                product.setProductId(prodId);
	                product.setProductName(res.getString("product_name"));
	                product.setPrice(res.getInt("price"));
	                product.setDescription(res.getString("description"));
	                product.setImage(res.getString("image"));
	                product.setRegistrationDate(res.getDate("registration_date"));
	                productMap.put(prodId, product);
	            }

	            SizeBean size = new SizeBean();
	            size.setSizeId(res.getInt("size_id"));
	            size.setSizeName(res.getString("size_name"));
	            size.setStockQuantity(res.getInt("stock_quantity"));

	            product.addSize(size);
	        }
	    }

	    return new ArrayList<>(productMap.values());
	}

	
	//　商品編集の情報を取得する
	public List<ProductBean> editAdminProductList(int productId) throws ClassNotFoundException, SQLException {
	    Map<Integer, ProductBean> productMap = new HashMap<>();

	    String sql = "SELECT p.product_id, p.category_id, p.gender, p.product_name, p.price, p.description, p.status, p.image, p.registration_date, "
	               + "i.inventory_id, s.size_id, s.size_name, i.stock_quantity "
	               + "FROM products p "
	               + "JOIN inventory i ON p.product_id = i.product_id "
	               + "JOIN sizes s ON i.size_id = s.size_id "
	               + "WHERE p.product_name = (SELECT product_name FROM products WHERE product_id = ?) "
	               + "ORDER BY s.size_id;";

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
	                    
	            SizeBean size = new SizeBean();
	            size.setSizeId(res.getInt("size_id"));
	            size.setSizeName(res.getString("size_name"));
	            size.setStockQuantity(res.getInt("stock_quantity"));
	            
	            ProductBean product = productMap.get(product_id);
	            if (product == null) {
	            	product = new ProductBean(product_id, category_id, gender, product_name, price, description, status, image, registration_date);
	            	productMap.put(product_id, product);
	            }
	            product.addSize(size);
	        }
	    }
	    return new ArrayList<>(productMap.values());
	}
	
	// 商品を編集する
	public void updateProduct(ProductBean product, Map<String, Integer> stockQuantities) 
			throws SQLException, ClassNotFoundException {
		// 商品情報の更新
	    String updateProductSql = "UPDATE products SET product_name = ?, price = ?, description = ?, image = ? WHERE product_id IN (SELECT product_id FROM (SELECT product_id FROM products WHERE product_name = (SELECT product_name FROM products WHERE product_id = ?)) AS temp);";

	    // 在庫数量の更新
	    String updateInventorySql = "UPDATE inventory SET stock_quantity = ? WHERE product_id = ? AND size_id = (SELECT size_id FROM sizes WHERE size_name = ?);";
		
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmtProduct = con.prepareStatement(updateProductSql);
			 PreparedStatement pstmtInventory = con.prepareStatement(updateInventorySql)) {
			
			// 商品情報の更新
			pstmtProduct.setString(1, product.getProductName());
			pstmtProduct.setInt(2, product.getPrice());
			pstmtProduct.setString(3, product.getDescription());
			pstmtProduct.setString(4, product.getImage());
			pstmtProduct.setInt(5, product.getProductId());
			pstmtProduct.executeUpdate();
			
			// 在庫数量の更新
	        for (Map.Entry<String, Integer> entry : stockQuantities.entrySet()) {
	            String[] parts = entry.getKey().split("_");
	            String productIdStr = parts[0];
	            String sizeName = parts[1];
	            int stockQuantity = entry.getValue();

	            int productId = Integer.parseInt(productIdStr);
	            pstmtInventory.setInt(1, stockQuantity);
	            pstmtInventory.setInt(2, productId);
	            pstmtInventory.setString(3, sizeName);
	            pstmtInventory.executeUpdate();
	        }
		}
	}
	
	
	public void updateProductStatus(int productId, boolean newStatus) 
			throws ClassNotFoundException, SQLException {
		String sql = "UPDATE products SET status = ? WHERE product_id = ?;";
		
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setBoolean(1, newStatus);
			pstmt.setInt(2, productId);
			pstmt.executeUpdate();
		}
	}
	
}
