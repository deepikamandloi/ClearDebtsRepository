package com.cleardebts.frontend.output;

import java.io.Serializable;

public class UserLoginOutput extends BaseOutput implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	private UserOutputData data;

	public UserLoginOutput(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public UserOutputData getData() {
		return data;
	}

	public void setData(UserOutputData data) {
		this.data = data;
	}

}