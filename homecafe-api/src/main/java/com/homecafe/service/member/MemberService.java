package com.homecafe.service.member;

import com.homecafe.domain.member.Member;
import com.homecafe.domain.member.MemberRepository;
import com.homecafe.service.member.dto.request.SignUpMemberRequest;
import com.homecafe.service.member.dto.request.UpdateMemberInfoRequest;
import com.homecafe.service.member.dto.response.MemberInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	@Transactional
	public Long signUpMember(SignUpMemberRequest request) {
		MemberServiceUtils.validateNonExistsMember(memberRepository, request.getEmail());
		return memberRepository.save(request.toEntity()).getId();
	}

	@Transactional(readOnly = true)
	public MemberInfoResponse getMemberInfo(Long memberId) {
		Member member = MemberServiceUtils.findMemberById(memberRepository, memberId);
		return MemberInfoResponse.of(member);
	}

	public MemberInfoResponse updateMemberInfo(UpdateMemberInfoRequest request, Long memberId) {
		Member member = MemberServiceUtils.findMemberById(memberRepository, memberId);
		member.updateMemberInfo(request.getName(), request.getProfileUrl());
		return MemberInfoResponse.of(member);
	}

}
