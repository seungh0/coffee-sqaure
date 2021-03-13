package com.homecafe.controller.member;

import com.homecafe.config.resolver.LoginMember;
import com.homecafe.config.session.MemberSession;
import com.homecafe.controller.ApiResponse;
import com.homecafe.service.member.MemberService;
import com.homecafe.service.member.dto.request.SignUpMemberRequest;
import com.homecafe.service.member.dto.request.UpdateMemberInfoRequest;
import com.homecafe.service.member.dto.response.MemberInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.homecafe.config.session.SessionConstants.AUTH_SESSION;

@RequiredArgsConstructor
@RestController
public class MemberController {

	private final HttpSession httpSession;
	private final MemberService memberService;

	@Operation(summary = "회원가입을 요청하는 API", description = "로그인을 위한 토큰이 반환됩니다")
	@PostMapping("/api/v1/member")
	public ApiResponse<String> signUpMember(@Valid @RequestBody SignUpMemberRequest request) {
		Long memberId = memberService.signUpMember(request);
		httpSession.setAttribute(AUTH_SESSION, MemberSession.of(memberId));
		return ApiResponse.of(httpSession.getId());
	}

	@Operation(summary = "내 정보를 불러오는 API", description = "Bearer 토큰이 필요합니다")
	@GetMapping("/api/v1/member")
	public ApiResponse<MemberInfoResponse> getMyMemberInfo(@LoginMember Long memberId) {
		return ApiResponse.of(memberService.getMemberInfo(memberId));
	}

	@Operation(summary = "내 정보를 수정하는 API", description = "Bearer 토큰이 필요합니다")
	@PutMapping("/api/v1/member")
	public ApiResponse<MemberInfoResponse> updateMemberInfo(@Valid @RequestBody UpdateMemberInfoRequest request, @LoginMember Long memberId) {
		return ApiResponse.of(memberService.updateMemberInfo(request, memberId));
	}

}
