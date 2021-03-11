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

	@ToString
	@Getter
	@NoArgsConstructor
	private static class KaKaoAccountResponse {

		private String email;

		private KaKaoProfileResponse profile;

		@ToString
		@Getter
		@NoArgsConstructor
		public static class KaKaoProfileResponse {
			private String nickname;
		}

	}

	public String getEmail() {
		return this.kakaoAccount.getEmail();
	}

	public String getName() {
		return this.kakaoAccount.getProfile().getNickname();
	}

}