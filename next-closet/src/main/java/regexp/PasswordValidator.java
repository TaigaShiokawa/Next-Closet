package regexp;

public class PasswordValidator {
	
	public static final String HALF_WIDTH_PATTERN = "^[\\x20-\\x7E]*$"; 
	//ASCIIコードで全ての半角文字と英数字を許可(全角は不可)
	
	public static boolean isHalfWidth(String password) {
		return password.matches(HALF_WIDTH_PATTERN);
	}
	
	//テスト用
	public static void main(String[] args) {
		String input = "Test@123!#"; // この文字列は半角英数字と特殊文字
		boolean isHalfWidth = PasswordValidator.isHalfWidth(input);
		System.out.println("入力は半角英数字および特殊文字のみですか？: " + isHalfWidth);
	}

}
