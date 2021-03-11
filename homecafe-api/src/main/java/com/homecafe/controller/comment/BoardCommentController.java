package com.homecafe.controller.comment;

import com.homecafe.config.resolver.LoginMember;
import com.homecafe.controller.ApiResponse;
import com.homecafe.service.comment.BoardCommentService;
import com.homecafe.service.comment.dto.request.AddCommentRequest;
import com.homecafe.service.comment.dto.response.BoardCommentInfoResponse;
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
	public ApiResponse<BoardCommentInfoResponse> addBoardComment(@Valid @RequestBody AddCommentRequest request, @LoginMember Long memberId) {
		return ApiResponse.of(boardCommentService.addComment(request, memberId));
	}

	@Operation(summary = "피드에 댓글을 삭제하는 API", description = "Bearer Token 필요")
	@DeleteMapping("/api/v1/board/comment")
	public ApiResponse<String> deleteBoardComment(@RequestParam Long commentId, @LoginMember Long memberId) {
		boardCommentService.deleteComment(commentId, memberId);
		return ApiResponse.OK;
	}

}
