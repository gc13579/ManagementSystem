package com.gc_company.exception;

public class LoginFailException extends Exception {

	public LoginFailException() {
		super();
	}

	public LoginFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoginFailException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginFailException(String message) {
		super(message);
	}

	public LoginFailException(Throwable cause) {
		super(cause);
	}

}
