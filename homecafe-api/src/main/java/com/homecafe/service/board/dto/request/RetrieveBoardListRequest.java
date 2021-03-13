package com.homecafe.service.board.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor
public class RetrieveBoardListRequest {

	@Min(value = 0, message = "lastBoardId는 최소 0입니다.")
	@NotNull(message = "lastBoardId를 입력해주세요")
	private Long lastBoardId;

	@Min(value = 1, message = "최소 한 개 이상의 피드를 조회할 수 있습니다.")
	@NotNull(message = "받아올 피드의 수를 정해주세요.")
	private int size;

}
