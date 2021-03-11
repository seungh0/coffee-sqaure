package com.homecafe.service.auth.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthResponse {

	private final AuthType type;

	private final String email;

	private final String name;

	private final String token;

	public static AuthResponse login(String token) {
		return new AuthResponse(AuthType.LOGIN, null, null, token);
	}

	public static AuthResponse signUpWithKaKao(String email, String name) {
		return new AuthResponse(AuthType.SIGN_UP, email, name, null);
	}

	public enum AuthType {
		SIGN_UP,
		LOGIN
	}

}
