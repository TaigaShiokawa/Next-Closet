package regexp;

public class PasswordStrengthChecker {
	
	public static int calculatePasswordStrength(String password) {
		int passwordStrengthLevel = 0;
		
		if(password.length() >= 8) {
			passwordStrengthLevel++;
		}
		
		if(password.matches(".*[a-z].*")) {
			passwordStrengthLevel++;
		}
		
		if(password.matches(".*[A-Z].*")) {
			passwordStrengthLevel++;
		}
		
		if(password.matches(".*[0-9].*")) {
			passwordStrengthLevel++;
		}
		
		if (password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
			passwordStrengthLevel++;
        }
		
		return passwordStrengthLevel;
	}

}
