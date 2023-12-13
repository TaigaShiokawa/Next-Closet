package regexp;

public class HalfWidthValidator {
	
	public static final String HALF_WIDTH_PATTERN = "^[\\x20-\\x7E]*$"; 
	//ASCIIコードで全ての半角文字と英数字を許可(全角は不可)
	
	public static boolean isHalfWidth(String password) {
		return password.matches(HALF_WIDTH_PATTERN);
	}

}
