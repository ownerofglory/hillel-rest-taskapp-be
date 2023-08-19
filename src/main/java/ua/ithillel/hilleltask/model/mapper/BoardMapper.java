package ua.ithillel.hilleltask.model.mapper;

import org.glassfish.jersey.spi.Contract;
import org.mapstruct.Mapper;
import ua.ithillel.hilleltask.model.Board;
import ua.ithillel.hilleltask.model.dto.BoardDTO;

@Contract
public interface BoardMapper {
    BoardDTO boardToBoardDTO(Board board);
    Board boardDTOToBoard(BoardDTO boardDTO);
}
