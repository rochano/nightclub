package com.nightclub.exception;

public class CustomException extends Exception {
	private static final long serialVersionUID = 1L;
	private String code;

	public CustomException(String code) {
		super();
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
