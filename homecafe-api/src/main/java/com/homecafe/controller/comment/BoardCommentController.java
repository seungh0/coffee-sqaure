package com.homecafe.controller.comment;

import com.homecafe.config.resolver.LoginMember;
import com.homecafe.controller.ApiResponse;
import com.homecafe.service.comment.BoardCommentService;
import com.homecafe.service.comment.dto.request.AddCommentRequest;
import com.homecafe.service.comment.dto.request.DeleteCommentRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class BoardCommentController {

	private final BoardCommentService boardCommentService;

	@Operation(summary = "피드에 댓글을 추가하는 API", description = "Bearer Token 필요")
	@PostMapping("/api/v1/board/comment")
	public ApiResponse<String> addBoardComment(@Valid @RequestBody AddCommentRequest request, @LoginMember Long memberId) {
		boardCommentService.addComment(request, memberId);
		return ApiResponse.OK;
	}

	@Operation(summary = "피드에 댓글을 삭제하는 API", description = "Bearer Token 필요")
	@DeleteMapping("/api/v1/board/comment")
	public ApiResponse<String> deleteBoardComment(@Valid DeleteCommentRequest request, @LoginMember Long memberId) {
		boardCommentService.deleteComment(request, memberId);
		return ApiResponse.OK;
	}

}
