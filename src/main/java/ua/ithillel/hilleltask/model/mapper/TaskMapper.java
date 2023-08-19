package ua.ithillel.hilleltask.model.mapper;

import org.glassfish.jersey.spi.Contract;
import org.mapstruct.Mapper;
import ua.ithillel.hilleltask.model.Task;
import ua.ithillel.hilleltask.model.dto.TaskDTO;

@Contract
public interface TaskMapper {
    TaskDTO taskToTaskDTO(Task task);
    Task taskDTOToTask(TaskDTO taskDTO);
}
