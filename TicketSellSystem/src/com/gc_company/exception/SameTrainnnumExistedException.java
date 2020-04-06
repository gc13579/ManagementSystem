package com.gc_company.exception;

public class SameTrainnnumExistedException extends Exception {

	public SameTrainnnumExistedException() {
		super();
	}

	public SameTrainnnumExistedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SameTrainnnumExistedException(String message, Throwable cause) {
		super(message, cause);
	}

	public SameTrainnnumExistedException(String message) {
		super(message);
	}

	public SameTrainnnumExistedException(Throwable cause) {
		super(cause);
	}

}
