package ua.ithillel.hilleltask.model.dto;

import ua.ithillel.hilleltask.model.Task;
import ua.ithillel.hilleltask.model.TaskList;

public class TaskMoveDTO {
    private TaskListDTO oldList;
    private TaskListDTO newList;
    private TaskDTO task;

    public TaskListDTO getOldList() {
        return oldList;
    }

    public void setOldList(TaskListDTO oldList) {
        this.oldList = oldList;
    }

    public TaskListDTO getNewList() {
        return newList;
    }

    public void setNewList(TaskListDTO newList) {
        this.newList = newList;
    }

    public TaskDTO getTask() {
        return task;
    }

    public void setTask(TaskDTO task) {
        this.task = task;
    }
}
