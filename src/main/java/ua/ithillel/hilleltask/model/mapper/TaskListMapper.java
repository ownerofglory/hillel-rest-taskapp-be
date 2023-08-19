package ua.ithillel.hilleltask.model.mapper;

import org.glassfish.jersey.spi.Contract;
import org.mapstruct.Mapper;
import ua.ithillel.hilleltask.model.TaskList;
import ua.ithillel.hilleltask.model.dto.TaskListDTO;

@Contract
public interface TaskListMapper {
    TaskListDTO taskListToTaskListDTO(TaskList taskList);
    TaskList taskListDTOToTaskList(TaskListDTO taskListdto);
}
