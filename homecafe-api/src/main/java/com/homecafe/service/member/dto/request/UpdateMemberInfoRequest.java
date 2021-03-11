package com.homecafe.service.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateMemberInfoRequest {

	private String name;

	private String profileUrl;

}
