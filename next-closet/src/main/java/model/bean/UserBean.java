package model.bean;

public class UserBean {
	
	private int userId;
	private String userName;
	private String kanaName;
	private String email;
	private String hashPass;
	private String registerDate;
	private String telNumber;
	private boolean userStatus;
	
	public UserBean() {}

	public UserBean(int userId, String userName, String kanaName, String email, String hashPass, String registerDate,
			String telNumber, boolean userStatus) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.kanaName = kanaName;
		this.email = email;
		this.hashPass = hashPass;
		this.registerDate = registerDate;
		this.telNumber = telNumber;
		this.userStatus = userStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getKanaName() {
		return kanaName;
	}

	public void setKanaName(String kanaName) {
		this.kanaName = kanaName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashPass() {
		return hashPass;
	}

	public void setHashPass(String hashPass) {
		this.hashPass = hashPass;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public boolean isUserStatus() {
		return userStatus;
	}

	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}

}
