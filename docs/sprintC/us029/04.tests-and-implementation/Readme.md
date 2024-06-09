# US029 - Record the completion of a task.

## 4. Tests 

**Test 1:** Check that a task cannot be marked as completed if it does not exist. 

    @Test
    void ensureTaskCannotBeCompletedIfNotExists() {
        Agenda agenda = new Agenda();
        Collaborator collaborator = new Collaborator("John Doe");
        Task task = new Task("Task 1", new Date(), "pending", collaborator);
        agenda.addTask(task);

        AgendaController controller = new AgendaController();
        controller.completeTask("Nonexistent Task");

        assertEquals("pending", task.getStatus());
    }

**Test 2:** Check that a task can be marked as completed.
    
        @Test
        void ensureTaskCanBeCompleted() {
            Agenda agenda = new Agenda();
            Collaborator collaborator = new Collaborator("John Doe");
            Task task = new Task("Task 1", new Date(), "pending", collaborator);
            agenda.addTask(task);
    
            AgendaController controller = new AgendaController();
            controller.completeTask("Task 1");
    
            assertEquals("completed", task.getStatus());
        }

**Test 3:** Check that a task cannot be marked as completed by an unassigned collaborator. 

    @Test
    void ensureTaskCannotBeCompletedByNonAssignedCollaborator() {
        Agenda agenda = new Agenda();
        Collaborator collaborator1 = new Collaborator("John Doe");
        Collaborator collaborator2 = new Collaborator("Jane Doe");
        Task task = new Task("Task 1", new Date(), "pending", collaborator1);
        agenda.addTask(task);

        CompleteTaskUI completeTaskUI = new CompleteTaskUI(collaborator2);
        completeTaskUI.run();

        assertEquals("pending", task.getStatus());
    }

## 5. Construction (Implementation)

### CompleteTaskUI

```java
public class CompleteTasksUI implements Runnable {
    private final ToDoListController toDoListController;
    public CompleteTasksUI() {
        this.toDoListController = new ToDoListController();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of the entry to cancel: ");
        String title = scanner.nextLine();

        boolean result = toDoListController.updateStatus(toDoListController.getToDoListEntry(title), EStatus.CANCELED);
        System.out.println(result ? "Entry cancelled successfully." : "Failed to cancel entry.");


    }
}
```




## 6. Integration and Demo 

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a