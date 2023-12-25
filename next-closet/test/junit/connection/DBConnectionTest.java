package junit.connection;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import connection.DBConnection;

public class DBConnectionTest {

	@Test
	public void testConnection() throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.getConnection();
		
		//接続確認
		assertNotNull(con, "接続がnullです");
		
		//接続のclose確認
		assertFalse(con.isClosed(), "接続が閉ざされています");
		
		con.close();
	}

}
