package com.homecafe.service.board.dto.request;

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
public class UpdateBoardRequest {

	@NotNull(message = "피드의 id를 선택해주세요")
	private Long boardId;

	@NotBlank(message = "피드의 내용을 입력해주세요")
	private String description;

	@Size(max = 6, message = "피드의 사진은 최대 6개까지 가능합니다.")
	@NotNull(message = "피드의 사진을 입력해주세요.")
	private List<String> pictures;

}
