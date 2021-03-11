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

	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<BoardPicture> pictureList = new ArrayList<>();

	private Board(Long memberId, String title, String description) {
		this.memberId = memberId;
		this.title = title;
		this.description = description;
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

}
