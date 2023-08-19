package ua.ithillel.hilleltask.dao;

import ua.ithillel.hilleltask.model.Task;

import java.util.List;

public interface TaskDao {
    List<Task> findByListId(Integer listId);
    Task findById(Integer id);
    Task update(Task task);
    Task save(Task task);
}
