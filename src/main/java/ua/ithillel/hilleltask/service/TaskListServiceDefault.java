package ua.ithillel.hilleltask.service;

import jakarta.inject.Inject;
import org.jvnet.hk2.annotations.Service;
import ua.ithillel.hilleltask.dao.TaskListDao;
import ua.ithillel.hilleltask.exception.InconsistentEntityOperation;
import ua.ithillel.hilleltask.model.TaskList;
import ua.ithillel.hilleltask.model.dto.TaskListDTO;
import ua.ithillel.hilleltask.model.mapper.TaskListMapper;

import java.util.List;

@Service
public class TaskListServiceDefault implements TaskListService {
    @Inject
    private TaskListDao taskListDao;
    @Inject
    private TaskListMapper taskListMapper;

    @Override
    public List<TaskListDTO> getTaskListsByBoardId(Integer boardId) {
        List<TaskList> byBoardId = taskListDao.findByBoardId(boardId);
        return byBoardId.stream()
                .map(taskListMapper::taskListToTaskListDTO)
                .toList();
    }

    @Override
    public TaskListDTO getTaskListById(Integer id) {
        TaskList byId = taskListDao.findById(id);
        return taskListMapper.taskListToTaskListDTO(byId);
    }

    @Override
    public TaskListDTO createTaskList(TaskListDTO taskListDTO) {
        TaskList taskList = taskListMapper.taskListDTOToTaskList(taskListDTO);
        TaskList savedTaskList = taskListDao.save(taskList);
        return taskListMapper.taskListToTaskListDTO(savedTaskList);
    }

    @Override
    public TaskListDTO editTaskList(Integer id, TaskListDTO taskListDTO) throws Exception {
        if (!id.equals(taskListDTO.getId())) {
            throw new InconsistentEntityOperation(String.format("taskListId '%d' doesn't much with id of list you've provided %d", id, taskListDTO.getId()));
        }

        TaskList taskList = taskListMapper.taskListDTOToTaskList(taskListDTO);
        TaskList updatedTaskList = taskListDao.update(taskList);

        return taskListMapper.taskListToTaskListDTO(updatedTaskList);
    }

    @Override
    public TaskListDTO deleteTaskList(Integer id) {
        TaskList deletedTaskList = taskListDao.delete(id);
        return taskListMapper.taskListToTaskListDTO(deletedTaskList);
    }
}
