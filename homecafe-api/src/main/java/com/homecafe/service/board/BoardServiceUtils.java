package com.homecafe.service.board;

import com.homecafe.domain.board.Board;
import com.homecafe.domain.board.BoardRepository;
import com.homecafe.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardServiceUtils {

	public static Board findBoardById(BoardRepository boardRepository, Long boardId) {
		Board board = boardRepository.findBoardById(boardId);
		if (board == null) {
			throw new NotFoundException(String.format("해당하는 board (%s)가 존재하지 않습니다", boardId), "해당하는 피드가 존재하지 않습니다");
		}
		return board;
	}

	static Board findBoardByIdAndMemberId(BoardRepository boardRepository, Long boardId, Long memberId) {
		Board board = boardRepository.findBoardByIdAndMemberId(boardId, memberId);
		if (board == null) {
			throw new NotFoundException(String.format("해당하는 board (%s)가 존재하지 않습니다", boardId), "해당하는 피드가 존재하지 않습니다");
		}
		return board;
	}

}
