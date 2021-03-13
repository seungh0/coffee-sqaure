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

	@NotBlank(message = "피드의 내용을 입력해주세요")
	private String description;

	@Size(max = 6, message = "피드의 사진은 최대 6개까지 가능합니다.")
	@NotNull(message = "피드의 사진을 입력해주세요.")
	private List<String> pictures;

	public Board toEntity(Long memberId) {
		Board board = Board.newInstance(memberId, description);
		board.addPictures(pictures);
		return board;
	}

}
