package hashedPassword;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPW {
	public static String hashPass(String pass) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256"); //MessageDigestインスタンスの取得.
		md.update(pass.getBytes()); //MessageDigestオブジェクトに入ってきたpwのバイト表現を追加.
		byte[] digest = md.digest(); //計算されたハッシュ値をバイト配列に格納.
		String hex = String.format("%064x", new BigInteger(1, digest)); //BigIntegerオブジェクトでバイト配列を正の数値と解釈.引数は(符号, 計算されたバイト配列).
		//1は正、-1は負, 0は符号なし.ここでは,16進数表現に一貫性を持たせるために1を使用.
		return hex;
	}

}
