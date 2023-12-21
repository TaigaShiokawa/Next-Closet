package junit.regexp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import regexp.PasswordStrengthChecker;

public class PasswordStrengthCheckerTest {

	@Test
	public void testCalculatePasswordStrength() {
		//パスワード強度レベル 1. パスワードが8文字未満(登録はできない)の場合
		assertEquals(1, PasswordStrengthChecker.calculatePasswordStrength("1234567"));
		
		//パスワード強度レベル 2. パスワードが全て小文字で8文字を超える場合
		assertEquals(2, PasswordStrengthChecker.calculatePasswordStrength("abcdefgh"));
	
		//パスワード強度レベル 3. パスワードが小文字+大文字+8文字以上
		assertEquals(3, PasswordStrengthChecker.calculatePasswordStrength("aBcDeFgH"));
		
		//パスワード強度レベル 4. パスワードが小文字+大文字+数字+8文字以上
		assertEquals(4, PasswordStrengthChecker.calculatePasswordStrength("aBcDeF12"));
		
		//パスワード強度レベル 5. パスワードが小文字+大文字+数字+特殊文字+8文字以上
		assertEquals(5, PasswordStrengthChecker.calculatePasswordStrength("aBcD12@?"));
		
		
	}

}
