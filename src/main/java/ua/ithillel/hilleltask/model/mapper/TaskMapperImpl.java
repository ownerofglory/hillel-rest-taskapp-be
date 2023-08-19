package ua.ithillel.hilleltask.model.mapper;

import org.jvnet.hk2.annotations.Service;
import ua.ithillel.hilleltask.model.Task;
import ua.ithillel.hilleltask.model.dto.TaskDTO;

@Service
public class TaskMapperImpl implements TaskMapper {
    public TaskMapperImpl() {
    }

    public TaskDTO taskToTaskDTO(Task task) {
        if (task == null) {
            return null;
        } else {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(task.getId());
            taskDTO.setTitle(task.getTitle());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setTaskListId(task.getTaskListId());
            return taskDTO;
        }
    }

    public Task taskDTOToTask(TaskDTO taskDTO) {
        if (taskDTO == null) {
            return null;
        } else {
            Task task = new Task();
            task.setId(taskDTO.getId());
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setTaskListId(taskDTO.getTaskListId());
            return task;
        }
    }
}