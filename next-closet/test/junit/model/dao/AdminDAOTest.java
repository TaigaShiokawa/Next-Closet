package junit.model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.bean.AdminBean;
import model.dao.AdminDAO;

class AdminDAOTest {

	 @Test
	    public void testValidate() throws SQLException, ClassNotFoundException {
	        // テストデータの準備
	        String email = "tanaka@email.com";
	        String password = "7e071fd9b023ed8f18458a73613a0834f6220bd5cc50357ba3493c6040a9ea8c";

	        	// データベースへの接続
	        	try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/next_closet_db", "root", "")) {
	            // テストデータの挿入
	            String insertSql = "INSERT INTO admins (admin_name, admin_kana_name, email, hash_pass, admin_status) " +
	                    "VALUES ('田中　健二', 'タナカ　ケンジ', ?, ?, 1)";
	            
	            try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
	                pstmt.setString(1, email);
	                pstmt.setString(2, password);
	                pstmt.executeUpdate();
	            }

	            // DAOのインスタンスを作成
	            AdminDAO adminDAO = new AdminDAO();

	            // テスト実行
	            AdminBean admin = adminDAO.validate(email, password);

	            // 期待値と実際の結果の比較
	            assertNotNull(admin);
	            assertEquals(email, admin.getEmail());
	            assertEquals(password, admin.getPassword());
	        }
	    }
	 
    @Test
    public void testRegisterAdmin() throws SQLException {
        // テストデータの準備
        String adminName = "田中　健二";
        String kanaName = "タナカ　ケンジ";
        String email = "tanaka@email.com";
        String password = "7e071fd9b023ed8f18458a73613a0834f6220bd5cc50357ba3493c6040a9ea8c";

        // データベース接続
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/next_closet_db", "root", "");
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO admins (admin_name, admin_kana_name, email, hash_pass) VALUES (?, ?, ?, ?)")) {

            // パラメータの設定
            pstmt.setString(1, adminName);
            pstmt.setString(2, kanaName);
            pstmt.setString(3, email);
            pstmt.setString(4, password);

            // テスト実行
            int result = pstmt.executeUpdate();

            // 期待値と実際の結果の比較
            assertEquals(1, result);
        }
    }
    
    @Test
    public void testGetAllStatusAdminList() throws SQLException, ClassNotFoundException {
        // テストデータの挿入
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/next_closet_db", "root", "")) {
            String insertSql = "INSERT INTO admins (admin_name, admin_kana_name, email, hash_pass, admin_status, registration_date) " +
                    "VALUES ('田中　健二', 'タナカ ケンジ', 'tanaka@email.com', '7e071fd9b023ed8f18458a73613a0834f6220bd5cc50357ba3493c6040a9ea8c', 1, '2023-12-17 23:51:07.0')";
            try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
                pstmt.executeUpdate();
            }

            // DAOのインスタンスを作成
            AdminDAO adminDAO = new AdminDAO();

            List<AdminBean> adminList = adminDAO.getAllStatusAdminList();

            // 期待値と実際の結果の比較
            assertFalse(adminList.isEmpty());
            AdminBean admin = adminList.get(0);
            assertEquals("田中　健二", admin.getAdminName());
            assertEquals("タナカ　ケンジ", admin.getAdminKanaName());
            assertEquals("tanaka@email.com", admin.getEmail());
            assertEquals("7e071fd9b023ed8f18458a73613a0834f6220bd5cc50357ba3493c6040a9ea8c", admin.getPassword());
            assertTrue(admin.isAdminStatus());
            assertNotNull(admin.getRegistrationDate());
        }
    }
    
    @Test
    public void testUpdatePass() throws SQLException, ClassNotFoundException {
        // テストデータの挿入
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/next_closet_db", "root", "")) {
            // テストデータの挿入
            String insertSql = "INSERT INTO admins (admin_name, admin_kana_name, email, hash_pass, admin_status, registration_date) " +
                    "VALUES ('田中　健二', 'タナカ ケンジ', 'tanaka@email.com', '7e071fd9b023ed8f18458a73613a0834f6220bd5cc50357ba3493c6040a9ea8c', 1, '2023-12-17 23:51:07.0')";
            try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
                pstmt.executeUpdate();
            }

            // DAOのインスタンスを作成
            AdminDAO adminDAO = new AdminDAO();

            // パスワードの変更
            int adminId = getAdminIdByEmail(con, "tanaka@email.com"); // テストデータの admin_id 取得
            int updatedRows = adminDAO.updatePass("newPassword", adminId);

            // 変更が成功したことを確認
            assertEquals(1, updatedRows);

            // 変更されたパスワードが正しいことを確認
            String updatedPassword = getPasswordByAdminId(con, adminId);
            assertEquals("newPassword", updatedPassword);
        }
    }

    private int getAdminIdByEmail(Connection con, String email) throws SQLException {
        String selectSql = "SELECT admin_id FROM admins WHERE email = ?";
        try (PreparedStatement pstmt = con.prepareStatement(selectSql)) {
            pstmt.setString(1, email);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                resultSet.next();
                return resultSet.getInt("admin_id");
            }
        }
    }

    private String getPasswordByAdminId(Connection con, int adminId) throws SQLException {
        String selectSql = "SELECT hash_pass FROM admins WHERE admin_id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(selectSql)) {
            pstmt.setInt(1, adminId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                resultSet.next();
                return resultSet.getString("hash_pass");
            }
        }
    }
    
    @Test
    public void testUpdateAdmin() throws SQLException, ClassNotFoundException {
        // テストデータの挿入
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/next_closet_db", "root", "")) {
            // テストデータの挿入
            String insertSql = "INSERT INTO admins (admin_name, admin_kana_name, email, hash_pass, admin_status, registration_date) " +
                    "VALUES ('田中　健二', 'タナカ ケンジ', 'tanaka@email.com', '7e071fd9b023ed8f18458a73613a0834f6220bd5cc50357ba3493c6040a9ea8c', 1, '2023-12-17 23:51:07.0')";
            try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
                pstmt.executeUpdate();
            }

            // DAOのインスタンスを作成
            AdminDAO adminDAO = new AdminDAO();

            // 管理者情報の変更
            int adminId = getAdminIdByEmail(con, "tanaka@email.com"); // テストデータの admin_id 取得
            int updatedRows = adminDAO.updateAdmin("New Admin", "テスト アドミン", "new@example.com", adminId);

            // 変更が成功したことを確認
            assertEquals(1, updatedRows);

            // 変更された情報が正しいことを確認
            AdminBean updatedAdmin = getAdminById(con, adminId);
            assertEquals("New Admin", updatedAdmin.getAdminName());
            assertEquals("テスト アドミン", updatedAdmin.getAdminKanaName());
            assertEquals("new@example.com", updatedAdmin.getEmail());
        }
    }


    private AdminBean getAdminById(Connection con, int adminId) throws SQLException {
        String selectSql = "SELECT * FROM admins WHERE admin_id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(selectSql)) {
            pstmt.setInt(1, adminId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                resultSet.next();
                return new AdminBean(
                        resultSet.getInt("admin_id"),
                        resultSet.getString("admin_name"),
                        resultSet.getString("admin_kana_name"),
                        resultSet.getString("email"),
                        resultSet.getString("hash_pass"),
                        resultSet.getBoolean("admin_status"),
                        resultSet.getDate("registration_date"));
            }
        }
    }
    
    @Test
    public void testGetAdminId() throws SQLException, ClassNotFoundException {
        // テストデータの挿入
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/next_closet_db", "root", "")) {
            // テストデータの挿入
            String insertSql = "INSERT INTO admins (admin_name, admin_kana_name, email, hash_pass, admin_status, registration_date) " +
                    "VALUES ('田中　健二', 'タナカ ケンジ', 'tanaka@email.com', '7e071fd9b023ed8f18458a73613a0834f6220bd5cc50357ba3493c6040a9ea8c', 1, '2023-12-17 23:51:07.0')";
            try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
                pstmt.executeUpdate();
            }

            // DAOのインスタンスを作成
            AdminDAO adminDAO = new AdminDAO();

            // 管理者IDの取得
            int adminId = adminDAO.getAdminId("tanaka@email.com");

            // 取得したIDが正しいことを確認
            assertEquals(getAdminIdByEmail(con, "tanaka@email.com"), adminId);
        }
    }
    
    @Test
    public void testAdminDelete() throws SQLException, ClassNotFoundException {
        // テストデータの挿入
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/next_closet_db", "root", "")) {
            // テストデータの挿入
            String insertSql = "INSERT INTO admins (admin_name, admin_kana_name, email, hash_pass, admin_status, registration_date) " +
                    "VALUES ('田中　健二', 'タナカ ケンジ', 'tanaka@email.com', '7e071fd9b023ed8f18458a73613a0834f6220bd5cc50357ba3493c6040a9ea8c', 1, '2023-12-17 23:51:07.0')";
            try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
                pstmt.executeUpdate();
            }

            // DAOのインスタンスを作成
            AdminDAO adminDAO = new AdminDAO();

            // 管理者IDの取得
            int adminId = getAdminIdByEmail(con, "tanaka@email.com");

            // 管理者の削除
            int result = adminDAO.adminDelete(adminId);

            // 削除が成功していることを確認
            assertEquals(1, result);

            // 削除された管理者が非アクティブであることを確認
            assertFalse(isAdminActive(con, adminId));
        }
    }

    private boolean isAdminActive(Connection con, int adminId) throws SQLException {
        String selectSql = "SELECT admin_status FROM admins WHERE admin_id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(selectSql)) {
            pstmt.setInt(1, adminId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                resultSet.next();
                return resultSet.getBoolean("admin_status");
            }
        }
    }
}
