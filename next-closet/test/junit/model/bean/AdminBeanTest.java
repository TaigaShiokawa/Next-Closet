package junit.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import model.bean.AdminBean;

class AdminBeanTest {

	@Test
	void testAdminBean() {
		
		AdminBean admin = new AdminBean();
		
		int adminId = 1 ;
		String adminName = "テスト　太郎";
		String adminKanaName = "テスト　タロウ";
		String email = "test.mail.com";
		String password = "aaaaaaaa";
		boolean adminStatus = true;
		Date registrationDate =  new Date(System.currentTimeMillis());
		
		admin.setAdminId(adminId);
		admin.setAdminName(adminName);
		admin.setAdminKanaName(adminKanaName);
		admin.setEmail(email);
		admin.setPassword(password);
		admin.setAdminStatus(adminStatus);
		admin.setRegistrationDate(registrationDate);
		
		 // ゲッターを使って値を取得し、設定した値と一致することを確認
        assertEquals(adminId, admin.getAdminId());
        assertEquals(adminName, admin.getAdminName());
        assertEquals(adminKanaName, admin.getAdminKanaName());
        assertEquals(email, admin.getEmail());
        assertEquals(adminStatus, admin.isAdminStatus());
        assertEquals(registrationDate, admin.getRegistrationDate());
        
        // 失敗ケース
        assertEquals(2, admin.getAdminId());
        assertEquals("テスト　失敗", admin.getAdminName());
        assertEquals("テスト　シッパイ", admin.getAdminKanaName());
        assertEquals("notest.mail.com", admin.getEmail());
        assertEquals(false, admin.isAdminStatus());
        assertEquals("2023-12-25", admin.getRegistrationDate());
        
		
	}

}
