package com.homecafe.service.comment.dto.request;

import com.homecafe.domain.comment.BoardComment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddCommentRequest {

	private Long boardId;

	private String content;

	public BoardComment toEntity(Long memberId) {
		return BoardComment.newInstance(boardId, memberId, content);
	}

}
