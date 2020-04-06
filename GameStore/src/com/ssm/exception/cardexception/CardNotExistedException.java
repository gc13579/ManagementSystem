package com.ssm.exception.cardexception;

public class CardNotExistedException extends Exception {

	public CardNotExistedException() {
		super();
	}

	public CardNotExistedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CardNotExistedException(String arg0) {
		super(arg0);
	}

	public CardNotExistedException(Throwable arg0) {
		super(arg0);
	}

}
