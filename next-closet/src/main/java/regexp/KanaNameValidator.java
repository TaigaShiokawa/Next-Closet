package regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KanaNameValidator {
	//全角スペースを強制(半角のスペースは許可しない)
	private static final String KANANAME_PATTERN = "^(?=.*\\u3000)[ァ-ヶー\u3000]+$"; 
	public static boolean validate(String password) {
		Pattern pattern = Pattern.compile(KANANAME_PATTERN);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
}