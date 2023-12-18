package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;
import model.bean.AdminBean;

public class AdminDAO {

	public AdminBean validate(String email,String password) throws ClassNotFoundException, SQLException {
		
		AdminBean admin = new AdminBean();
		//管理者ログイン用
		String sql ="SELECT * FROM admins WHERE email = ? and hash_pass = ?";
		try(Connection con = DBConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				admin = new AdminBean();
				admin.setAdminName(rs.getInt("admin_id"));
			}
		}
		return admin;
	}
	//管理者新規登録用
	public int registerAdmin(String adminName, String kanaName, String email, String password) 
			throws ClassNotFoundException, SQLException {
		int processingNum = 0;
		String sql = "INSERT INTO admins (admin_name, admin_kana_name, email, hash_pass) VALUES (?, ?, ?, ?)";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, adminName);
			pstmt.setString(2, kanaName);
			pstmt.setString(3, email);
			pstmt.setString(4, password);
			processingNum = pstmt.executeUpdate();
		}
		return processingNum;
	}

	
	public int updateAdmin(String adminName, String kanaName, String email, String password,int adminId)
	        throws SQLException, ClassNotFoundException {
	    String sql = "UPDATE admins SET admin_name = ?, admin_kana_name = ?, email = ?, hash_pass = ? WHERE admin_id = ?";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement preparedStatement = con.prepareStatement(sql)) {

	        // Set parameters for the PreparedStatement
	        preparedStatement.setString(1, adminName);
	        preparedStatement.setString(2, kanaName);
	        preparedStatement.setString(3, email);
	        preparedStatement.setString(4, password);
	        preparedStatement.setInt(5, adminId);

	        // Execute the update
	        int rowsAffected = preparedStatement.executeUpdate();

	        // Check if the update was successful
	        return rowsAffected;

	    } catch (SQLException | ClassNotFoundException e) {
	        // Log or print the exception for debugging
	        e.printStackTrace();
	        // Re-throw the exception to be handled by the calling code
	        throw e;
	    }
	}
	
	//ユーザIDを取得
	public int getAdminId(String email)throws ClassNotFoundException, SQLException {

		int AdminId = -1;
		String sql = "SELECT Admin_id FROM admins WHERE email = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, email);
			
			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				AdminId = res.getInt("user_id");
			}
		}
		return AdminId;
	}

}