package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class represents a TaskCategoryRepository in the system.
 * It manages a list of TaskCategory objects and provides methods to manipulate and retrieve them.
 */
public class TaskCategoryRepository {

    /**
     * The list of TaskCategory objects.
     */
    private final List<TaskCategory> taskCategories;

    /**
     * Constructs a TaskCategoryRepository object.
     * It initializes the taskCategories list as an empty ArrayList.
     */
    public TaskCategoryRepository() {
        taskCategories = new ArrayList<>();
    }

    /**
     * This method returns an existing Task Category by its description.
     *
     * @param taskCategoryDescription The description of the task category to be retrieved.
     * @return The task category.
     * @throws IllegalArgumentException if the task category does not exist, which should never happen.
     */
    public TaskCategory getTaskCategoryByDescription(String taskCategoryDescription) {
        TaskCategory newTaskCategory = new TaskCategory(taskCategoryDescription);
        TaskCategory taskCategory = null;
        if (taskCategories.contains(newTaskCategory)) {
            taskCategory = taskCategories.get(taskCategories.indexOf(newTaskCategory));
        }
        if (taskCategory == null) {
            throw new IllegalArgumentException(
                    "Task Category requested for [" + taskCategoryDescription + "] does not exist.");
        }
        return taskCategory;
    }

    /**
     * Adds a TaskCategory to the repository if it is valid.
     * If the TaskCategory is added successfully, it returns an Optional containing the TaskCategory.
     * If the TaskCategory is not valid, it returns an empty Optional.
     *
     * @param taskCategory the TaskCategory to add
     * @return an Optional containing the added TaskCategory if successful, or an empty Optional if not
     */
    public Optional<TaskCategory> add(TaskCategory taskCategory) {

        Optional<TaskCategory> newTaskCategory = Optional.empty();
        boolean operationSuccess = false;

        if (validateTaskCategory(taskCategory)) {
            newTaskCategory = Optional.of(taskCategory.clone());
            operationSuccess = taskCategories.add(newTaskCategory.get());
        }

        if (!operationSuccess) {
            newTaskCategory = Optional.empty();
        }

        return newTaskCategory;
    }

    /**
     * Validates a TaskCategory.
     * It checks if the TaskCategory already exists in the list.
     *
     * @param taskCategory the TaskCategory to validate
     * @return true if the TaskCategory is valid, false otherwise
     */
    private boolean validateTaskCategory(TaskCategory taskCategory) {
        boolean isValid = !taskCategories.contains(taskCategory);
        return isValid;
    }

    /**
     * Returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<TaskCategory> getTaskCategories() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(taskCategories);
    }
}