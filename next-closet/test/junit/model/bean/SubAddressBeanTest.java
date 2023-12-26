package junit.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.bean.SubAddressBean;

public class SubAddressBeanTest {

	@Test
	public void testSubAddressBean() {
		
		SubAddressBean subAddress = new SubAddressBean();
		
		// テストデータ
		int addAddressId = 1;
		
		// 値をセット
		subAddress.setAddAddressId(addAddressId);
		
		// 値の取得
		assertEquals(addAddressId, subAddress.getAddAddressId());
	}

}
