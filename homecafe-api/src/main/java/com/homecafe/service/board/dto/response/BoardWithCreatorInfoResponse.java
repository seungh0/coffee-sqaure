package com.homecafe.service.board.dto.response;

import com.homecafe.domain.board.Board;
import com.homecafe.domain.member.Member;
import com.homecafe.service.member.dto.response.MemberInfoResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import static lombok.AccessLevel.PRIVATE;

@ToString
@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class BoardWithCreatorInfoResponse {

	private final BoardInfoResponse board;

	private final MemberInfoResponse creator;

	public static BoardWithCreatorInfoResponse of(Board board, Member member, Long memberId) {
		return new BoardWithCreatorInfoResponse(BoardInfoResponse.of(board, memberId), MemberInfoResponse.of(member));
	}

}