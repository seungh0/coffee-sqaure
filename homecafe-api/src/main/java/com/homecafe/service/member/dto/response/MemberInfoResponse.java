package com.homecafe.service.member.dto.response;

import com.homecafe.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberInfoResponse {

	private final Long id;

	private final String email;

	private final String name;

	private final String profileUrl;

	public static MemberInfoResponse of(Member member) {
		return new MemberInfoResponse(member.getId(), member.getEmail(), member.getName(), member.getProfileUrl());
	}

}