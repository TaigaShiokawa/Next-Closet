package junit.regexp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import regexp.AddressValidator;

public class AddressValidatorTest {

	@Test
	public void testConvertFullWidthNumbersToHalfwidth() {
		String input = "１２３４５";
		
		//期待値
	    String expected = "12345";
	    
	    //実装値
	    String actual = AddressValidator.convertFullWidthNumbersToHalfwidth(input);
	    System.out.println("全角を半角へ: OK");
	    assertEquals(expected, actual);
	}

	@Test
	public void testRemoveUnnecessarySpaces() {
	    String input = "   不要な   スペース  ";
	    
	    //期待値
	    String expected = "不要な スペース";
	    
	    //実測値
	    String actual = AddressValidator.removeUnnecessarySpaces(input);
	    System.out.println("不要スペース削除: OK");
	    assertEquals(expected, actual);
	}

	@Test
	public void testNormalizeAddress() {
	    String input = "１２３   ４５  ";
	    
	    //期待値
	    String expected = "123 45";
	    
	    //実測値
	    String actual = AddressValidator.normalizeAddress(input);
	    System.out.println("全角を半角へ: OK");
	    assertEquals(expected, actual);
	}

}
