package ua.ithillel.hilleltask.service;

import ua.ithillel.hilleltask.model.dto.TaskDTO;
import ua.ithillel.hilleltask.model.dto.TaskMoveDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getTasksByListId(Integer listId);
    TaskDTO getTaskById(Integer listId, Integer id);
    TaskDTO createTask(Integer listId, TaskDTO taskDTO);
    TaskDTO editTask(Integer listId, Integer id, TaskDTO taskDTO) throws Exception;

    TaskMoveDTO moveTask(TaskMoveDTO taskMoveDTO);
}
