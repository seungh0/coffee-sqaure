package com.homecafe.service.member.dto.request;

import com.homecafe.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class SignUpMemberRequest {

	@NotBlank
	private String email;

	@NotBlank
	private String name;

	private String profileUrl;

	public Member toEntity() {
		return Member.newKaKaoMember(email, name, profileUrl);
	}

}
