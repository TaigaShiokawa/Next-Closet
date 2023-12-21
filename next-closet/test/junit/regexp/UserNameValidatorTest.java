package junit.regexp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import regexp.UserNameValidator;

class UserNameValidatorTest {
	
	//失敗ケース
	@Nested
	public class validate {
		
		//姓のみ
		@Test
		public void testValidate1() {
			String input = "山田";
			boolean actual = UserNameValidator.validate(input);
			assertFalse(actual);
		}
		
		//姓の後ろに半角スペース
		@Test
		public void testValidate2() {
			String input = "山田 ";
			boolean actual = UserNameValidator.validate(input);
			assertFalse(actual);
		}
		
		//先頭に半角スペース
		@Test
		public void testValidate3() {
			String input = " 山田";
			boolean actual = UserNameValidator.validate(input);
			assertFalse(actual);
		}
	}
	
	//成功ケース
	@Nested
	public class validateSuccessTest {
		
		//ローマ字
		@Test
		public void testValidateSuccsess1() {
			String input = "yamada　taro";
			boolean actual = UserNameValidator.validate(input);
			assertTrue(actual);
		}
		
		//カタカナ
		@Test
		public void testValidateSuccsess2() {
			String input = "ヤマダ　タロウ";
			boolean actual = UserNameValidator.validate(input);
			assertTrue(actual);
		}
		
		//ひらがな
		@Test
		public void testValidateSuccsess3() {
			String input = "やまだ　たろう";
			boolean actual = UserNameValidator.validate(input);
			assertTrue(actual);
		}
		
		//漢字
		@Test
		public void testValidateSuccsess4() {
			String input = "山田　太郎";
			boolean actual = UserNameValidator.validate(input);
			assertTrue(actual);
		}
	}

}
