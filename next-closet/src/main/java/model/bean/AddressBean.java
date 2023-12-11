package model.bean;

public class AddressBean {
	
	private int addressId;
	private int userId;
	private String postCode;
	private String prefectures;
	private String address;
	
	public AddressBean() {}

	public AddressBean(int addressId, int userId, String postCode, String prefectures, String address) {
		super();
		this.addressId = addressId;
		this.userId = userId;
		this.postCode = postCode;
		this.prefectures = prefectures;
		this.address = address;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPrefectures() {
		return prefectures;
	}

	public void setPrefectures(String prefectures) {
		this.prefectures = prefectures;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
