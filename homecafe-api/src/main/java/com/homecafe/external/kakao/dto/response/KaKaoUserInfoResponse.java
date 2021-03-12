package com.homecafe.external.kakao.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class KaKaoUserInfoResponse {

	private String id;

	private KaKaoAccountResponse kakaoAccount;

	public String getEmail() {
		return this.kakaoAccount.getEmail();
	}

	public String getName() {
		if (this.kakaoAccount.getProfile() == null) {
			return null;
		}
		return this.kakaoAccount.getProfile().getNickname();
	}

	public String getProfileImage() {
		if (this.kakaoAccount.getProfile() == null) {
			return null;
		}
		return this.kakaoAccount.getProfile().getProfileImage();
	}

}