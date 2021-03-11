package com.homecafe.service.board.dto.request;

import com.homecafe.domain.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateBoardRequest {

	private String title;

	private String description;

	private List<String> pictures;

	public Board toEntity(Long memberId) {
		Board board = Board.newInstance(memberId, title, description);
		board.addPictures(pictures);
		return board;
	}

}
