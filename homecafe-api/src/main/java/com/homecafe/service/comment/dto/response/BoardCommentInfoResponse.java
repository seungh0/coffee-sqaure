package com.homecafe.service.comment.dto.response;

import com.homecafe.domain.comment.BoardComment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardCommentInfoResponse {

	private final Long commentId;

	private final Long boardId;

	private final Long memberId;

	private final String content;

	public static BoardCommentInfoResponse of(BoardComment boardComment) {
		return new BoardCommentInfoResponse(boardComment.getId(), boardComment.getBoardId(), boardComment.getMemberId(), boardComment.getContent());
	}

}
