package regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelNumberValidator {
	
	//11文字以外は無効
	private static final String TEL_NUMBER_PATTERN = "^\\d{11}$";
	
	public static boolean validate(String postCode) {
		Pattern pattern = Pattern.compile(TEL_NUMBER_PATTERN);
		Matcher matcher = pattern.matcher(postCode);
		return matcher.matches();
	}

}
