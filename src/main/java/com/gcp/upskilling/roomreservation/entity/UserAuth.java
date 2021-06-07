package com.gcp.upskilling.roomreservation.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class UserAuth {

	
	private String username;
	
	private String authority;

	private String password;
	
	private int enabled;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public UserAuth(String username, String authority, String password, int enabled) {
		super();
		this.username = username;
		this.authority = authority;
		this.password = password;
		this.enabled = enabled;
	}

	public UserAuth() {
		super();
	}
	
	
	
}
