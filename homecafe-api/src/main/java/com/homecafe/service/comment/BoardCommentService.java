package com.homecafe.service.comment;

import com.homecafe.domain.board.Board;
import com.homecafe.domain.board.BoardRepository;
import com.homecafe.domain.comment.BoardComment;
import com.homecafe.domain.comment.BoardCommentRepository;
import com.homecafe.service.board.BoardServiceUtils;
import com.homecafe.service.comment.dto.request.AddCommentRequest;
import com.homecafe.service.comment.dto.request.DeleteCommentRequest;
import com.homecafe.service.comment.dto.request.UpdateCommentRequest;
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
		Board board = BoardServiceUtils.findBoardById(boardRepository, request.getBoardId());
		board.increaseCommentsCount();
		boardCommentRepository.save(request.toEntity(memberId));
	}

	@Transactional
	public void updateComment(UpdateCommentRequest request, Long memberId) {
		BoardComment comment = BoardCommentServiceUtils.findCommentByIdAndMemberId(boardCommentRepository, request.getCommentId(), memberId);
		comment.update(request.getContent());
	}

	@Transactional
	public void deleteComment(DeleteCommentRequest request, Long memberId) {
		Board board = BoardServiceUtils.findBoardById(boardRepository, request.getBoardId());
		BoardComment comment = BoardCommentServiceUtils.findCommentByIdAndMemberId(boardCommentRepository, request.getCommentId(), memberId);
		board.decreaseCommentsCount();
		comment.delete();
	}

}
