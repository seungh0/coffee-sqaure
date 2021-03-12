package com.homecafe.service.comment.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCommentRequest {

	private Long commentId;

	private String content;

}
