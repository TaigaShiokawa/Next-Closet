package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import model.bean.AdminBean;

public class AdminDAO {
	
    // 管理者ログイン
	public AdminBean validate(String email,String password)
			throws ClassNotFoundException, SQLException {
		AdminBean admin = new AdminBean();
		String sql ="SELECT * FROM admins WHERE email = ? and hash_pass = ? and admin_status = true";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet res = stmt.executeQuery();
			while(res.next()) {
				admin = new AdminBean();
				admin.setAdminId(res.getInt("admin_id"));
				admin.setAdminName(res.getString("admin_name"));
				admin.setAdminKanaName(res.getString("admin_kana_name"));
				admin.setEmail(res.getString("email"));
				admin.setPassword(res.getString("hash_pass"));
				admin.setAdminStatus(res.getBoolean("admin_status"));
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
	
	//全管理者の情報取得
	public List< AdminBean > getAllStatusAdminList()
			throws ClassNotFoundException, SQLException {
		List< AdminBean > list = new  ArrayList <AdminBean>();
		String sql = "SELECT * FROM admins ";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) { 
			ResultSet res = pstmt.executeQuery();
			while (res.next()) { 
				int admin_id = res.getInt("admin_id");
			    String admin_name = res.getString("admin_name");
			    String kana_name = res.getString("admin_kana_name");
			    String email = res.getString("email");
			    String hash_pass = res.getString("hash_pass");
			    boolean status = res.getBoolean("admin_status");
			    Date registration_date = res.getDate("registration_date"); 

			    list.add(new AdminBean (admin_id, admin_name,  kana_name, email, hash_pass , status , registration_date ));
			}
		}
		return list;
	}
	
	//管理者のパスワードを変更
	public int updatePass(String password,int adminId) 
			throws SQLException, ClassNotFoundException {
		int row = 0;
		String sql = "UPDATE admins SET hash_pass = ? WHERE admin_id = ?";
	    try (Connection con = DBConnection.getConnection();
	    		PreparedStatement pstmt = con.prepareStatement(sql)){
	    	pstmt.setString(1, password);
	    	pstmt.setInt(2, adminId);
	    	row = pstmt.executeUpdate();
	    } catch (SQLException | ClassNotFoundException e) {
	    	e.printStackTrace();
	    	throw e;
	    }
	    return row;
	}
	
	//管理者のパスワード以外の情報を変更
	public int updateAdmin(String adminName, String kanaName, String email,int adminId)
			throws SQLException, ClassNotFoundException {
		int row = 0;
		String sql = "UPDATE admins SET admin_name = ?, admin_kana_name = ?, email = ? WHERE admin_id = ?";
		try (Connection con = DBConnection.getConnection();
	    		PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1, adminName);
	        pstmt.setString(2, kanaName);
	        pstmt.setString(3, email);
	        pstmt.setInt(4, adminId);
	        row = pstmt.executeUpdate();
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        throw e;
	    }
	    return row;
	}
	
	//管理者IDを取得
	public int getAdminId(String email)
			throws ClassNotFoundException, SQLException {
		int AdminId = -1;
		String sql = "SELECT Admin_id FROM admins WHERE email = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, email);
			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				AdminId = res.getInt("admin_id");
			}
		}
		return AdminId;
	}
	
	//ID毎に管理者の情報を受け取る
	public AdminBean getDetailadmin(int adminId)
			throws ClassNotFoundException, SQLException {
		AdminBean admin = new AdminBean();
		String sql = "SELECT * FROM admins WHERE admin_id = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) { 
			pstmt.setInt(1, adminId);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				admin.setAdminId(res.getInt("admin_id"));
				admin.setAdminName(res.getString("admin_name"));
				admin.setAdminKanaName(res.getString("admin_kana_name"));
				admin.setEmail(res.getString("email"));
				admin.setAdminStatus(res.getBoolean("admin_status"));
				admin.setRegistrationDate(res.getDate("registration_date"));
				admin.setEmail(res.getString("email"));
			}
		}
		return admin;
	}
	
			//管理者を復元
			public void adminTrueStatus(int adminId) throws ClassNotFoundException, SQLException {

				String sql = "UPDATE admins SET admin_status = true WHERE admin_id = ?";
				try (Connection con = DBConnection.getConnection(); 
						PreparedStatement pstmt = con.prepareStatement(sql)) {
					pstmt.setInt(1, adminId);
					pstmt.executeUpdate();
				}
			}

	//管理者の削除
		public int adminDelete(int adminId) throws ClassNotFoundException, SQLException {
			int processingNum = 0;
			String sql = "UPDATE admins SET admin_status = false WHERE admin_id = ?";
			try (Connection con = DBConnection.getConnection(); 
					PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setInt(1, adminId);
				pstmt.executeUpdate();
				processingNum = pstmt.executeUpdate();
			}
			return processingNum;
		}
}