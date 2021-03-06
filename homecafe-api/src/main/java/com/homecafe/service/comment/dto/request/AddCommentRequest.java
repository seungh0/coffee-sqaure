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

	@NotNull(message = "피드의 id를 선택해주세요")
	private Long boardId;

	@NotBlank(message = "댓글의 내용을 입력해주세요")
	private String content;

	public BoardComment toEntity(Long memberId) {
		return BoardComment.newInstance(boardId, memberId, content);
	}

}
