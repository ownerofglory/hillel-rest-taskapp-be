package ua.ithillel.hilleltask;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.ithillel.hilleltask.model.dto.TaskDTO;
import ua.ithillel.hilleltask.model.dto.TaskListDTO;
import ua.ithillel.hilleltask.model.dto.TaskMoveDTO;
import ua.ithillel.hilleltask.service.TaskListService;
import ua.ithillel.hilleltask.service.TaskService;

import java.util.List;

@Path("lists")
public class TaskListsResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskListsResource.class);

    @Inject
    private TaskListService taskListService;
    @Inject
    private TaskService taskService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TaskListDTO> getAllByBoard(@QueryParam("boardId") Integer boardId) {
        LOGGER.info("Get All by board");
        LOGGER.debug("Get All by board id {}", boardId);
        return taskListService.getTaskListsByBoardId(boardId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TaskListDTO add(TaskListDTO taskListDTO) {
        LOGGER.info("Add task list");
        LOGGER.debug("Add task list {}", taskListDTO);
        return taskListService.createTaskList(taskListDTO);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TaskListDTO get(@PathParam("id") Integer id) {
        return taskListService.getTaskListById(id);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TaskListDTO edit(@PathParam("id") Integer id, TaskListDTO taskListDTO) {
        try {
            return taskListService.editTaskList(id, taskListDTO);
        } catch (Exception e) {
            LOGGER.error("Exception when editing a task list {}", e.getMessage());
            throw new RuntimeException();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TaskListDTO delete(@PathParam("id") Integer id) {
        return taskListService.deleteTaskList(id);
    }

    @GET
    @Path("/{listId}/tasks")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TaskDTO> getTasks(@PathParam("listId") Integer listId) {
        return taskService.getTasksByListId(listId);
    }


    @POST
    @Path("/{listId}/tasks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TaskDTO addTask(@PathParam("listId") Integer listId, TaskDTO taskDTO) {
        return taskService.createTask(listId, taskDTO);
    }

    @GET
    @Path("/{listId}/tasks/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public TaskDTO getTask(@PathParam("listId") Integer listId,
                           @PathParam("taskId") Integer taskId) {
        return taskService.getTaskById(listId, taskId);
    }


    @PUT
    @Path("/{listId}/tasks/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public TaskDTO editTask(@PathParam("listId") Integer listId,
                           @PathParam("taskId") Integer taskId,
                           TaskDTO taskDTO) {
        try {
            return taskService.editTask(listId, taskId, taskDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/taskMove")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TaskMoveDTO moveTask(TaskMoveDTO taskMoveDTO) {
        return taskService.moveTask(taskMoveDTO);
    }
}
