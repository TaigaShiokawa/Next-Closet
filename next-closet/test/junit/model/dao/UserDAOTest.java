package junit.model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import model.bean.AddressBean;
import model.bean.UserBean;
import model.dao.UserDAO;

public class UserDAOTest {
	
	UserDAO uDao = new UserDAO();
	
	@Nested
	public class registerToUsersTable {
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
	}

	@Nested
	public class registerToAddressesTable {
		@Test
		void testRegisterAddressSuccess() throws ClassNotFoundException, SQLException {
			int resultSuccess = uDao.registerAddress(25, "1234567", "兵庫県", "神戸市1-1");
			assertEquals(1, resultSuccess);
		}
		
		@Test
		void testRegisterAddressFailure() throws ClassNotFoundException, SQLException {
			Assertions.assertThrows(SQLException.class, () -> {
	            uDao.registerAddress(25, null, "Tokyo", "Shibuya");
	        });
		}
	}
	
	@Nested
	public class getUserIdFromUsersTable {
		//ユーザーID取得成功
		@Test
	    public void testGetUserIdWithExistingEmail() throws ClassNotFoundException, SQLException {
	        String existingEmail = "yamada@mail.com"; // 実際に存在するメールアドレス
	        int expectedUserId = 25; // 期待されるユーザーID

	        int userId = uDao.getUserId(existingEmail);

	        assertEquals(expectedUserId, userId, "存在するメールアドレスで正しいユーザーIDが取得されるべきです");
	    }
		
		//ユーザーID取得失敗
	    @Test
	    public void testGetUserIdWithNonExistingEmail() throws ClassNotFoundException, SQLException {
	        String nonExistingEmail = "yamada@gmail.com"; // 存在しないメールアドレス

	        int userId = uDao.getUserId(nonExistingEmail);

	        assertEquals(-1, userId, "存在しないメールアドレスで-1が返されるべきです");
	    }
	}
	
	@Nested
	public class getUserAddressIdFromAddressesTable {
		//アドレスID取得成功
		@Test
	    public void testGetUserAddressIdWithExistingUser() throws ClassNotFoundException, SQLException {
	        int existingUserId = 24; // 存在するユーザーID
	        AddressBean address = uDao.getUserAddressId(existingUserId);

	        assertNotNull(address, "アドレス情報はnullであってはならない");
	        assertEquals(existingUserId, address.getUserId(), "ユーザーIDが一致すること");
	    }
		
		//アドレスID取得失敗
	    @Test
	    public void testGetUserAddressIdWithNonExistingUser() throws ClassNotFoundException, SQLException {
	        int nonExistingUserId = 999; // 存在しないユーザーID
	        AddressBean address = uDao.getUserAddressId(nonExistingUserId);

	        assertNull(address, "存在しないユーザーIDではアドレス情報はnullでなければならない");
	    }
	}
	
	@Nested
	public class useLoginResult {
		//ログイン成功ケース
		@Test
	    public void testUserLoginWithValidCredentials() throws ClassNotFoundException, SQLException {
	        String email = "yamada@mail.com"; // 有効なメールアドレス
	        String password = "yamada-pass "; // 有効なパスワード
	        UserBean user = uDao.userLogin(email, password);

	        assertNotNull(user, "ユーザー情報はnullであってはならない");
	        assertEquals(email, user.getEmail(), "メールアドレスが一致すること");
	    }
		
		//ログイン失敗ケース
	    @Test
	    public void testUserLoginWithInvalidCredentials() throws ClassNotFoundException, SQLException {
	        String email = "yamada@gmail.com"; // 無効なメールアドレス
	        String password = "rootuser"; // 無効なパスワード
	        UserBean user = uDao.userLogin(email, password);

	        assertNull(user, "無効な認証情報ではユーザー情報はnullでなければならない");
	    }
	}
	
	@Nested
	public class updateUserInformationInusersTable {
		//更新成功
		@Test
	    public void testLoginUserUpdateWithValidData() throws ClassNotFoundException, SQLException {
	        int userId = 25; // 有効なユーザーID
	        int updatedRows = uDao.loginUserUpdate("New Name", "New Kana", "123456789", "yamada@mail.co.jp", userId);
	        assertTrue(updatedRows > 0, "有効なデータでユーザー情報が更新されるべきです");
	    }

		//更新失敗
	    @Test
	    public void testLoginUserUpdateWithInvalidUserId() throws ClassNotFoundException, SQLException {
	        int invalidUserId = 9999; // 無効なユーザーID
	        int updatedRows = uDao.loginUserUpdate("Name", "Kana", "123456789", "email@example.com", invalidUserId);
	        assertEquals(0, updatedRows, "無効なユーザーIDで更新は行われないべきです");
	    }
		
	}
	
	@Nested
	public class updateUserAddressInfomationInAddressesTable {
		//更新成功
		@Test
	    public void testLoginUserAddressUpdateWithValidData() throws ClassNotFoundException, SQLException {
	        int userId = 25; // 有効なユーザーID
	        int updatedRows = uDao.loginUserAddressUpdate("123-4567", "Tokyo", "Shibuya", userId);
	        assertTrue(updatedRows > 0, "有効なデータでユーザーの住所が更新されるべきです");
	    }

		//更新失敗
	    @Test
	    public void testLoginUserAddressUpdateWithInvalidUserId() throws ClassNotFoundException, SQLException {
	        int invalidUserId = 9999; // 無効なユーザーID
	        int updatedRows = uDao.loginUserAddressUpdate("123-4567", "Tokyo", "Shibuya", invalidUserId);
	        assertEquals(0, updatedRows, "無効なユーザーIDで住所の更新は行われないべきです");
	    }
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
