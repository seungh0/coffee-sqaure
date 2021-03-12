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

	@NotNull
	private Long boardId;

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@Size(max = 6)
	@NotNull
	private List<String> pictures;

}
