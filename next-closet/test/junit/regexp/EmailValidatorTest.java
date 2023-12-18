package junit.regexp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import regexp.EmailValidator;

class EmailValidatorTest {

	@Test
	//ドメインの第二部分がない失敗ケース
	void testValidate1() {
		String input = "taro@mail";
		boolean actual = EmailValidator.validate(input);
		assertFalse(actual);
	}
	
	@Test
	//ユーザー名が無いケース
	void testValidate2() {
		String input = "@mail.com";
		boolean actual = EmailValidator.validate(input);
		assertFalse(actual);
	}
	@Test
	//ドメインの第二部分が一文字のみのケース
	void testValidate3() {
		String input = "taro@mail.c";
		boolean actual = EmailValidator.validate(input);
		assertFalse(actual);
	}
	@Test
	//ドメイン名にピリオドが連続しているケース
	void testValidate4() {
		String input = "taro@mail..c";
		boolean actual = EmailValidator.validate(input);
		assertFalse(actual);
	}
	@Test
	//ユーザー名にスペースが含まれるケース
	void testValidate5() {
		String input = "taro yamada@mail.c";
		boolean actual = EmailValidator.validate(input);
		assertFalse(actual);
	}
}
