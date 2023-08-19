package ua.ithillel.hilleltask.dao;

import jakarta.inject.Inject;
import org.jvnet.hk2.annotations.Service;
import ua.ithillel.hilleltask.exception.EntityNotDeletedException;
import ua.ithillel.hilleltask.exception.EntityNotFoundException;
import ua.ithillel.hilleltask.exception.EntityNotSavedException;
import ua.ithillel.hilleltask.model.TaskList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskListMySqlJdbcDao implements TaskListDao {
    @Inject
    private Connection connection;

    @Override
    public List<TaskList> findByBoardId(Integer boardId) {
        try {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("SELECT * FROM task_list WHERE board_id = ?");
            statement.setInt(1, boardId);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            List<TaskList> lists = new ArrayList<>();
            while (resultSet.next()) {
                TaskList list = new TaskList();
                list.setId(resultSet.getInt("id"));
                list.setName(resultSet.getString("name"));
                lists.add(list);
            }

            return lists;
        } catch (SQLException e) {
            throw new EntityNotFoundException(String.format("Unable to find lists by board id %d: %s", boardId, e.getMessage()));
        }
    }

    @Override
    public TaskList findById(Integer id) {
        try {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("SELECT * FROM task_list WHERE id = ?");
            statement.setInt(1, id);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();

            TaskList list = new TaskList();
            list.setId(resultSet.getInt("id"));
            list.setName(resultSet.getString("name"));

            return list;
        } catch (SQLException e) {
            throw new EntityNotFoundException(String.format("Unable to find list by id %d: %s", id, e.getMessage()));
        }
    }

    @Override
    public TaskList save(TaskList taskList) {
        try {
            String sql = "INSERT INTO task_list (name, board_id) " +
                    "VALUES(?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, taskList.getName());
            statement.setInt(2, taskList.getBoardId());
            int rows = statement.executeUpdate();

            if (rows == 0) {
                throw new EntityNotSavedException(String.format("Unable to save list '%s'", taskList.getName()));
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                taskList.setId(generatedKeys.getInt(1));
            }

            return taskList;
        } catch (SQLException e) {
            throw new EntityNotSavedException(String.format("Unable to save list '%s': %s", taskList.getName(), e.getMessage()));
        }
    }

    @Override
    public TaskList update(TaskList taskList) {
        try {
            String sql = "UPDATE task_list  " +
                    "SET name = ? " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, taskList.getName());
            statement.setInt(2, taskList.getId());
            int rows = statement.executeUpdate();

            if (rows == 0) {
                throw new EntityNotSavedException(String.format("Unable to update list '%s'", taskList.getName()));
            }

            return taskList;
        } catch (SQLException e) {
            throw new EntityNotSavedException(String.format("Unable to update list '%s': %s", taskList.getName(), e.getMessage()));
        }
    }

    @Override
    public TaskList delete(Integer id) {
        try {
            TaskList taskList = findById(id);
            if (taskList == null) {
                return null;
            }

            String sql = "DELETE FROM task_list  " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();

            if (rows == 0) {
                throw new EntityNotDeletedException(String.format("Unable to delete list by id %d", id));
            }

            return taskList;
        } catch (SQLException e) {
            throw new EntityNotDeletedException(String.format("Unable to delete list by id %d: %s", id, e.getMessage()));
        }
    }
}
