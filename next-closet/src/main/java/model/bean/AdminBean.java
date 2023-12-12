package model.bean;

import java.io.Serializable;

public class AdminBean implements Serializable{
	private String admin_name;
	private String email;
	private String password;
	
	public AdminBean() {}

	public AdminBean(String admin_name, String email, String password) {
		this.admin_name = admin_name;
		this.email = email;
		this.password = password;
	}

	public AdminBean(String email, String password) {
		this.email = email;
		this.password = password;
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
   

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

}
