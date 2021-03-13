package com.homecafe.service.comment.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor
public class UpdateCommentRequest {

	@NotNull(message = "댓글의 id를 선택해주세요")
	private Long commentId;

	@NotBlank(message = "변경할 댓글의 내용을 입력해주세요")
	private String content;

}
