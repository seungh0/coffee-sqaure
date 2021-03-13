package com.homecafe.service.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor
public class UpdateMemberInfoRequest {

	@NotBlank(message = "이름을 입력해주세요")
	private String name;

	private String profileUrl;

}
