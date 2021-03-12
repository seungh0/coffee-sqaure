package com.homecafe.service.comment.dto.response;

import com.homecafe.domain.comment.BoardComment;
import com.homecafe.domain.member.Member;
import com.homecafe.service.member.dto.response.MemberInfoResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardCommentInfoResponse {

	private final Long commentId;

	private final Long boardId;

	private final String content;

	private final MemberInfoResponse writer;

	public static BoardCommentInfoResponse of(BoardComment boardComment, Member member) {
		return new BoardCommentInfoResponse(boardComment.getId(), boardComment.getBoardId(), boardComment.getContent(), MemberInfoResponse.of(member));
	}

}
