package com.homecafe.domain.comment.repository;

import com.homecafe.domain.comment.BoardComment;

import java.util.List;

public interface BoardCommentRepositoryCustom {

	BoardComment findCommentByIdAndMemberId(Long commentId, Long memberId);

	List<BoardComment> findAllBoardCommentsByBoardId(Long boardId);

}
