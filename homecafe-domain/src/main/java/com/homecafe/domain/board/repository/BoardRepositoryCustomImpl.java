package com.homecafe.domain.board.repository;

import com.homecafe.domain.board.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.homecafe.domain.board.QBoard.board;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Board> findBoardsOrderByIdDesc(int size) {
		return queryFactory.selectFrom(board)
				.orderBy(board.id.desc())
				.limit(size)
				.fetch();
	}

	@Override
	public List<Board> findBoardsLessThanOrderByIdDescLimit(Long lastBoardId, int size) {
		return queryFactory.selectFrom(board)
				.where(
						board.id.lt(lastBoardId)
				)
				.orderBy(board.id.desc())
				.limit(size)
				.fetch();
	}

}
