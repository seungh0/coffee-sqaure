package com.homecafe.domain.comment;

import com.homecafe.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BoardComment extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long boardId;

	@Column(nullable = false)
	private Long memberId;

	@Column(nullable = false)
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

	public void update(String content) {
		this.content = content;
	}

}
