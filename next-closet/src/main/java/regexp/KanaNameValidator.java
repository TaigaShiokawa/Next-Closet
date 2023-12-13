package regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KanaNameValidator {
	private static final String KANANAME_PATTERN = "^[ァ-ヶー\u3000 ]+$"; //全角または半角のスペースを強制させる

	public static boolean validate(String password) {
		Pattern pattern = Pattern.compile(KANANAME_PATTERN);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
}
