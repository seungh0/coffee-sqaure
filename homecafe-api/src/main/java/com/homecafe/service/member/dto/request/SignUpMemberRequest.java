package com.homecafe.service.member.dto.request;

import com.homecafe.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpMemberRequest {

	private String email;

	private String name;

	private String profileUrl;

	public Member toEntity() {
		return Member.newKaKaoMember(email, name, profileUrl);
	}

}
