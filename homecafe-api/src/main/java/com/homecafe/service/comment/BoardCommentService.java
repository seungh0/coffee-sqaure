package com.homecafe.service.comment;

import com.homecafe.domain.board.BoardRepository;
import com.homecafe.domain.comment.BoardComment;
import com.homecafe.domain.comment.BoardCommentRepository;
import com.homecafe.service.board.BoardServiceUtils;
import com.homecafe.service.comment.dto.request.AddCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardCommentService {

	private final BoardRepository boardRepository;
	private final BoardCommentRepository boardCommentRepository;

	@Transactional
	public void addComment(AddCommentRequest request, Long memberId) {
		BoardServiceUtils.validateExistBoard(boardRepository, request.getBoardId());
		boardCommentRepository.save(request.toEntity(memberId));
	}

	@Transactional
	public void deleteComment(Long commentId, Long memberId) {
		BoardComment comment = BoardCommentServiceUtils.findCommentByIdAndMemberId(boardCommentRepository, commentId, memberId);
		comment.delete();
	}

}
