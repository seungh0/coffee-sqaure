package com.homecafe.domain.board.repository;

import com.homecafe.domain.board.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.homecafe.domain.board.QBoard.board;
import static com.homecafe.domain.board.QBoardPicture.boardPicture;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Board> findBoardsOrderByIdDesc(int size) {
		return queryFactory.selectFrom(board)
				.innerJoin(board.pictureList, boardPicture).fetchJoin()
				.orderBy(board.id.desc())
				.limit(size)
				.fetch();
	}

	@Override
	public List<Board> findBoardsLessThanOrderByIdDescLimit(Long lastBoardId, int size) {
		return queryFactory.selectFrom(board)
				.innerJoin(board.pictureList, boardPicture).fetchJoin()
				.where(
						board.id.lt(lastBoardId)
				)
				.orderBy(board.id.desc())
				.limit(size)
				.fetch();
	}

	@Override
	public Board findBoardById(Long boardId) {
		return queryFactory.selectFrom(board)
				.innerJoin(board.pictureList, boardPicture).fetchJoin()
				.where(
						board.id.eq(boardId)
				).fetchOne();
	}

	@Override
	public List<Board> findBoardsWithKeywordOrderByIdDesc(String keyword, int size) {
		return queryFactory.selectFrom(board)
				.innerJoin(board.pictureList, boardPicture).fetchJoin()
				.where(
						board.title.contains(keyword)
				)
				.orderBy(board.id.desc())
				.limit(size)
				.fetch();
	}

	@Override
	public List<Board> findBoardsWithKeywordLessThanOrderByIdDescLimit(String keyword, Long lastBoardId, int size) {
		return queryFactory.selectFrom(board)
				.innerJoin(board.pictureList, boardPicture).fetchJoin()
				.where(
						board.id.lt(lastBoardId),
						board.title.contains(keyword)
				)
				.orderBy(board.id.desc())
				.limit(size)
				.fetch();
	}

	@Override
	public List<Board> findBoardByMemberId(Long memberId) {
		return queryFactory.selectFrom(board)
				.innerJoin(board.pictureList, boardPicture).fetchJoin()
				.where(
						board.memberId.eq(memberId)
				).fetch();
	}

}
