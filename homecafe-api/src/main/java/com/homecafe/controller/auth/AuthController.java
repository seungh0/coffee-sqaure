package com.homecafe.controller.auth;

import com.homecafe.controller.ApiResponse;
import com.homecafe.service.auth.AuthService;
import com.homecafe.service.auth.dto.request.AuthRequest;
import com.homecafe.service.auth.dto.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthController {

	private final AuthService authService;

	@GetMapping("/api/v1/auth/kakao")
	public ApiResponse<AuthResponse> handleKaKaoAuthentication(@Valid AuthRequest request) {
		return ApiResponse.of(authService.handleKaKaoAuthentication(request.getAccessToken()));
	}

}