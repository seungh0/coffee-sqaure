package com.homecafe.domain.member.repository;

import com.homecafe.domain.member.Member;
import com.homecafe.domain.member.MemberProvider;

public interface MemberRepositoryCustom {

	Member findMemberByEmailAndProvider(String email, MemberProvider provider);

}
