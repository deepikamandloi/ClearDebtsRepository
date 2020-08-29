package com.cleardebts.frontend.output;

public class UserOutputData {

	private UserOutput user = new UserOutput();
	private String authToken = null;

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public UserOutput getUser() {
		return user;
	}

	public void setUser(UserOutput user) {
		this.user = user;
	}

	public String getAuthToken() {
		return authToken;
	}
	
	

}
