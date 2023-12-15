package regexp;

public class AddressValidator {
	//住所に含まれる数字を全角から半角に置換
	public static final String convertFullWidthNumbersToHalfwidth(String input) {
		StringBuilder converted = new StringBuilder();
		for(char c : input.toCharArray()) {
			if(c >= '０' && c <= '９') {
				converted.append((char) (c - '０' + '0'));
			} else {
				converted.append(c);
			}
		}
		return converted.toString();
	}
	
	// 不要なスペースを削除するメソッド
    public static String removeUnnecessarySpaces(String input) {
        return input.replaceAll("\\s+", " ").trim();
    }

    // 住所の正規化を行うメソッド
    public static String normalizeAddress(String address) {
        // 全角数字を半角に変換
        String convertedNumbers = convertFullWidthNumbersToHalfwidth(address);
        // 不要なスペースを削除
        return removeUnnecessarySpaces(convertedNumbers);
    }
	// テスト用のmainメソッド
//    public static void main(String[] args) {
//        String address = "　東京都新宿区西新宿２－８－１　";
//        String normalizedAddress = normalizeAddress(address);
//        System.out.println("正規化された住所: " + normalizedAddress);
//    }
}