package com.homecafe.domain.member.repository;

import com.homecafe.domain.member.Member;

public interface MemberRepositoryCustom {

	Member findMemberByEmail(String email);

	Member findMemberById(Long id);

	Member findMemberById(Long id);

	Member findMemberByEmail(String email);

}
