package com.gc_company.exception;

public class InsufficientMoneyException extends Exception {

	public InsufficientMoneyException() {
		super();
	}

	public InsufficientMoneyException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InsufficientMoneyException(String message, Throwable cause) {
		super(message, cause);
	}

	public InsufficientMoneyException(String message) {
		super(message);
	}

	public InsufficientMoneyException(Throwable cause) {
		super(cause);
	}

}
