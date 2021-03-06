package com.homecafe.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

	public static final ApiResponse<String> OK = new ApiResponse<>(null, null, "OK");

	private final String code;
	private final String message;
	private final T data;

	public static <T> ApiResponse<T> of(T data) {
		return new ApiResponse<>(null, null, data);
	}

}
