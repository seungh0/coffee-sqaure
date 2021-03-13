package com.homecafe.domain.board.repository;

import com.homecafe.domain.board.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.homecafe.domain.board.QBoard.board;
import static com.homecafe.domain.board.QBoardPicture.boardPicture;
import static com.homecafe.domain.board.QBoardLike.boardLike;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	// TODO 페치 조인 + 페이지네이션시, 메모리에서 limit를 해주는 문제점이 있어서 페치 조인을 하지 않고, batch_size를 제한했는데 괜찮은 방법일까?
	@Override
	public List<Board> findBoardsOrderByIdDesc(int size) {
		return queryFactory.selectFrom(board).distinct()
				.leftJoin(board.pictureList, boardPicture)
				.where(
						board.isDeleted.isFalse()
				)
				.orderBy(board.id.desc())
				.limit(size)
				.fetch();
	}

	@Override
	public List<Board> findBoardsLessThanOrderByIdDescLimit(Long lastBoardId, int size) {
		return queryFactory.selectFrom(board).distinct()
				.leftJoin(board.pictureList, boardPicture)
				.where(
						board.id.lt(lastBoardId),
						board.isDeleted.isFalse()
				)
				.orderBy(board.id.desc())
				.limit(size)
				.fetch();
	}

	@Override
	public Board findBoardById(Long boardId) {
		return queryFactory.selectFrom(board)
				.leftJoin(board.pictureList, boardPicture).fetchJoin()
				.where(
						board.id.eq(boardId),
						board.isDeleted.isFalse()
				).fetchOne();
	}

	@Override
	public List<Board> findBoardsWithKeywordOrderByIdDesc(String keyword, int size) {
		return queryFactory.selectFrom(board).distinct()
				.leftJoin(board.pictureList, boardPicture)
				.where(
						board.description.contains(keyword),
						board.isDeleted.isFalse()
				)
				.orderBy(board.id.desc())
				.limit(size)
				.fetch();
	}

	@Override
	public List<Board> findBoardsWithKeywordLessThanOrderByIdDescLimit(String keyword, Long lastBoardId, int size) {
		return queryFactory.selectFrom(board).distinct()
				.leftJoin(board.pictureList, boardPicture)
				.where(
						board.id.lt(lastBoardId),
						board.description.contains(keyword),
						board.isDeleted.isFalse()
				)
				.orderBy(board.id.desc())
				.limit(size)
				.fetch();
	}

	@Override
	public List<Board> findBoardByMemberId(Long memberId) {
		return queryFactory.selectFrom(board).distinct()
				.leftJoin(board.pictureList, boardPicture).fetchJoin()
				.where(
						board.memberId.eq(memberId),
						board.isDeleted.isFalse()
				)
				.orderBy(board.id.desc())
				.fetch();
	}

	@Override
	public Board findBoardByIdAndMemberId(Long boardId, Long memberId) {
		return queryFactory.selectFrom(board)
				.leftJoin(board.pictureList, boardPicture).fetchJoin()
				.where(
						board.id.eq(boardId),
						board.memberId.eq(memberId),
						board.isDeleted.isFalse()
				).fetchOne();
	}

	/**
	 * boardPicture도 페치조인 걸고 싶지만 3개 이상 못걸어서 batch_size만 설정해둠
	 */
	@Override
	public List<Board> findLikeBoardByMemberId(Long memberId) {
		return queryFactory.selectFrom(board)
				.innerJoin(board.boardLikeList, boardLike).fetchJoin()
				.where(
						board.memberId.eq(memberId),
						boardLike.memberId.eq(memberId),
						board.isDeleted.isFalse()
				)
				.orderBy(board.id.desc())
				.fetch();
	}

}
