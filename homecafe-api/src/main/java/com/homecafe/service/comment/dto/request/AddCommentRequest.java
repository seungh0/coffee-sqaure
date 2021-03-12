package com.homecafe.service.comment.dto.request;

import com.homecafe.domain.comment.BoardComment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor
public class AddCommentRequest {

	@NotNull
	private Long boardId;

	@NotBlank
	private String content;

	public BoardComment toEntity(Long memberId) {
		return BoardComment.newInstance(boardId, memberId, content);
	}

}
