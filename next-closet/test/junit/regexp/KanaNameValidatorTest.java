package junit.regexp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import regexp.KanaNameValidator;

public class KanaNameValidatorTest {

	@Nested
	public class validateTest {
		@Test
		//半角スペースが入っているケース
		public void testValidate1() {
			String input = "ヤマダ タロウ";
			boolean actual = KanaNameValidator.validate(input);
			assertFalse(actual);
		}

		@Test
		//スペースが入ってないケース
		public void testValidate2() {
			String input = "ヤマダタロウ";
			boolean actual = KanaNameValidator.validate(input);
			assertFalse(actual);
		}
	}

	@Test
	//成功ケース
	public void testValidateSuccess() {
		String input = "ヤマダ　タロウ";
		boolean actual = KanaNameValidator.validate(input);
		assertTrue(actual);
	}
}
