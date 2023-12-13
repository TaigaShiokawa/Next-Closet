package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;

public class AdminDao {

	public boolean validate(String email,String password) throws ClassNotFoundException, SQLException {
		
		boolean status = false;
		
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
}
