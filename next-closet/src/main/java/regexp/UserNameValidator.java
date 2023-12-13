package regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserNameValidator {
	private static final String USER_NAME_PATTERN = "^[ぁ-んァ-ヶ一-龠々0-9A-Za-z\u3000 ]+$";
	
	public static boolean validate(String userName) {
		Pattern pattern = Pattern.compile(USER_NAME_PATTERN);
		Matcher matcher = pattern.matcher(userName);
		return matcher.matches();
	}

}
