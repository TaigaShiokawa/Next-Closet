package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		final String URL ="jdbc:mysql://localhost/todo_test_db?useUnicode=true&characterEncoding=utf8";
		final String USER ="root";
		final String PASS ="";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(URL,USER,PASS);
		return con;	
	}
}
