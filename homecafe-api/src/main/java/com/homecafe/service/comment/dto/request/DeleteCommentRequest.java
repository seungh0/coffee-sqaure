package com.homecafe.service.comment.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DeleteCommentRequest {

	@NotNull
	private Long boardId;

	@NotNull
	private Long commentId;

}
