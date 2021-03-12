package com.homecafe.domain.board.repository;

import com.homecafe.domain.board.Board;

import java.util.List;

public interface BoardRepositoryCustom {

	List<Board> findBoardsOrderByIdDesc(int size);

	List<Board> findBoardsLessThanOrderByIdDescLimit(Long lastBoardId, int size);

	Board findBoardById(Long boardId);

	List<Board> findBoardsWithKeywordOrderByIdDesc(String keyword, int size);

	List<Board> findBoardsWithKeywordLessThanOrderByIdDescLimit(String keyword, Long lastBoardId, int size);

	List<Board> findBoardByMemberId(Long memberId);

	Board findBoardByIdAndMemberId(Long boardId, Long memberId);

}
