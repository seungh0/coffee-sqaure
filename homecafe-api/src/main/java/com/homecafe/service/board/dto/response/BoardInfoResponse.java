package com.homecafe.service.board.dto.response;

import com.homecafe.domain.board.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardInfoResponse {

	private final Long id;

	private final String description;

	private final int likesCount;

	private final int commentsCount;

	private final boolean isLike;

	private final List<String> pictureUrls = new ArrayList<>();

	public static BoardInfoResponse of(Board board, Long memberId) {
		BoardInfoResponse response = new BoardInfoResponse(board.getId(), board.getDescription(), board.getLikesCount(), board.getCommentsCount(), board.isLike(memberId));
		response.pictureUrls.addAll(board.getPictures());
		return response;
	}

}
