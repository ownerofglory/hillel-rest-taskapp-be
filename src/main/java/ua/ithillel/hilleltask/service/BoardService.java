package ua.ithillel.hilleltask.service;

import org.glassfish.jersey.spi.Contract;
import ua.ithillel.hilleltask.model.dto.BoardDTO;

import java.util.List;

@Contract
public interface BoardService {
    List<BoardDTO> getAllBoards();
    BoardDTO getBoardById(Integer id);
    BoardDTO editBoard(Integer id, BoardDTO boardDTO) throws Exception;
    BoardDTO deleteBoard(Integer id) throws Exception;
    BoardDTO createBoard(BoardDTO boardDTO);
}
