package junit.regexp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import regexp.PostCodeValidator;

class PostCodeValidatorTest {
	
	//失敗ケース
	@Nested
	public class validateTest {
		@Test
		public void testPostCode1() {
			String input = "123456";
			
			//実測値
			boolean actual = PostCodeValidator.validate(input);
			assertFalse(actual);
		}
		@Test
		public void testPostCode2() {
			String input = "12345-6";
			
			//実測値
			boolean actual = PostCodeValidator.validate(input);
			assertFalse(actual);
		}
		
	}
	
	//成功ケース
	@Test
	public void testPostCodeSuccsess() {
		String input = "1234567";
		
		//実測値
		boolean actual = PostCodeValidator.validate(input);
		assertTrue(actual);
	}
}
