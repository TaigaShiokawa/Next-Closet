package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import model.bean.CategoryBean;
import model.bean.ProductBean;

public class ProductDAO {
	
	//一覧表示(productsテーブル)
	public List <ProductBean>  allProductList() throws SQLException , ClassNotFoundException{
		List <ProductBean> productList = new ArrayList<>();
		String sql = "SELECT * FROM products WHERE status = 1";
        try(Connection con = DBConnection.getConnection();  //データベースに接続する
        PreparedStatement statement = con.prepareStatement(sql); //発行したいSQLを生成
        ResultSet res = statement.executeQuery()){ //引数で指定されたSQLをデータベースで実行するメソッド
        		         
		            while (res.next()){ 
		            	int product_id	    	 	= res.getInt("product_id");
		            	int category_id	     		= res.getInt("category_id");
		            	int gender        		    = res.getInt("gender");	
		            	String product_name  		= res.getString("product_name");
		            	int price            		= res.getInt("price");
		            	String description   		= res.getString("description");
		            	boolean status       		= res.getBoolean("status");
		            	String image         		= res.getString("image");
		            	Date registration_date      = res.getDate("registration_date");
		            	productList.add(new ProductBean (product_id, category_id,  gender, product_name, price , description , status , image , registration_date));
		            }
		     }	
		return productList;		
	}
	
	//一覧表示(productsテーブル) カテゴリー別の商品一覧
		public List <ProductBean>  allCategoryProductList(int categoryId) throws SQLException , ClassNotFoundException{
			
			List <ProductBean> productList = new ArrayList<>();
			String sql = "SELECT * FROM products WHERE status = 1 AND category_id = ?";
	        try(Connection con = DBConnection.getConnection();  //データベースに接続する
	        PreparedStatement pstmt = con.prepareStatement(sql)){ //引数で指定されたSQLをデータベースで実行するメソッド
	        	pstmt.setInt(1, categoryId);
	        	ResultSet res = pstmt.executeQuery();
	        		         
			            while (res.next()){ 
			            	int product_id	    	 	= res.getInt("product_id");
			            	int category_id	     		= res.getInt("category_id");
			            	int gender        		    = res.getInt("gender");	
			            	String product_name  		= res.getString("product_name");
			            	int price            		= res.getInt("price");
			            	String description   		= res.getString("description");
			            	boolean status       		= res.getBoolean("status");
			            	String image         		= res.getString("image");
			            	Date registration_date      = res.getDate("registration_date");
			            	productList.add(new ProductBean (product_id, category_id,  gender, product_name, price , description , status , image , registration_date));
			            }
			     }	
			return productList;		
		}
	
	
	
	//genderごとのカテゴリー別商品一覧
	public List <ProductBean>  categoryProductList(int categoryId , int genderNo) throws SQLException , ClassNotFoundException{
		
		List <ProductBean> productList = new ArrayList<>();
       
		String sql = "SELECT * FROM products WHERE status = 1 AND category_id = ? AND gender = ?";
		 try(Connection con = DBConnection.getConnection();  //データベースに接続する
			 PreparedStatement pstmt = con.prepareStatement(sql)){ //引数で指定されたSQLをデータベースで実行するメソッド
      
			 pstmt.setInt(1, categoryId);
			 pstmt.setInt(2, genderNo);
        	
        	  ResultSet res = pstmt.executeQuery(); //引数で指定されたSQLをデータベースで実行するメソッド
        		         
		            while (res.next()){ 
		            	
		            	int product_id	    	 	= res.getInt("product_id");
		            	int category_id	     		= res.getInt("category_id");
		            	int gender        		    = res.getInt("gender");	
		            	String product_name  		= res.getString("product_name");
		            	int price            		= res.getInt("price");
		            	String description   		= res.getString("description");
		            	boolean status       		= res.getBoolean("status");
		            	String image         		= res.getString("image");
		            	Date registration_date      = res.getDate("registration_date");
		            	productList.add(new ProductBean (product_id, category_id,  gender, product_name, price , description , status , image , registration_date));
		            }
		     }	
		return productList;		
	}
	
	//商品詳細用　（一つの商品の情報を持ってくる）
	public List <ProductBean>  detailProductList(int productId ) throws SQLException , ClassNotFoundException{
		
		List <ProductBean> productList = new ArrayList<>();
       
		String sql = "SELECT * FROM products WHERE category_id = ? ";
		 try(Connection con = DBConnection.getConnection();  //データベースに接続する
			 PreparedStatement pstmt = con.prepareStatement(sql)){ //引数で指定されたSQLをデータベースで実行するメソッド
      
			 pstmt.setInt(1, productId);
        	
        	  ResultSet res = pstmt.executeQuery(); //引数で指定されたSQLをデータベースで実行するメソッド
        		         
		            while (res.next()){ 
		            	
		            	int product_id	    	 	= res.getInt("product_id");
		            	int category_id	     		= res.getInt("category_id");
		            	int gender        		    = res.getInt("gender");	
		            	String product_name  		= res.getString("product_name");
		            	int price            		= res.getInt("price");
		            	String description   		= res.getString("description");
		            	boolean status       		= res.getBoolean("status");
		            	String image         		= res.getString("image");
		            	Date registration_date      = res.getDate("registration_date");
		            	productList.add(new ProductBean (product_id, category_id,  gender, product_name, price , description , status , image , registration_date));
		            }
		     }	
		return productList;		
	}
	
	
	//カテゴリーのリスト
	public List <CategoryBean> categoryList () throws SQLException , ClassNotFoundException {
	
        String sql = "SELECT * FROM categories "; //postsテーブルの全データをsqlに格納
        List <CategoryBean> categoryList = new ArrayList<>();
        
        try(Connection con = DBConnection.getConnection(); //データベースに接続する
        PreparedStatement statement = con.prepareStatement(sql); //発行したいSQLを生成
        ResultSet res = statement.executeQuery()){ //引数で指定されたSQLをデータベースで実行するメソッド
        		         
		            while (res.next()){ 
		            	int category_id			= res.getInt("category_id");
		            	String category_name	= res.getString("category_name");						
		            	categoryList.add(new CategoryBean( category_id , category_name));
		            }
		     }	
		return categoryList;		
}

	//商品の在庫（単品）の在庫数とストック数を比較する
		public  int getStockItem(int productId , int  sizeId) throws ClassNotFoundException, SQLException {
			String sql = "SELECT * FROM add inventory WHERE product_id = ? AND size_id = ?";
			int inventory = -1;
			try (Connection con = DBConnection.getConnection(); 
					PreparedStatement pstmt = con.prepareStatement(sql)) {
					pstmt.setInt(1, productId);
					pstmt.setInt(2, sizeId);
				
				ResultSet res = pstmt.executeQuery();
				while(res.next()) {
					inventory = res.getInt("stock_quantity");
				}
				return inventory;
			}
		}
		
		//買い物かご全ての商品の在庫数と購入数を比較するメソッド
		public boolean cartProductStock(int userId ) throws SQLException , ClassNotFoundException{
			 String sql = "SELECT * FROM cart_items WHERE user_id = " + userId ; //postsテーブルの全データをsqlに格納
		        boolean stock = true;
		        try(Connection con = DBConnection.getConnection();  //データベースに接続する
		   			 PreparedStatement pstmt = con.prepareStatement(sql)){ //引数で指定されたSQLをデータベースで実行するメソッド
		         
		   			 pstmt.setInt(1, userId);
		           	  ResultSet res = pstmt.executeQuery(); //引数で指定されたSQLをデータベースで実行するメソッド
		        		    
				            while (res.next()){ 
				            	int product_id			= res.getInt("product_id");
				            	int size_id			= res.getInt("size_id");
				            	int quantity			= res.getInt("quantity");
				            	
				            	sql = "SELECT stock_quantity FROM inventory WHERE product_id = " + product_id + " AND size_id = " + size_id  ; //postsテーブルの全データをsqlに格納
					    	        try(Connection conn = DBConnection.getConnection();  //データベースに接続する
					    	   			 PreparedStatement pst = con.prepareStatement(sql)){
					    	        	int stock_quantity = res.getInt("stock_quantity");
					    	        	if(  quantity -  stock_quantity  < 0  ) {
					    	        		stock = false;
					    	        	}
					    	        
					    	        }
				            }
				     }	
				return stock;		
		}


	

}