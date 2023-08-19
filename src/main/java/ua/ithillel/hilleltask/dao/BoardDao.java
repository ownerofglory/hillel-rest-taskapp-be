package ua.ithillel.hilleltask.dao;

import org.glassfish.jersey.spi.Contract;
import ua.ithillel.hilleltask.model.Board;

import java.util.List;

@Contract
public interface BoardDao {
    Board save(Board board);
    List<Board> findAll();
    Board find(Integer id);
    Board update(Board board);
    Board delete(Integer id);
}
