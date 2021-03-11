package com.homecafe.controller.member;

import com.homecafe.config.resolver.LoginMember;
import com.homecafe.config.session.MemberSession;
import com.homecafe.controller.ApiResponse;
import com.homecafe.service.member.MemberService;
import com.homecafe.service.member.dto.request.SignUpMemberRequest;
import com.homecafe.service.member.dto.response.MemberInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.homecafe.config.session.SessionConstants.AUTH_SESSION;

@RequiredArgsConstructor
@RestController
public class MemberController {

	private final HttpSession httpSession;
	private final MemberService memberService;

	@PostMapping("/api/v1/member")
	public ApiResponse<String> signUpMember(@Valid @RequestBody SignUpMemberRequest request) {
		Long memberId = memberService.signUpMember(request);
		httpSession.setAttribute(AUTH_SESSION, MemberSession.of(memberId));
		return ApiResponse.of(httpSession.getId());
	}

	@GetMapping("/api/v1/member")
	public ApiResponse<MemberInfoResponse> getMyMemberInfo(@LoginMember Long memberId) {
		return ApiResponse.of(memberService.getMemberInfo(memberId));
	}

}
