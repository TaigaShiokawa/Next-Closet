package junit.regexp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import regexp.EmailValidator;

public class EmailValidatorTest {

	@Nested
	public class validateTest {
		@Test
		//ドメインの第二部分がない失敗ケース
		public void testValidate1() {
			String input = "taro@mail";
			boolean actual = EmailValidator.validate(input);
			assertFalse(actual);
		}

		@Test
		//ユーザー名が無いケース
		public void testValidate2() {
			String input = "@mail.com";
			boolean actual = EmailValidator.validate(input);
			assertFalse(actual);
		}

		@Test
		//ドメインの第二部分が一文字のみのケース
		public void testValidate3() {
			String input = "taro@mail.c";
			boolean actual = EmailValidator.validate(input);
			assertFalse(actual);
		}

		@Test
		//ドメイン名にピリオドが連続しているケース
		public void testValidate4() {
			String input = "taro@mail..c";
			boolean actual = EmailValidator.validate(input);
			assertFalse(actual);
		}

		@Test
		//ユーザー名にスペースが含まれるケース
		public void testValidate5() {
			String input = "taro yamada@mail.c";
			boolean actual = EmailValidator.validate(input);
			assertFalse(actual);
		}
	}

	@Nested
	public class validateSuccessTest {
		@Test
		//成功ケース
		public void testValidateSuccess1() {
			String input = "taro.yamada@mail.com";
			boolean actual = EmailValidator.validate(input);
			assertTrue(actual);
		}

		@Test
		//成功ケース
		public void testValidateSuccess2() {
			String input = "taro@mail.co.jp";
			boolean actual = EmailValidator.validate(input);
			assertTrue(actual);
		}
	}
}
