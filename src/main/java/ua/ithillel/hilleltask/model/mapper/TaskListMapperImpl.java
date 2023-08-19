package ua.ithillel.hilleltask.model.mapper;

import org.jvnet.hk2.annotations.Service;
import ua.ithillel.hilleltask.model.TaskList;
import ua.ithillel.hilleltask.model.dto.TaskListDTO;

@Service
public class TaskListMapperImpl implements TaskListMapper {
    public TaskListMapperImpl() {
    }

    public TaskListDTO taskListToTaskListDTO(TaskList taskList) {
        if (taskList == null) {
            return null;
        } else {
            TaskListDTO taskListDTO = new TaskListDTO();
            taskListDTO.setId(taskList.getId());
            taskListDTO.setName(taskList.getName());
            taskListDTO.setBoardId(taskList.getBoardId());

            return taskListDTO;
        }
    }

    public TaskList taskListDTOToTaskList(TaskListDTO taskListdto) {
        if (taskListdto == null) {
            return null;
        } else {
            TaskList taskList = new TaskList();
            taskList.setId(taskListdto.getId());
            taskList.setName(taskListdto.getName());
            taskList.setBoardId(taskListdto.getBoardId());
            return taskList;
        }
    }
}

