package com.homecafe.service.comment.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor
public class DeleteCommentRequest {

	@NotNull(message = "피드의 id를 선택해주세요")
	private Long boardId;

	@NotNull(message = "댓글의 id를 선택해주세요")
	private Long commentId;

}
