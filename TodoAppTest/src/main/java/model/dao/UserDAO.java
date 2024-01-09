package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;
import model.dto.UserDTO;

public class UserDAO {
	
	//新規登録
	public int userRegister(String userName, String email, String password) throws ClassNotFoundException, SQLException {
		int row = 0;
		String sql = "INSERT INTO users (user_name, email, password) VALUES (?,?,?)";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, userName);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			
			row = pstmt.executeUpdate();
		}
		return row;
 	}
	
	//ログイン
	public UserDTO userLogin(String email, String password) throws ClassNotFoundException, SQLException {
		UserDTO user = null;
		String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				user = new UserDTO();
				user.setUserId(res.getInt("user_id"));
				user.setUserName(res.getString("user_name"));
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
			}
		}
		return user;
	}
	
	//ユーザーIDの取得
	public int getUserId(String email) throws ClassNotFoundException, SQLException {
		int userId = -1;
		String sql = "SELECT user_id FROM users WHERE email = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, email);
			
			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				userId = res.getInt("user_id");
			}
		}
		return userId;
	}

}
