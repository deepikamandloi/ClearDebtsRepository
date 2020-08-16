package com.cleardebts.exception;

public class ClearDebtsException extends Exception {

	private Integer errorCode;

	public ClearDebtsException() {
	}

	public ClearDebtsException(String message) {
		super(message);
	}

	public ClearDebtsException(String message, Integer errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
