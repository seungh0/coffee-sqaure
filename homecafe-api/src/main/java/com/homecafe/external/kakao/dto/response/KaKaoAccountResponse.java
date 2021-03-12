package com.homecafe.external.kakao.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
class KaKaoAccountResponse {

	private String email;

	private String gender;

	private String birthday;

	private KaKaoProfileResponse profile;

}