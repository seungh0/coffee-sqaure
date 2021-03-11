package com.homecafe.domain.comment.repository;

import com.homecafe.domain.comment.BoardComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.homecafe.domain.comment.QBoardComment.boardComment;

@RequiredArgsConstructor
public class BoardCommentRepositoryCustomImpl implements BoardCommentRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public BoardComment findCommentByIdAndMemberId(Long commentId, Long memberId) {
		return queryFactory.selectFrom(boardComment)
				.where(
						boardComment.id.eq(commentId),
						boardComment.isDeleted.isFalse()
				).fetchOne();
	}

	@Override
	public List<BoardComment> findAllBoardCommentsByBoardId(Long boardId) {
		return queryFactory.selectFrom(boardComment)
				.where(
						boardComment.boardId.eq(boardId),
						boardComment.isDeleted.isFalse()
				).fetch();
	}

}
