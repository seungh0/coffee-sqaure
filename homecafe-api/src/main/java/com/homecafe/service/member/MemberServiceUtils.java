package com.homecafe.service.member;

import com.homecafe.domain.member.Member;
import com.homecafe.domain.member.MemberRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberServiceUtils {

	static void validateNonExistsMember(MemberRepository memberRepository, String email) {
		Member member = memberRepository.findMemberByEmail(email);
		if (member != null) {
			throw new IllegalArgumentException(String.format("이미 존재하는 회원 (%s) 입니다.", email));
		}
	}

	public static Member findMemberById(MemberRepository memberRepository, Long memberId) {
		Member member = memberRepository.findMemberById(memberId);
		if (member == null) {
			throw new IllegalArgumentException(String.format("해당 하는 회원 (%s) 은 없습니다", memberId));
		}
		return member;
	}

}
