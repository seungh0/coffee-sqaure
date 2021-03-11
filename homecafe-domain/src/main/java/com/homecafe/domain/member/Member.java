package com.homecafe.domain.member;

import com.homecafe.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private Email email;

	private String name;

	private String profileUrl;

	@Builder
	public Member(String email, String name, String profileUrl) {
		this.email = Email.of(email);
		this.name = name;
		this.profileUrl = profileUrl;
	}

	public static Member newKaKaoMember(String email, String name, String profileUrl) {
		return Member.builder()
				.email(email)
				.name(name)
				.profileUrl(profileUrl)
				.build();
	}

	public String getEmail() {
		return this.email.getEmail();
	}

	@Builder
	public Member(String email, String name, String profileUrl, MemberProvider provider) {
		this.email = email;
		this.name = name;
		this.profileUrl = profileUrl;
		this.provider = provider;
	}

	public static Member newKaKaoMember(String email, String name, String profileUrl) {
		return Member.builder()
				.email(email)
				.name(name)
				.profileUrl(profileUrl)
				.provider(MemberProvider.KAKAO)
				.build();
	}

}
