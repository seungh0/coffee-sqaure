package com.homecafe.domain.comment;

import com.homecafe.domain.member.Member;
import com.homecafe.domain.member.MemberRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardCommentMemberCollection {

	private final Map<Long, Member> memberMap = new HashMap<>();

	private BoardCommentMemberCollection(MemberRepository memberRepository, List<BoardComment> boardCommentList) {
		this.memberMap.putAll(findMembersInBoardList(memberRepository, boardCommentList));
	}

	private Map<Long, Member> findMembersInBoardList(MemberRepository memberRepository, List<BoardComment> boardCommentList) {
		List<Member> memberList = memberRepository.findAllById(getMemberIdsFrom(boardCommentList));
		return memberList.stream()
				.collect(Collectors.toMap(Member::getId, member -> member));
	}

	private Set<Long> getMemberIdsFrom(List<BoardComment> boardCommentList) {
		return boardCommentList.stream()
				.map(BoardComment::getMemberId)
				.collect(Collectors.toSet());
	}

	public static BoardCommentMemberCollection of(MemberRepository memberRepository, List<BoardComment> boardCommentList) {
		return new BoardCommentMemberCollection(memberRepository, boardCommentList);
	}

	public Member getMember(Long memberId) {
		return memberMap.get(memberId);
	}

}
