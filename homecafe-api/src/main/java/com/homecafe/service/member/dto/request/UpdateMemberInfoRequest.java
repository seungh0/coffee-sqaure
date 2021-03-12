package com.homecafe.service.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class UpdateMemberInfoRequest {

	private String name;

	private String profileUrl;

}
