package com.homecafe.service.member;

import com.homecafe.domain.member.Member;
import com.homecafe.domain.member.MemberRepository;
import com.homecafe.exception.ConflictException;
import com.homecafe.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberServiceUtils {

	static void validateNonExistsMember(MemberRepository memberRepository, String email) {
		Member member = memberRepository.findMemberByEmail(email);
		if (member != null) {
			throw new ConflictException(String.format("이미 존재하는 회원 (%s) 입니다.", email), "이미 존재하는 유저입니다.");
		}
	}

	public static Member findMemberById(MemberRepository memberRepository, Long memberId) {
		Member member = memberRepository.findMemberById(memberId);
		if (member == null) {
			throw new NotFoundException(String.format("해당 하는 회원 (%s) 은 없습니다", memberId), "존재하지 않는 유저입니다.");
		}
		return member;
	}

}
