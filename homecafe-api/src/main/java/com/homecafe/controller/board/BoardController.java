package com.homecafe.controller.board;

import com.homecafe.config.resolver.LoginMember;
import com.homecafe.controller.ApiResponse;
import com.homecafe.service.board.BoardService;
import com.homecafe.service.board.dto.request.CreateBoardRequest;
import com.homecafe.service.board.dto.response.BoardInfoResponse;
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
	public ApiResponse<List<BoardInfoResponse>> retrieveBoardList(@RequestParam long lastBoardId, @RequestParam int size) {
		return ApiResponse.of(boardService.retrieveBoardList(lastBoardId, size));
	}

	@Operation(summary = "특정 피드의 상세 조회 API")
	@GetMapping("/api/v1/board")
	public ApiResponse<BoardInfoResponse> retrieveBoard(@RequestParam Long boardId) {
		return ApiResponse.of(boardService.retrieveBoard(boardId));
	}

}
