package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import model.bean.CategoryBean;

public class CategoryDAO {
	
	//特定のオーダー情報取得
	public List <CategoryBean> getCategoryList()
			throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM categories "; //postsテーブルの全データをsqlに格納
	    List <CategoryBean> categoryList = new ArrayList<>();
	    try(Connection con = DBConnection.getConnection();
	    		PreparedStatement statement = con.prepareStatement(sql);
	        ResultSet res = statement.executeQuery()) {
	        while (res.next()) {
	        	int category_id = res.getInt("category_id");
	        	String category_name = res.getString("category_name");
			    
	        	categoryList.add(new CategoryBean( category_id , category_name));
			}
		}
		return categoryList;
	}

	//カテゴリーが今あるかどうか
	public boolean getCategory(String categoryName)
			throws ClassNotFoundException, SQLException {
		String sql = "SELECT COUNT(category_name = ? ) AS c FROM categories WHERE category_name = ?"; //postsテーブルの全データをsqlに格納
	    int count = 0;
	    boolean category = false;    
	    try(Connection con = DBConnection.getConnection();
	    		PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1,categoryName);
			pstmt.setString(2,categoryName);
			ResultSet res = pstmt.executeQuery();
	        while (res.next()) { 
	        	count = res.getInt("c");
			}
			if( count == 0 ) {
				category = true;
			}     
	    }
		return category;
	}
	
	//カテゴリー追加
	public void addCategory(String categoryName) 
			throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO categories (category_name) VALUES (?)";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, categoryName);
			pstmt.executeUpdate();
		}
	}
	
	//このカテゴリーを選択している商品が今あるかどうか
	public boolean getNoProductCategory( int categoryId )
			throws ClassNotFoundException, SQLException {
		String sql = "SELECT COUNT(category_id = ? )AS c FROM products WHERE category_id = ? ";//postsテーブルの全データをsqlに格納
		int count = -1;
		boolean category = false;
		try(Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, categoryId);
			pstmt.setInt(2, categoryId);
		    ResultSet res = pstmt.executeQuery();
		    while (res.next()) {
		    	count = res.getInt("c");
		    	}
				if( count == 0 ) {
					category = true;
			} 
		}
		return category;
	}
		
	//カテゴリー追加
	public void deleteCategory( int categoryId )
			throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM categories WHERE category_id = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, categoryId);
			pstmt.executeUpdate();
		}
	}
	
}
