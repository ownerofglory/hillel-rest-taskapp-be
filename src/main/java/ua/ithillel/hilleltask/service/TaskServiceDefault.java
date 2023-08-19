package ua.ithillel.hilleltask.service;

import jakarta.inject.Inject;
import org.jvnet.hk2.annotations.Service;
import ua.ithillel.hilleltask.dao.TaskDao;
import ua.ithillel.hilleltask.dao.TaskListDao;
import ua.ithillel.hilleltask.exception.InconsistentEntityOperation;
import ua.ithillel.hilleltask.model.Task;
import ua.ithillel.hilleltask.model.dto.TaskDTO;
import ua.ithillel.hilleltask.model.mapper.TaskMapper;

import java.util.List;

@Service
public class TaskServiceDefault implements TaskService {
    @Inject
    private TaskDao taskDao;
    @Inject
    private TaskMapper taskMapper;

    @Override
    public List<TaskDTO> getTasksByListId(Integer listId) {
        List<Task> byListId = taskDao.findByListId(listId);

        return byListId.stream()
                .map(taskMapper::taskToTaskDTO)
                .toList();
    }

    @Override
    public TaskDTO getTaskById(Integer listId, Integer id) {
        Task byId = taskDao.findById(id);
        return taskMapper.taskToTaskDTO(byId);
    }

    @Override
    public TaskDTO createTask(Integer listId, TaskDTO taskDTO) {
        Task task = taskMapper.taskDTOToTask(taskDTO);
        task.setTaskListId(listId);
        Task savedTask = taskDao.save(task);
        return taskMapper.taskToTaskDTO(savedTask);
    }

    @Override
    public TaskDTO editTask(Integer listId, Integer id, TaskDTO taskDTO) throws Exception {
        if (id.equals(taskDTO.getId())) {
            throw new InconsistentEntityOperation(String.format("taskId '%d' doesn't much with id of task you've provided %d", id, taskDTO.getId()));
        }

        Task task = taskMapper.taskDTOToTask(taskDTO);
        Task updatedTask = taskDao.update(task);
        return taskMapper.taskToTaskDTO(updatedTask);
    }

}
