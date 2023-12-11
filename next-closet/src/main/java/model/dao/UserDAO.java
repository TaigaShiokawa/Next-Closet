package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;
import model.bean.AddressBean;
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
	public int registerAddress(int userId, String postCode, String prefectures, String address) 
			throws ClassNotFoundException, SQLException {
		int processingNum = 0;
		String sql = "INSERT INTO addresses (user_id, post_code, prefectures, address) VALUES (?, ?, ?, ?)";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			pstmt.setString(2, postCode);
			pstmt.setString(3, prefectures);
			pstmt.setString(4, address);
			
			processingNum = pstmt.executeUpdate();
		}
		return processingNum;
	}
	
	//アドレスid取得
	public AddressBean getUserAddressId(int userId) 
			throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM addresses WHERE user_id = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			AddressBean address = new AddressBean();
			pstmt.setInt(1, userId);
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				address = new AddressBean();
				address.setUser_id(res.getInt("user_id"));
				address.setPostCode(res.getString("post_code"));
				address.setPrefectures(res.getString("prefectures"));
				address.setAddress(res.getString("address"));
			}
			return address;
		}
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
				user.setKanaName(res.getString("kana_name"));
				user.setEmail(res.getString("email"));
				user.setTelNumber(res.getString("tel_number"));
				user.setUserStatus(res.getBoolean("user_status"));
			}
		}
		return user;
	}
	
	//ユーザー情報編集
	public int loginUserUpDate(String userName, String kanaName, String telNumber, String email, String password) throws ClassNotFoundException, SQLException {
		int processingNum = 0;
		String sql = "UPDATE users SET user_name = ?, kana_name = ?, tel_number = ?, email = ?, hash_pass = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, userName);
			pstmt.setString(2, kanaName);
			pstmt.setString(3, telNumber);
			pstmt.setString(4, email);
			pstmt.setString(5, password);
			
			processingNum = pstmt.executeUpdate();
		}
		return processingNum;
	}
	
	//ユーザー住所編集
}