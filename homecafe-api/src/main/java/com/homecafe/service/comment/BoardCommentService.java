package com.homecafe.service.comment;

import com.homecafe.domain.comment.BoardComment;
import com.homecafe.domain.comment.BoardCommentRepository;
import com.homecafe.service.comment.dto.request.AddCommentRequest;
import com.homecafe.service.comment.dto.response.BoardCommentInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardCommentService {

	private final BoardCommentRepository boardCommentRepository;

	@Transactional
	public BoardCommentInfoResponse addComment(AddCommentRequest request, Long memberId) {
		BoardComment boardComment = boardCommentRepository.save(request.toEntity(memberId));
		return BoardCommentInfoResponse.of(boardComment);
	}

	@Transactional
	public void deleteComment(Long commentId, Long memberId) {
		BoardComment comment = BoardCommentServiceUtils.findCommentByIdAndMemberId(boardCommentRepository, commentId, memberId);
		comment.delete();
	}

}
