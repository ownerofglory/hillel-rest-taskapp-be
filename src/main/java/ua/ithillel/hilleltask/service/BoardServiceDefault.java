package ua.ithillel.hilleltask.service;

import jakarta.inject.Inject;
import org.jvnet.hk2.annotations.Service;
import ua.ithillel.hilleltask.dao.BoardDao;
import ua.ithillel.hilleltask.exception.InconsistentEntityOperation;
import ua.ithillel.hilleltask.model.Board;
import ua.ithillel.hilleltask.model.dto.BoardDTO;
import ua.ithillel.hilleltask.model.mapper.BoardMapper;

import java.util.List;

@Service
public class BoardServiceDefault implements BoardService {
    @Inject
    private BoardDao boardDao;
    @Inject
    private BoardMapper boardMapper;

    @Override
    public List<BoardDTO> getAllBoards() {
        List<Board> all = boardDao.findAll();

        return all.stream()
                .map(boardMapper::boardToBoardDTO)
                .toList();
    }

    @Override
    public BoardDTO getBoardById(Integer id) {
        Board board = boardDao.find(id);
        return boardMapper.boardToBoardDTO(board);
    }

    @Override
    public BoardDTO editBoard(Integer id, BoardDTO boardDTO) throws Exception {
        if (!id.equals(boardDTO.getId())) {
            throw new InconsistentEntityOperation(String.format("boardId '%d' doesn't much with id of board you've provided %d", id, boardDTO.getId()));
        }

        Board board = boardMapper.boardDTOToBoard(boardDTO);

        Board updateDBoard = boardDao.update(board);

        return boardMapper.boardToBoardDTO(updateDBoard);
    }

    @Override
    public BoardDTO deleteBoard(Integer id) throws Exception {
        Board deletedBoard = boardDao.delete(id);

        return boardMapper.boardToBoardDTO(deletedBoard);
    }

    @Override
    public BoardDTO createBoard(BoardDTO boardDTO) {
        Board board = boardMapper.boardDTOToBoard(boardDTO);

        Board savedBoard = boardDao.save(board);

        return boardMapper.boardToBoardDTO(savedBoard);
    }
}
