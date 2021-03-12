package com.homecafe.service.auth.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor
public class AuthRequest {

	@NotBlank
	private String accessToken;

}