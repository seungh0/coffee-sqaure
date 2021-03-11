package com.homecafe.domain.comment;

import com.homecafe.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BoardComment extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long boardId;

	private Long memberId;

	private String content;

	private boolean isDeleted;

	private BoardComment(Long boardId, Long memberId, String content) {
		this.boardId = boardId;
		this.memberId = memberId;
		this.content = content;
		this.isDeleted = false;
	}

	public static BoardComment newInstance(Long boardId, Long memberId, String content) {
		return new BoardComment(boardId, memberId, content);
	}

	public void delete() {
		this.isDeleted = true;
	}

}
