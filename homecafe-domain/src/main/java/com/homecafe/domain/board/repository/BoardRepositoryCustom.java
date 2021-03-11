package com.homecafe.domain.board.repository;

import com.homecafe.domain.board.Board;

import java.util.List;

public interface BoardRepositoryCustom {

	List<Board> findBoardsOrderByIdDesc(int size);

	List<Board> findBoardsLessThanOrderByIdDescLimit(Long lastBoardId, int size);

}
