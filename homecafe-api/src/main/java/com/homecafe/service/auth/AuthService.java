package com.homecafe.service.auth;

import com.homecafe.domain.member.Member;
import com.homecafe.domain.member.MemberRepository;
import com.homecafe.external.kakao.KaKaoApiCaller;
import com.homecafe.external.kakao.dto.response.KaKaoUserInfoResponse;
import com.homecafe.service.auth.dto.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import static com.homecafe.config.session.SessionConstants.AUTH_SESSION;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

	private final KaKaoApiCaller kaKaoApiCaller;
	private final HttpSession httpSession;

	private final MemberRepository memberRepository;

	@Transactional(readOnly = true)
	public AuthResponse handleKaKaoAuthentication(String accessToken) {
		KaKaoUserInfoResponse userInfoResponse = kaKaoApiCaller.getKaKaoUserProfileInfo(accessToken);
		log.info("구글 유저 정보 요창 {}", userInfoResponse);
		Member findMember = memberRepository.findMemberByEmail(userInfoResponse.getEmail());
		if (findMember == null) {
			return AuthResponse.signUpWithKaKao(userInfoResponse.getEmail(), userInfoResponse.getName(), userInfoResponse.getProfileImage());
		}
		httpSession.setAttribute(AUTH_SESSION, findMember.getId());
		return AuthResponse.login(httpSession.getId());
	}

}