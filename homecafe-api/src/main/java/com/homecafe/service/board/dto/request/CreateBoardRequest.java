package com.homecafe.service.board.dto.request;

import com.homecafe.domain.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
public class CreateBoardRequest {

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@Size(max = 6)
	@NotNull
	private List<String> pictures;

	public Board toEntity(Long memberId) {
		Board board = Board.newInstance(memberId, title, description);
		board.addPictures(pictures);
		return board;
	}

}
