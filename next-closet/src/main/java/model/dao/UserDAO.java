package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;
import model.bean.UserBean;

public class UserDAO {
	
	//新規登録 (usersテーブル)
	public int registerUser(String userName, String kanaName, String email, String password, String telNumber) 
			throws ClassNotFoundException, SQLException {
		int processingNum = 0;
		String sql = "INSERT INTO users (user_name, kana_name, email, hash_pass, tel_number) VALUES (?, ?, ?, ?, ?)";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, userName);
			pstmt.setString(2, kanaName);
			pstmt.setString(3, email);
			pstmt.setString(4, password);
			pstmt.setString(5, telNumber);
			processingNum = pstmt.executeUpdate();
		}
		return processingNum;
	}
	
	//新規登録 (addressesテーブル)
	public int registerAddress(int userId, String postCode, String address) 
			throws ClassNotFoundException, SQLException {
		int processingNum = 0;
		String sql = "INSERT INTO addresses (user_id, post_code, address) VALUES (?, ?, ?)";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			pstmt.setString(2, postCode);
			pstmt.setString(3, address);
			
			processingNum = pstmt.executeUpdate();
		}
		return processingNum;
	}
	
	//アドレスid取得
	public int getUserAddressId(String email) 
			throws ClassNotFoundException, SQLException {
		int userAddressId = -1;
		String sql = "SELECT address_id FROM addresses WHERE email = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, email);
			
			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				userAddressId = res.getInt("address_id");
			}
		}
		return userAddressId;
	}
	
	//ユーザIDを取得
	public int getUserId(String email) 
			throws ClassNotFoundException, SQLException {
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
	
	//ユーザーログイン
	public UserBean userLogin(String email, String password) 
			throws ClassNotFoundException, SQLException {
		
		UserBean user = new UserBean();
		
		String sql = "SELECT * FROM users WHERE email = ? AND hash_pass = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				user = new UserBean();
				user.setUserId(res.getInt("user_id"));
				user.setUserName(res.getString("user_name"));
				user.setEmail(res.getString("email"));
				user.setTelNumber(res.getString("tel_number"));
				user.setUserStatus(res.getBoolean("user_status"));
			}
		}
		return user;
	}
}