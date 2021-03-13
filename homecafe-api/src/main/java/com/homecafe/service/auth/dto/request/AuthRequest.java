package com.homecafe.service.auth.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor
public class AuthRequest {

	@NotBlank(message = "토큰을 입력해주세요")
	private String accessToken;

}