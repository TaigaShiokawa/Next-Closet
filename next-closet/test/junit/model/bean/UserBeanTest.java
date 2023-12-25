package junit.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import model.bean.UserBean;

class UserBeanTest {

	@Test
    public void testUserBean() {
        // UserBean のインスタンスを作成
        UserBean user = new UserBean();

        // テストデータの設定
        int userId = 1;
        String userName = "Test User";
        String kanaName = "テストユーザー";
        String email = "test@example.com";
        String hashPass = "hashedpassword";
        Date registerDate = new Date(System.currentTimeMillis());
        String telNumber = "1234567890";
        boolean userStatus = true;

        // セッターを使って値を設定
        user.setUserId(userId);
        user.setUserName(userName);
        user.setKanaName(kanaName);
        user.setEmail(email);
        user.setHashPass(hashPass);
        user.setRegisterDate(registerDate);
        user.setTelNumber(telNumber);
        user.setUserStatus(userStatus);

        // ゲッターを使って値を取得し、設定した値と一致することを確認
        assertEquals(userId, user.getUserId());
        assertEquals(userName, user.getUserName());
        assertEquals(kanaName, user.getKanaName());
        assertEquals(email, user.getEmail());
        assertEquals(hashPass, user.getHashPass());
        assertEquals(registerDate, user.getRegisterDate());
        assertEquals(telNumber, user.getTelNumber());
        assertEquals(userStatus, user.isUserStatus());
    }

}
