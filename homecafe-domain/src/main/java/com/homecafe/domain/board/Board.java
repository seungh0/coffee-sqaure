package com.homecafe.domain.board;

import com.homecafe.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long memberId;

	private String title;

	private String description;

	private int likesCount;

	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<BoardPicture> pictureList = new ArrayList<>();

	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<BoardLike> boardLikeList = new ArrayList<>();

	private Board(Long memberId, String title, String description) {
		this.memberId = memberId;
		this.title = title;
		this.description = description;
		this.likesCount = 0;
	}

	public static Board newInstance(Long memberId, String title, String description) {
		return new Board(memberId, title, description);
	}

	public void addPictures(List<String> pictureUrls) {
		for (String picture : pictureUrls) {
			this.addPicture(picture);
		}
	}

	private void addPicture(String pictureUrl) {
		this.pictureList.add(BoardPicture.of(this, pictureUrl));
	}

	public List<String> getPictures() {
		return this.pictureList.stream()
				.map(BoardPicture::getPictureUrl)
				.collect(Collectors.toList());
	}

	public void addLike(Long memberId) {
		if (hasAlreadyLike(memberId)) {
			throw new IllegalArgumentException(String.format("이미 멤버 (%s)는 피드 (%s)에 좋아요를 눌렀습니다", memberId, this.id));
		}
		BoardLike boardLike = BoardLike.of(this, memberId);
		this.boardLikeList.add(boardLike);
		this.likesCount++;
	}

	private boolean hasAlreadyLike(Long memberId) {
		return this.boardLikeList.stream()
				.anyMatch(boardLike -> boardLike.isSameEntity(memberId));
	}

	public void cancelLike(Long memberId) {
		BoardLike boardLike = findLike(memberId);
		boardLikeList.remove(boardLike);
		this.likesCount--;
	}

	private BoardLike findLike(Long memberId) {
		return this.boardLikeList.stream()
				.filter(mapper -> mapper.isSameEntity(memberId))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("멤버 (%s)는 피 (%s)에 좋아요를 누른 적이 없습니다", memberId, this.id)));
	}

}
