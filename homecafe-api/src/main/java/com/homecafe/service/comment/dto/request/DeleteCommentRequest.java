package com.homecafe.service.comment.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor
public class DeleteCommentRequest {

	@NotNull
	private Long boardId;

	@NotNull
	private Long commentId;

}
