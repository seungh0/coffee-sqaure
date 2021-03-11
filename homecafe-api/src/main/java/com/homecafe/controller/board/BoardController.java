package com.homecafe.controller.board;

import com.homecafe.config.resolver.LoginMember;
import com.homecafe.controller.ApiResponse;
import com.homecafe.service.board.BoardService;
import com.homecafe.service.board.dto.request.CreateBoardRequest;
import com.homecafe.service.board.dto.response.BoardInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

	private final BoardService boardService;

	@PostMapping("/api/v1/board")
	public ApiResponse<BoardInfoResponse> createBoard(@Valid @RequestBody CreateBoardRequest request, @LoginMember Long memberId) {
		return ApiResponse.of(boardService.createBoard(request, memberId));
	}

	@GetMapping("/api/v1/board/list")
	public ApiResponse<List<BoardInfoResponse>> retrieveBoardList(@RequestParam long lastBoardId, @RequestParam int size) {
		return ApiResponse.of(boardService.retrieveBoardList(lastBoardId, size));
	}

	@GetMapping("/api/v1/board")
	public ApiResponse<BoardInfoResponse> retrieveBoard(@RequestParam Long boardId) {
		return ApiResponse.of(boardService.retrieveBoard(boardId));
	}

}
