package com.homecafe.service.board.dto.response;

import com.homecafe.domain.board.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardInfoResponse {

	private final Long id;

	private final String title;

	private final String description;

	private final int likesCount;

	private final List<String> pictureUrls = new ArrayList<>();

	public static BoardInfoResponse of(Board board) {
		BoardInfoResponse response = new BoardInfoResponse(board.getId(), board.getTitle(), board.getDescription(), board.getLikesCount());
		response.pictureUrls.addAll(board.getPictures());
		return response;
	}

}
