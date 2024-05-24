package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Task;

import java.util.ArrayList;
import java.util.List;

public class ToDoListRepository {
    private ToDoList toDoList;
    private List<Task> tasks;

    public ToDoListRepository() {
        this.toDoList = new ToDoList();
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean addTaskToToDoList(Task task) {
        this.tasks.add(task);
        return this.toDoList.addTask(task);
    }

    public boolean removeTaskFromToDoList(Task task) {
        return toDoList.removeTask(task) && tasks.remove(task);
    }

    public ToDoList getToDoList() {
        return toDoList;
    }
}
