package junit.regexp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import regexp.PasswordValidator;

public class PasswordValidatorTest {

	//失敗ケース
	@Test
	public void testIsHalfWidth() {
		String input = "ｎext-closet";
		
		//実測値
		boolean actual = PasswordValidator.isHalfWidth(input);
		assertFalse(actual);
	}
	
	//成功ケース
	@Test
	public void testIsHalfWidthSuccsess() {
		String input = "next-closet12";
		
		//実測値
		boolean actual = PasswordValidator.isHalfWidth(input);
		assertTrue(actual);
	}

}
