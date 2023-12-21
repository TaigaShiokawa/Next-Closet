package junit.regexp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import regexp.TelNumberValidator;

class TelNumberValidatorTest {

	//失敗ケース
	@Test
	public void testValidate() {
		String input = "1234567890";
		
		//実測値
		boolean actual = TelNumberValidator.validate(input);
		assertFalse(actual);
	}
	
	//成功ケース
	@Test
	public void testValidateSuccess() {
		String input = "12345678910";
		
		//実測値
		boolean actual = TelNumberValidator.validate(input);
		assertTrue(actual);
	}

}
