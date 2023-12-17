package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.DBConnection;
import model.bean.AddressBean;
import model.bean.UserBean;

public class UserDAO {
	
	//新規登録 (usersテーブル)
	public int registerUser(String userName, String kanaName, String email, String password, String telNumber) {
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
		} catch(SQLException e) {
			String errorMessage = "SQL [" + sql + "] の実行中にエラーが発生しました: userName=" + userName + 
                    ", kanaName=" + kanaName + ", email=" + email + ", telNumber=" + telNumber + 
                    ". Error: " + e.getMessage();
			// エラーメッセージをログに記録する
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, errorMessage, e);
			throw new RuntimeException("SQL実行中にエラーが発生しました.", e);
			
			//以下、自分のデスクトップにLogを出力するためのコードなので無視でOK
//			Logger logger = LogToFile.getLogger();
//	        logger.log(Level.SEVERE, "SQL Error: " + e.getMessage(), e);
//	        long delay = 60 * 1000; 
//	        LogFileDelete.deleteLogFileAfter("/Users/shiokawa.taiga/Desktop/nextClosetLogger.txt", delay);
		} catch(ClassNotFoundException e) {
			// DB接続ドライバが見つからない場合にスローされる例外
	        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "DB driver not found", e);
	        throw new RuntimeException("DBドライバーが見つかりませんでした.", e);
	        
	        //以下、自分のデスクトップにLogを出力するためのコードなので無視でOK
//			Logger logger = LogToFile.getLogger();
//			logger.log(Level.SEVERE, "DBドライバーが見つかりませんでした." + e);
//			long delay = 60 * 1000; 
//	        LogFileDelete.deleteLogFileAfter("/Users/shiokawa.taiga/Desktop/nextClosetLogger.txt", delay);
//			throw new RuntimeException("DBドライバーが見つかりませんでした.", e);
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
	//ユーザIDを取得
	public int getUserId(String email)throws ClassNotFoundException, SQLException {

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
				address.setUserId(res.getInt("user_id"));
				address.setPostCode(res.getString("post_code"));
				address.setPrefectures(res.getString("prefectures"));
				address.setAddress(res.getString("address"));
			}
			return address;
		}
	}
	

	//サブの方のアドレス取得
//		public List < AddressBean > getUserAddAddress(int userId) throws ClassNotFoundException, SQLException {
//			String sql = "SELECT * FROM add_addresses WHERE user_id = ?";
//			try (Connection con = DBConnection.getConnection(); 
//					PreparedStatement pstmt = con.prepareStatement(sql)) {
//				AddressBean address = new AddressBean();
//				pstmt.setInt(1, userId);
//				
//				ResultSet res = pstmt.executeQuery();
//				while(res.next()) {
//					address = new AddressBean();
//					address.setUserId(res.getInt("user_id"));
//					address.setPostCode(res.getString("post_code"));
//					address.setPrefectures(res.getString("prefectures"));
//					address.setAddress(res.getString("address"));
//				}
//				return address;
//			}
//		}


	//ユーザーログイン
	public UserBean userLogin(String email, String password) 
			throws ClassNotFoundException, SQLException {
		
		UserBean user = new UserBean();
		
		String sql = "SELECT * FROM users WHERE email = ? AND hash_pass = ? AND user_status = true";
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
				user.setHashPass(res.getString("hash_pass"));
				user.setTelNumber(res.getString("tel_number"));
				user.setUserStatus(res.getBoolean("user_status"));
			}
		}
		return user;
	}
	
	//ユーザー情報 編集
	public int loginUserUpdate(String userName, String kanaName, String telNumber, String email, int userId) 
			throws ClassNotFoundException, SQLException {
		int processingNum = 0;
		String sql = "UPDATE users SET user_name = ?, kana_name = ?, tel_number = ?, email = ? WHERE user_id = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, userName);
			pstmt.setString(2, kanaName);
			pstmt.setString(3, telNumber);
			pstmt.setString(4, email);
			pstmt.setInt(5, userId);
			
			processingNum = pstmt.executeUpdate();
		}
		return processingNum;
	}
	
	//ユーザー住所 編集
	public int loginUserAddressUpdate(String postCode, String prefectures, String address, int userId) throws ClassNotFoundException, SQLException {
		int processingNum = 0;
		String sql = "UPDATE addresses SET post_code = ?, prefectures = ?, address = ? WHERE user_id = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, postCode);
			pstmt.setString(2, prefectures);
			pstmt.setString(3, address);
			pstmt.setInt(4, userId);
			
			processingNum = pstmt.executeUpdate();
		}
		return processingNum;
	}
	
	//編集後、ユーザー取得
	public UserBean getUpdateUser(int userId) throws ClassNotFoundException, SQLException {
		UserBean user = new UserBean();
		String sql = "SELECT * FROM users WHERE user_id = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) { 
			pstmt.setInt(1, userId);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				user.setUserId(res.getInt("user_id"));
				user.setUserName(res.getString("user_name"));
				user.setKanaName(res.getString("kana_name"));
				user.setEmail(res.getString("email"));
				user.setHashPass(res.getString("hash_pass")); //消してもいいかも. 後で確認する.
				user.setTelNumber(res.getString("tel_number"));
			}
		}
		return user;
	}
	
	//パスワード変更
	public int userPasswordUpdate(int userId, String password) throws ClassNotFoundException, SQLException {
		int processingNum = 0;
		String sql = "UPDATE users SET hash_pass = ? WHERE user_id = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, password);
			pstmt.setInt(2, userId);
			
			processingNum = pstmt.executeUpdate();
		}
		return processingNum;
	}
	
	//サブ住所追加
	public int setSubAddress(int userId, String postCode, String address, String prefectures) 
			throws ClassNotFoundException, SQLException {
		int processingNum = 0;
		String sql = "INSERT INTO add_addresses (user_id, post_code, address, prefectures) VALUES (?, ?, ?, ?)";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			pstmt.setString(2, postCode);
			pstmt.setString(3, address);
			pstmt.setString(4, prefectures);
			
			processingNum = pstmt.executeUpdate();
		}
		return processingNum;
	}
	
	//サブ住所取得
	public List<AddressBean> getSubAddress(int userId) 
			throws ClassNotFoundException, SQLException {
		
		List<AddressBean> addressList = new ArrayList<>();
		String sql = "SELECT * FROM add_addresses WHERE user_id = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, userId);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				AddressBean address = new AddressBean();
				address.setAddAddressId(res.getInt("add_address_id"));
				address.setUserId(res.getInt("user_id"));
				address.setAddress(res.getString("address"));
				address.setPostCode(res.getString("post_code"));
				address.setPrefectures(res.getString("prefectures"));
				
				addressList.add(address);
			}
		}
		return addressList;
	}
	
	// サブ住所削除
	public int deleteSubAddress(int addAddressId) 
	        throws ClassNotFoundException, SQLException {
		
		int processingNum = 0;
	    String sql = "DELETE FROM add_addresses WHERE add_address_id = ?";
	    try (Connection con = DBConnection.getConnection(); 
	            PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setInt(1, addAddressId);
	        processingNum = pstmt.executeUpdate();
	    }
	    return processingNum;
	}
	
	//ユーザー退会
	public int chageUserStatus(int userId) throws ClassNotFoundException, SQLException {
		int processingNum = 0;
		String sql = "UPDATE users SET user_status = false WHERE user_id = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
			processingNum = pstmt.executeUpdate();
		}
		return processingNum;
	}
	
		
      //全ユーザーの情報取得
		public List< UserBean > getAllStatusUserList() throws ClassNotFoundException, SQLException {
			 List< UserBean > list = new  ArrayList <UserBean>();
			
			
			String sql = "SELECT * FROM users ";
			try (Connection con = DBConnection.getConnection(); 
					PreparedStatement pstmt = con.prepareStatement(sql)) { 
			    	ResultSet res = pstmt.executeQuery();
					
					while (res.next()){ 
		            	int user_id	    	 	  	= res.getInt("user_id");
		            	String user_name	     	= res.getString("user_name");
		            	String kana_name       	    = res.getString("kana_name");
		            	String email  				= res.getString("email");
		            	String hash_pass            = res.getString("hash_pass");
		            	Date register_date          = res.getDate("registration_date"); 
		            	String tel_number   		= res.getString("tel_number");
		            	boolean status       		= res.getBoolean("user_status");
		            	list.add(new UserBean (user_id, user_name,  kana_name, email, hash_pass ,  register_date ,tel_number , status ));
		            }
				}
			
			return list;
		}
		//ユーザーidからユーザーの情報を取得（アドミン用ユーザー詳細情報用）
				public List<UserBean> getUserDetail(int userId) throws ClassNotFoundException, SQLException {
					List< UserBean > userList = new ArrayList<UserBean>();
					
					String sql = "SELECT * FROM users JOIN addresses ON users.user_id = addresses.user_id WHERE users.user_id = ?";
					//sql実行
					try (Connection con = DBConnection.getConnection();
												PreparedStatement pstmt = con.prepareStatement(sql)){
						
						pstmt.setInt(1, userId);
						
						ResultSet res = pstmt.executeQuery();
						
						while (res.next()) {
						
						int user_id = res.getInt("user_id"); 
						String user_name = res.getString("user_name");
						String kana_name = res.getString("kana_name");
						String email = res.getString("email");
						String hash_pass = res.getString("hash_pass");
						Date register_date = res.getDate("registration_date");
						String tel_number = res.getString("tel_number");
						boolean user_status = res.getBoolean("user_status");
						
						int address_id = res.getInt("address_id");
						int useradd_id = res.getInt("user_id");
						String post_code = res.getString("post_code");
						String address = res.getString("address");
						String prefectures = res.getString("prefectures");
						
						UserBean userLists = new UserBean(user_id, user_name, kana_name, email,hash_pass, register_date,  tel_number, user_status);
						
						userLists.setAddressId(address_id);
						userLists.setUserId(useradd_id);
						userLists.setPostCode(post_code);
						userLists.setAddress(address);
						userLists.setPrefectures(prefectures);
						
						userList.add(userLists);
						
						}
					}
					return userList;
				}
				
}

