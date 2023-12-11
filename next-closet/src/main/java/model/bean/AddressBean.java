package model.bean;

public class AddressBean {
	
	private int address_id;
	private int user_id;
	private String postCode;
	private String prefectures;
	private String address;
	
	public AddressBean() {}

	public AddressBean(int address_id, int user_id, String postCode, String prefectures, String address) {
		super();
		this.address_id = address_id;
		this.user_id = user_id;
		this.postCode = postCode;
		this.prefectures = prefectures;
		this.address = address;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
