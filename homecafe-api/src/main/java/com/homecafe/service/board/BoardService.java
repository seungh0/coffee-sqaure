package com.homecafe.service.board;

import com.homecafe.domain.board.Board;
import com.homecafe.domain.board.BoardRepository;
import com.homecafe.service.board.dto.request.CreateBoardRequest;
import com.homecafe.service.board.dto.response.BoardInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;

	@Transactional
	public BoardInfoResponse createBoard(CreateBoardRequest request, Long memberId) {
		Board board = boardRepository.save(request.toEntity(memberId));
		return BoardInfoResponse.of(board);
	}

	@Transactional(readOnly = true)
	public List<BoardInfoResponse> retrieveBoardList(Long lastBoardId, int size) {
		return lastBoardId == 0 ? getLatestBoards(size) : getLatestBoardLessThanId(lastBoardId, size);
	}

	private List<BoardInfoResponse> getLatestBoards(int size) {
		List<Board> boardList = boardRepository.findBoardsOrderByIdDesc(size);
		return boardList.stream()
				.map(BoardInfoResponse::of)
				.collect(Collectors.toList());
	}

	private List<BoardInfoResponse> getLatestBoardLessThanId(Long lastBoardId, int size) {
		List<Board> boardList = boardRepository.findBoardsLessThanOrderByIdDescLimit(lastBoardId, size);
		return boardList.stream()
				.map(BoardInfoResponse::of)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public BoardInfoResponse retrieveBoard(Long boardId) {
		Board board = BoardServiceUtils.findBoardById(boardRepository, boardId);
		return BoardInfoResponse.of(board);
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

}
