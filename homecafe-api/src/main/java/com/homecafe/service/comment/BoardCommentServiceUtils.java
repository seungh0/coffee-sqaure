package com.homecafe.service.comment;

import com.homecafe.domain.comment.BoardComment;
import com.homecafe.domain.comment.BoardCommentRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCommentServiceUtils {

	public static BoardComment findCommentByIdAndMemberId(BoardCommentRepository boardCommentRepository, Long commentId, Long memberId) {
		BoardComment comment = boardCommentRepository.findCommentByIdAndMemberId(commentId, memberId);
		if (comment == null) {
			throw new IllegalArgumentException(String.format("멤버 (%s)에 해당하는 댓글 (%s)이 존재하지 않습니다", memberId, commentId));
		}
		return comment;
	}

	public static List<BoardComment> findAllBoardCommentsByCommentId(BoardCommentRepository boardCommentRepository, Long boardId) {
		return boardCommentRepository.findAllBoardCommentsByBoardId(boardId);
	}

}
