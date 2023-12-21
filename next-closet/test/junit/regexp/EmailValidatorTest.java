package junit.regexp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import regexp.EmailValidator;

public class EmailValidatorTest {

	@Nested
	public class validateTest {
		//ドメインの第二部分がない失敗ケース
		@Test
		public void testValidate1() {
			String input = "taro@mail";
			boolean actual = EmailValidator.validate(input);
			assertFalse(actual);
		}

		//ユーザー名が無いケース
		@Test
		public void testValidate2() {
			String input = "@mail.com";
			boolean actual = EmailValidator.validate(input);
			assertFalse(actual);
		}

		//ドメインの第二部分が一文字のみのケース
		@Test
		public void testValidate3() {
			String input = "taro@mail.c";
			boolean actual = EmailValidator.validate(input);
			assertFalse(actual);
		}

		//ドメイン名にピリオドが連続しているケース
		@Test
		public void testValidate4() {
			String input = "taro@mail..c";
			boolean actual = EmailValidator.validate(input);
			assertFalse(actual);
		}

		//ユーザー名にスペースが含まれるケース
		@Test
		public void testValidate5() {
			String input = "taro yamada@mail.c";
			boolean actual = EmailValidator.validate(input);
			assertFalse(actual);
		}
	}

	@Nested
	public class validateSuccessTest {
		//成功ケース
		@Test
		public void testValidateSuccess1() {
			String input = "taro.yamada@mail.com";
			boolean actual = EmailValidator.validate(input);
			assertTrue(actual);
		}

		//成功ケース
		@Test
		public void testValidateSuccess2() {
			String input = "taro@mail.co.jp";
			boolean actual = EmailValidator.validate(input);
			assertTrue(actual);
		}
	}
}
