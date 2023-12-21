package regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserNameValidator {
	//全角スペースを強制(半角のスペースは許可しない)
	//ひらがな、カタカナ、漢字、繰り返し漢字記号(々など)、特殊文字、英字、および全角スペースを含む文字列で、少なくとも1つの全角スペースを含む
	private static final String USER_NAME_PATTERN = "^(?=.*\u3000)[ぁ-んァ-ヶ一-龠々0-9A-Za-z\u3000]+$"; 
	
	public static boolean validate(String userName) {
		Pattern pattern = Pattern.compile(USER_NAME_PATTERN);
		Matcher matcher = pattern.matcher(userName);
		return matcher.matches();
	}
}