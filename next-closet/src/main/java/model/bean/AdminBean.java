package model.bean;

import java.io.Serializable;

public class AdminBean implements Serializable{
	private String adminName;
	private String adminKanaName;
	private String email;
	private String password;
	
	public AdminBean() {}

	public AdminBean(String adminName, String adminKanaName, String email, String password) {
		super();
		this.adminName = adminName;
		this.adminKanaName = adminKanaName;
		this.email = email;
		this.password = password;
	}

	public AdminBean(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminKanaName() {
		return adminKanaName;
	}

	public void setAdminKanaName(String adminKanaName) {
		this.adminKanaName = adminKanaName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
