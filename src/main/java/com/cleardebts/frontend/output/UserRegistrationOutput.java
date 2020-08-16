package com.cleardebts.frontend.output;

public class UserRegistrationOutput {

	private UserRegistrationOutputData data = new UserRegistrationOutputData();

	Boolean success;
	String message;

	public UserRegistrationOutputData getData() {
		return data;
	}

	public void setData(UserRegistrationOutputData data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
