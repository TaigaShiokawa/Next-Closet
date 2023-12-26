package junit.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.bean.AddressBean;

class AddressBeanTest {

	@Test
	void testAddressBean() {
		
		AddressBean addressBean = new AddressBean();
		
		// テストデータ
		int addressId = 1;
		int userId = 1;
		String postCode = "1234567";
		String prefectures = "北海道";
		String address = "札幌市1-1";
		
		// 値をセット
		addressBean.setAddAddressId(addressId);
		addressBean.setUserId(userId);
		addressBean.setPostCode(postCode);
		addressBean.setPrefectures(prefectures);
		addressBean.setAddress(address);
		
		assertEquals(addressId, addressBean.getAddAddressId());
		assertEquals(userId, addressBean.getUserId());
		assertEquals(postCode, addressBean.getPostCode());
		assertEquals(prefectures, addressBean.getPrefectures());
		assertEquals(address, addressBean.getAddress());
	}

}
