package com.homecafe.service.board;

import com.homecafe.domain.board.Board;
import com.homecafe.domain.board.BoardCreatorCollection;
import com.homecafe.domain.board.BoardRepository;
import com.homecafe.domain.comment.BoardComment;
import com.homecafe.domain.comment.BoardCommentMemberCollection;
import com.homecafe.domain.comment.BoardCommentRepository;
import com.homecafe.domain.member.Member;
import com.homecafe.domain.member.MemberRepository;
import com.homecafe.service.board.dto.request.CreateBoardRequest;
import com.homecafe.service.board.dto.request.UpdateBoardRequest;
import com.homecafe.service.board.dto.response.BoardInfoResponse;
import com.homecafe.service.board.dto.response.BoardWithCommentInfoResponse;
import com.homecafe.service.board.dto.response.BoardWithCreatorInfoResponse;
import com.homecafe.service.comment.BoardCommentServiceUtils;
import com.homecafe.service.member.MemberServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;
	private final BoardCommentRepository boardCommentRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public BoardInfoResponse createBoard(CreateBoardRequest request, Long memberId) {
		Board board = boardRepository.save(request.toEntity(memberId));
		return BoardInfoResponse.of(board, memberId);
	}

	@Transactional(readOnly = true)
	public List<BoardWithCreatorInfoResponse> retrieveBoardList(Long lastBoardId, int size, Long memberId) {
		return lastBoardId == 0 ? getLatestBoards(size, memberId) : getLatestBoardLessThanId(lastBoardId, size, memberId);
	}

	private List<BoardWithCreatorInfoResponse> getLatestBoards(int size, Long memberId) {
		List<Board> boardList = boardRepository.findBoardsOrderByIdDesc(size);
		BoardCreatorCollection collection = BoardCreatorCollection.of(memberRepository, boardList);
		return boardList.stream()
				.map(board -> BoardWithCreatorInfoResponse.of(board, collection.getCreator(board.getMemberId()), memberId))
				.collect(Collectors.toList());
	}

	private List<BoardWithCreatorInfoResponse> getLatestBoardLessThanId(Long lastBoardId, int size, Long memberId) {
		List<Board> boardList = boardRepository.findBoardsLessThanOrderByIdDescLimit(lastBoardId, size);
		BoardCreatorCollection collection = BoardCreatorCollection.of(memberRepository, boardList);
		return boardList.stream()
				.map(board -> BoardWithCreatorInfoResponse.of(board, collection.getCreator(board.getMemberId()), memberId))
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<BoardWithCreatorInfoResponse> retrieveSearchBoardList(String keyword, Long lastBoardId, int size, Long memberId) {
		return lastBoardId == 0 ? getLatestSearchBoards(keyword, size, memberId) : getLatestSearchBoardLessThanId(keyword, lastBoardId, size, memberId);
	}

	private List<BoardWithCreatorInfoResponse> getLatestSearchBoards(String keyword, int size, Long memberId) {
		List<Board> boardList = boardRepository.findBoardsWithKeywordOrderByIdDesc(keyword, size);
		BoardCreatorCollection collection = BoardCreatorCollection.of(memberRepository, boardList);
		return boardList.stream()
				.map(board -> BoardWithCreatorInfoResponse.of(board, collection.getCreator(board.getMemberId()), memberId))
				.collect(Collectors.toList());
	}

	private List<BoardWithCreatorInfoResponse> getLatestSearchBoardLessThanId(String keyword, Long lastBoardId, int size, Long memberId) {
		List<Board> boardList = boardRepository.findBoardsWithKeywordLessThanOrderByIdDescLimit(keyword, lastBoardId, size);
		BoardCreatorCollection collection = BoardCreatorCollection.of(memberRepository, boardList);
		return boardList.stream()
				.map(board -> BoardWithCreatorInfoResponse.of(board, collection.getCreator(board.getMemberId()), memberId))
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public BoardWithCommentInfoResponse retrieveBoard(Long boardId, Long memberId) {
		Board board = BoardServiceUtils.findBoardById(boardRepository, boardId);
		Member creator = MemberServiceUtils.findMemberById(memberRepository, board.getMemberId());
		List<BoardComment> boardCommentList = BoardCommentServiceUtils.findAllBoardCommentsByCommentId(boardCommentRepository, board.getId());
		BoardCommentMemberCollection collection = BoardCommentMemberCollection.of(memberRepository, boardCommentList);
		return BoardWithCommentInfoResponse.of(board, boardCommentList, creator, collection, memberId);
	}

	@Transactional
	public BoardInfoResponse updateBoard(UpdateBoardRequest request, Long memberId) {
		Board board = BoardServiceUtils.findBoardByIdAndMemberId(boardRepository, request.getBoardId(), memberId);
		board.updateInfo(request.getDescription(), request.getPictures());
		return BoardInfoResponse.of(board, memberId);
	}

	@Transactional
	public void deleteBoard(Long boardId, Long memberId) {
		Board board = BoardServiceUtils.findBoardByIdAndMemberId(boardRepository, boardId, memberId);
		board.delete();
	}

	@Transactional
	public void addBoardLike(Long boardId, Long memberId) {
		Board board = BoardServiceUtils.findBoardById(boardRepository, boardId);
		board.addLike(memberId);
	}

	@Transactional
	public void cancelBoardLike(Long boardId, Long memberId) {
		Board board = BoardServiceUtils.findBoardById(boardRepository, boardId);
		board.cancelLike(memberId);
	}

	@Transactional(readOnly = true)
	public List<BoardInfoResponse> retrieveMyBoardList(Long memberId) {
		List<Board> boardList = boardRepository.findBoardByMemberId(memberId);
		return boardList.stream()
				.map(board -> BoardInfoResponse.of(board, memberId))
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<BoardInfoResponse> retrieveMyLikeBoardList(Long memberId) {
		List<Board> boardList = boardRepository.findLikeBoardByMemberId(memberId);
		return boardList.stream()
				.map(board -> BoardInfoResponse.of(board, memberId))
				.collect(Collectors.toList());
	}

}
