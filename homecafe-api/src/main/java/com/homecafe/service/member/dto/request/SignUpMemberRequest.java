package com.homecafe.service.member.dto.request;

import com.homecafe.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor
public class SignUpMemberRequest {

	@NotBlank(message = "이메일을 입력해주세요")
	private String email;

	@NotBlank(message = "이름을 입력해주세요")
	private String name;

	private String profileUrl;

	public Member toEntity() {
		return Member.newKaKaoMember(email, name, profileUrl);
	}

}
