package regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostCodeValidator {
	//ハイフンなしの7文字
	private static final String POSTCODE_PATTERN = "^\\d{7}$";
	
	public static boolean validate(String postCode) {
		Pattern pattern = Pattern.compile(POSTCODE_PATTERN);
		Matcher matcher = pattern.matcher(postCode);
		return matcher.matches();
	}
}
