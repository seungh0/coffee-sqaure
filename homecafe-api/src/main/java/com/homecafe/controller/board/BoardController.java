package com.homecafe.controller.board;

import com.homecafe.config.resolver.LoginMember;
import com.homecafe.controller.ApiResponse;
import com.homecafe.service.board.BoardService;
import com.homecafe.service.board.dto.request.CreateBoardRequest;
import com.homecafe.service.board.dto.response.BoardInfoResponse;
import com.homecafe.service.board.dto.response.BoardWithCommentInfoResponse;
import com.homecafe.service.board.dto.response.BoardWithCreatorInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

	private final BoardService boardService;

	@Operation(summary = "피드를 추가하는 API", description = "Bearer Token 필요합니다.")
	@PostMapping("/api/v1/board")
	public ApiResponse<BoardInfoResponse> createBoard(@Valid @RequestBody CreateBoardRequest request, @LoginMember Long memberId) {
		return ApiResponse.of(boardService.createBoard(request, memberId));
	}

	@Operation(summary = "메인 페이지 피드 리스트 조회 API", description = "lastBoardId= 가장 마지막에 보여지는 피드의 id, size=몇 개의 피드를 받아올 것인지")
	@GetMapping("/api/v1/board/list")
	public ApiResponse<List<BoardWithCreatorInfoResponse>> retrieveBoardList(@RequestParam long lastBoardId, @RequestParam int size) {
		return ApiResponse.of(boardService.retrieveBoardList(lastBoardId, size));
	}

	@Operation(summary = "메인 페이지 키워드 검색 피드 리스트 API", description = "lastBoardId= 가장 마지막에 보여지는 피드의 id, size=몇 개의 피드를 받아올 것인지")
	@GetMapping("/api/v1/board/list/search")
	public ApiResponse<List<BoardWithCreatorInfoResponse>> retrieveSearchBoardList(@RequestParam String keyword, @RequestParam long lastBoardId, @RequestParam int size) {
		return ApiResponse.of(boardService.retrieveSearchBoardList(keyword, lastBoardId, size));
	}

	@Operation(summary = "특정 피드의 상세 조회 API")
	@GetMapping("/api/v1/board")
	public ApiResponse<BoardWithCommentInfoResponse> retrieveBoard(@RequestParam Long boardId) {
		return ApiResponse.of(boardService.retrieveBoard(boardId));
	}

	@Operation(summary = "피드의 좋아요를 추가하는 API", description = "Bearer 토큰이 필요합니다")
	@PostMapping("/api/v1/board/like/{boardId}")
	public ApiResponse<String> addBoardLike(@PathVariable Long boardId, @LoginMember Long memberId) {
		boardService.addBoardLike(boardId, memberId);
		return ApiResponse.OK;
	}

	@Operation(summary = "피드의 좋아요를 취소하는 API", description = "Bearer 토큰이 필요합니다")
	@DeleteMapping("/api/v1/board/like/{boardId}")
	public ApiResponse<String> cancelBoardLike(@PathVariable Long boardId, @LoginMember Long memberId) {
		boardService.cancelBoardLike(boardId, memberId);
		return ApiResponse.OK;
	}

}
