package ua.ithillel.hilleltask.dao;

import jakarta.inject.Inject;
import org.jvnet.hk2.annotations.Service;
import ua.ithillel.hilleltask.exception.EntityNotFoundException;
import ua.ithillel.hilleltask.exception.EntityNotSavedException;
import ua.ithillel.hilleltask.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskMySqlJdbcDao implements TaskDao {
    @Inject
    private Connection connection;

    @Override
    public List<Task> findByListId(Integer listId) {
        try {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("SELECT * FROM task WHERE task_list_id = ?");
            statement.setInt(1, listId);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            List<Task> tasks = new ArrayList<>();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setTitle(resultSet.getString("title"));
                task.setDescription(resultSet.getString("description"));
                task.setTaskListId(resultSet.getInt("task_list_id"));
                tasks.add(task);
            }

            return tasks;
        } catch (SQLException e) {
            throw new EntityNotFoundException(String.format("Unable to find task by listId %d: %s", listId, e.getMessage()));
        }
    }

    @Override
    public Task findById(Integer id) {
        try {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("SELECT * FROM task WHERE id = ?");
            statement.setInt(1, id);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();

            Task task = new Task();
            task.setId(resultSet.getInt("id"));
            task.setTitle(resultSet.getString("title"));
            task.setDescription(resultSet.getString("description"));
            task.setTaskListId(resultSet.getInt("task_list_id"));

            return task;
        } catch (SQLException e) {
            throw new EntityNotFoundException(String.format("Unable to find task by id %d: %s", id, e.getMessage()));
        }
    }

    @Override
    public Task update(Task task) {
        try {
            String sql = "UPDATE task  " +
                    "SET title = ?, " +
                    " description = ? " +
                    "WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setInt(3, task.getId());
            int rows = statement.executeUpdate();

            if (rows == 0) {
                throw new EntityNotSavedException(String.format("Unable to update task ('%s', '%s')", task.getTitle(), task.getDescription()));
            }

            return task;
        } catch (SQLException e) {
            throw new EntityNotSavedException(String.format("Unable to update task ('%s', '%s'): %s", task.getTitle(), task.getDescription(), e.getMessage()));
        }
    }

    @Override
    public Task save(Task task) {
        try {
            String sql = "INSERT INTO task (title, description, task_list_id) " +
                    "VALUES(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setInt(3, task.getTaskListId());
            int rows = statement.executeUpdate();

            if (rows == 0) {
                throw new EntityNotSavedException(String.format("Unable to save task ('%s', '%s')", task.getTitle(), task.getDescription()));
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                task.setId(generatedKeys.getInt(1));
            }

            return task;
        } catch (SQLException e) {
            throw new EntityNotSavedException(String.format("Unable to save task ('%s', '%s'): %s", task.getTitle(), task.getDescription(), e.getMessage()));
        }
    }
}
