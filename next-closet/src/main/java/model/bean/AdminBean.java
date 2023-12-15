package model.bean;

import java.io.Serializable;
import java.sql.Date;

public class AdminBean implements Serializable{
	private int adminId;
	private String adminName;
	private String adminKanaName;
	private String email;
	private String password;
	private boolean adminStatus;
	private Date registrationDate;
	
	public AdminBean() {}

	public AdminBean(int adminId ,String adminName, String adminKanaName, String email, String password ,  boolean adminStatus , Date registrationDate) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminKanaName = adminKanaName;
		this.email = email;
		this.password = password;
		this.adminStatus = adminStatus;
		this.registrationDate = registrationDate ;
	}

	public AdminBean(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public boolean isAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(boolean adminStatus) {
		this.adminStatus = adminStatus;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
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
	
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	

}
