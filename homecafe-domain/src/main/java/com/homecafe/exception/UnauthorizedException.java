package com.homecafe.exception;

public class UnauthorizedException extends CustomException {

	public UnauthorizedException(String message, String description) {
		super(message, description);
	}

	public UnauthorizedException(String message) {
		super(message);
	}

}
