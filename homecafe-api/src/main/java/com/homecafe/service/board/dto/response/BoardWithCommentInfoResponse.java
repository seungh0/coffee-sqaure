package com.homecafe.service.board.dto.response;

import com.homecafe.domain.board.Board;
import com.homecafe.domain.comment.BoardComment;
import com.homecafe.domain.comment.BoardCommentMemberCollection;
import com.homecafe.domain.member.Member;
import com.homecafe.service.comment.dto.response.BoardCommentInfoResponse;
import com.homecafe.service.member.dto.response.MemberInfoResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardWithCommentInfoResponse {

	private final BoardInfoResponse board;

	private final MemberInfoResponse creator;

	private final List<BoardCommentInfoResponse> comments = new ArrayList<>();

	public static BoardWithCommentInfoResponse of(Board board, List<BoardComment> boardCommentList, Member creator, BoardCommentMemberCollection collection, Long memberId) {
		List<BoardCommentInfoResponse> commentInfoResponses = boardCommentList.stream()
				.map(boardComment -> BoardCommentInfoResponse.of(boardComment, collection.getMember(boardComment.getMemberId())))
				.collect(Collectors.toList());
		BoardWithCommentInfoResponse response = new BoardWithCommentInfoResponse(BoardInfoResponse.of(board, memberId), MemberInfoResponse.of(creator));
		response.comments.addAll(commentInfoResponses);
		return response;
	}

}
