package ua.ithillel.hilleltask.dao;

import ua.ithillel.hilleltask.model.TaskList;

import java.util.List;

public interface TaskListDao {
    List<TaskList> findByBoardId(Integer boardId);
    TaskList findById(Integer id);
    TaskList save(TaskList taskList);
    TaskList update(TaskList taskList);
    TaskList delete(Integer id);
}
