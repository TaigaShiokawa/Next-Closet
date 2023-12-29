package junit.model.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.dao.UserDAO;

public class UserDAOTest {
	
	UserDAO uDao = new UserDAO();

	//新規会員登録成功ケース
	@Test
	public void testRegisterUserSuccess() {
		
		int resultSuccess = uDao.registerUser("山田　太郎", "やまだ　たろう", "taro.yamada@mail.com", "yamada-pass", "08011112222");
		assertEquals(1, resultSuccess);
	}
	
	//新規会員登録失敗ケース
	@Test
	public void testRegisterUserFailure() {
		
		//プログラム実行時に発生する可能性のあるエラー(引数が無効など)
		assertThrows(RuntimeException.class, () -> {
            uDao.registerUser(null, null, "test.yamada@mail.com", "yamada-pass", "09011112222");
        });
	}

	@Test
	void testRegisterAddress() {
		fail("まだ実装されていません");
	}

	@Test
	void testGetUserId() {
		fail("まだ実装されていません");
	}

	@Test
	void testGetUserAddressId() {
		fail("まだ実装されていません");
	}

	@Test
	void testUserLogin() {
		fail("まだ実装されていません");
	}

	@Test
	void testLoginUserUpdate() {
		fail("まだ実装されていません");
	}

	@Test
	void testLoginUserAddressUpdate() {
		fail("まだ実装されていません");
	}

	@Test
	void testGetUpdatedUser() {
		fail("まだ実装されていません");
	}

	@Test
	void testChangeUserPassword() {
		fail("まだ実装されていません");
	}

	@Test
	void testAddSubAddress() {
		fail("まだ実装されていません");
	}

	@Test
	void testGetSubAddress() {
		fail("まだ実装されていません");
	}

	@Test
	void testDeleteSubAddress() {
		fail("まだ実装されていません");
	}

	@Test
	void testUserWithdrawal() {
		fail("まだ実装されていません");
	}

	@Test
	void testGetAllUserInfo() {
		fail("まだ実装されていません");
	}

	@Test
	void testGetUserDetail() {
		fail("まだ実装されていません");
	}

	@Test
	void testUserTrueStatus() {
		fail("まだ実装されていません");
	}

}
