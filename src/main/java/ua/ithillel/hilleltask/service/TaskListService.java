package ua.ithillel.hilleltask.service;

import ua.ithillel.hilleltask.model.dto.TaskDTO;
import ua.ithillel.hilleltask.model.dto.TaskListDTO;

import java.util.List;

public interface TaskListService {
    List<TaskListDTO> getTaskListsByBoardId(Integer boardId);
    TaskListDTO getTaskListById(Integer id);
    TaskListDTO createTaskList(TaskListDTO taskListDTO);
    TaskListDTO editTaskList(Integer id, TaskListDTO taskListDTO) throws Exception;
    TaskListDTO deleteTaskList(Integer id);
}
