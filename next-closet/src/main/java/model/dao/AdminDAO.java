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

	public boolean validate(String email,String password) throws ClassNotFoundException, SQLException {
		
		boolean status = false;
		//管理者ログイン用
		String sql ="SELECT * FROM admins WHERE email = ? and hash_pass = ?";


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
	
	
	//全管理者の情報取得
			public List< AdminBean > getAllStatusAdminList() throws ClassNotFoundException, SQLException {
				 List< AdminBean > list = new  ArrayList <AdminBean>();
				
				String sql = "SELECT * FROM admin ";
				try (Connection con = DBConnection.getConnection(); 
						PreparedStatement pstmt = con.prepareStatement(sql)) { 
				    	ResultSet res = pstmt.executeQuery();
						
						while (res.next()){ 
			            	int admin_id	    	 	= res.getInt("admin_id");
			            	String admin_name	     	= res.getString("admin_name");
			            	String kana_name       	    = res.getString("admin_kana_name");
			            	String email  				= res.getString("email");
			            	String hash_pass            = res.getString("hash_pass");
			            	boolean status       		= res.getBoolean("admin_status");
			            	Date registration_date      = res.getDate("registration_date"); 

			            	list.add(new AdminBean (admin_id, admin_name,  kana_name, email, hash_pass , status , registration_date ));
			            }
					}
				
				return list;
			}
	
	

}