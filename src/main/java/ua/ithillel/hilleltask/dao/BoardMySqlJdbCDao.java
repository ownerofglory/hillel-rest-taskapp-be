package ua.ithillel.hilleltask.dao;

import jakarta.inject.Inject;
import org.jvnet.hk2.annotations.Service;
import ua.ithillel.hilleltask.exception.EntityNotDeletedException;
import ua.ithillel.hilleltask.exception.EntityNotFoundException;
import ua.ithillel.hilleltask.exception.EntityNotSavedException;
import ua.ithillel.hilleltask.model.Board;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardMySqlJdbCDao implements BoardDao {
    @Inject
    private Connection connection;

    @Override
    public Board save(Board board) {
        try {
            String sql = "INSERT INTO board (name) " +
                    "VALUES(?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, board.getName());
            int rows = statement.executeUpdate();

            if (rows == 0) {
                throw new EntityNotSavedException(String.format("Unable to save board '%s'", board.getName()));
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                board.setId(generatedKeys.getInt(1));
            }

            return board;
        } catch (SQLException e) {
            throw new EntityNotSavedException(String.format("Unable to save board '%s': %s", board.getName(), e.getMessage()));
        }
    }

    @Override
    public List<Board> findAll() {

        try {
            Statement statement = null;
            statement = connection.createStatement();
            statement.execute("SELECT * FROM board");

            ResultSet resultSet = statement.getResultSet();
            List<Board> boards = new ArrayList<>();
            while (resultSet.next()) {
                Board board = new Board();
                board.setId(resultSet.getInt("id"));
                board.setName(resultSet.getString("name"));
                boards.add(board);
            }

            return boards;
        } catch (SQLException e) {
            throw new EntityNotFoundException(String.format("Unable to find boards: %s", e.getMessage()));
        }
    }

    @Override
    public Board find(Integer id) {
        try {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("SELECT * FROM board WHERE id = ?");
            statement.setInt(1, id);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            Board board = new Board();
            board.setId(resultSet.getInt("id"));
            board.setName(resultSet.getString("name"));

            return board;
        } catch (SQLException e) {
            throw new EntityNotFoundException(String.format("Unable to find board by id %d: %s", id, e.getMessage()));
        }
    }

    @Override
    public Board update(Board board) {
        try {
            String sql = "UPDATE board  " +
                    "SET name = ? " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, board.getName());
            statement.setInt(2, board.getId());
            int rows = statement.executeUpdate();

            if (rows == 0) {
                throw new EntityNotSavedException(String.format("Unable to update board '%s'", board.getName()));
            }

            return board;
        } catch (SQLException e) {
            throw new EntityNotSavedException(String.format("Unable to update board '%s': %s", board.getName(), e.getMessage()));
        }
    }

    @Override
    public Board delete(Integer id) {
        try {
            Board board = find(id);
            if (board == null) {
                return null;
            }

            String sql = "DELETE FROM board  " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();

            if (rows == 0) {
                throw new EntityNotDeletedException(String.format("Unable to delete board by id %d", id));
            }

            return board;
        } catch (SQLException e) {
            throw new EntityNotDeletedException(String.format("Unable to delete board by id %d: %s", id, e.getMessage()));
        }
    }
}
