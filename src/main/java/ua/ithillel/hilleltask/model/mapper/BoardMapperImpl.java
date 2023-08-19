package ua.ithillel.hilleltask.model.mapper;

import org.jvnet.hk2.annotations.Service;
import ua.ithillel.hilleltask.model.Board;
import ua.ithillel.hilleltask.model.dto.BoardDTO;

@Service
public class BoardMapperImpl implements BoardMapper {
        public BoardMapperImpl() {
        }

        public BoardDTO boardToBoardDTO(Board board) {
            if (board == null) {
                return null;
            } else {
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setId(board.getId());
                boardDTO.setName(board.getName());
                return boardDTO;
            }
        }

        public Board boardDTOToBoard(BoardDTO boardDTO) {
            if (boardDTO == null) {
                return null;
            } else {
                Board board = new Board();
                board.setId(boardDTO.getId());
                board.setName(boardDTO.getName());
                return board;
            }
        }

}
