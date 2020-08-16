package com.cleardebts.frontend.output;

public class UserRegistrationOutput extends BaseOutput {

	private UserRegistrationOutputData data = new UserRegistrationOutputData();

	public UserRegistrationOutputData getData() {
		return data;
	}

	public void setData(UserRegistrationOutputData data) {
		this.data = data;
	}

}
