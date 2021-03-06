package com.homecafe.config.resolver;

import com.homecafe.config.session.MemberSession;
import com.homecafe.config.session.SessionConstants;
import com.homecafe.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class LoginMemberResolver implements HandlerMethodArgumentResolver {

	private final static String BEARER_TOKEN = "Bearer ";

	private final SessionRepository<? extends Session> sessionRepository;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean hasAnnotation = parameter.getParameterAnnotation(LoginMember.class) != null;
		boolean isMatchType = parameter.getParameterType().equals(Long.class);
		return hasAnnotation && isMatchType;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		String header = webRequest.getHeader(HttpHeaders.AUTHORIZATION);
		Session session = extractSessionFromHeader(header);
		MemberSession memberSession = session.getAttribute(SessionConstants.AUTH_SESSION);
		return memberSession.getMemberId();
	}

	private Session extractSessionFromHeader(String header) {
		if (header == null) {
			throw new UnauthorizedException("세션이 없습니다");
		}
		if (!header.startsWith(BEARER_TOKEN)) {
			throw new UnauthorizedException(String.format("잘못된 세션입니다 (%s)", header));
		}
		Session session = sessionRepository.getSession(header.split(BEARER_TOKEN)[1]);
		if (session == null) {
			throw new UnauthorizedException(String.format("잘못된 세션입니다 (%s)", header));
		}
		return session;
	}

}