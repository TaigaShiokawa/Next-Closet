package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;

public class NakaDAO {

	public boolean validate(String email,String password) throws ClassNotFoundException, SQLException {
		
		boolean status = false;
		//管理者ログイン用です
		String sql ="SELECT * FROM admins WHERE email = ? and password = ?";
		try
		   (Connection con = DBConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			status = rs.next();
		}
		return status;
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

}