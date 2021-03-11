package com.homecafe.service.board.dto.request;

import com.homecafe.domain.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateBoardRequest {

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@Max(6)
	@NotNull
	private List<String> pictures;

	public Board toEntity(Long memberId) {
		Board board = Board.newInstance(memberId, title, description);
		board.addPictures(pictures);
		return board;
	}

}
