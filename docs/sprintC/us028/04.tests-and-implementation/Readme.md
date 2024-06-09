# US028 - List assigned tasks between two dates.

## 4. Tests 

**Test 1:** Check that you can list tasks between two dates. 

    @Test
    void ensureTasksAreListedBetweenDates() {
        Agenda agenda = new Agenda();
        Collaborator collaborator = new Collaborator("John Doe");
        Task task1 = new Task("Task 1", new Date(2023, 6, 1), "pending", collaborator);
        Task task2 = new Task("Task 2", new Date(2023, 6, 15), "completed", collaborator);
        Task task3 = new Task("Task 3", new Date(2023, 6, 30), "pending", collaborator);

        agenda.addTask(task1);
        agenda.addTask(task2);
        agenda.addTask(task3);

        AgendaController controller = new AgendaController();
        Date startDate = new Date(2023, 6, 1);
        Date endDate = new Date(2023, 6, 30);

        List<Task> tasks = controller.getEntriesBetweenDates(startDate, endDate, collaborator);
        assertEquals(3, tasks.size());
    }

**Test 2:** Check that the task list is sorted by date.

    @Test
    void ensureTasksAreSortedByDate() {
        Agenda agenda = new Agenda();
        Collaborator collaborator = new Collaborator("John Doe");
        Task task1 = new Task("Task 1", new Date(2023, 6, 30), "pending", collaborator);
        Task task2 = new Task("Task 2", new Date(2023, 6, 1), "completed", collaborator);
        Task task3 = new Task("Task 3", new Date(2023, 6, 15), "pending", collaborator);

        agenda.addTask(task1);
        agenda.addTask(task2);
        agenda.addTask(task3);

        AgendaController controller = new AgendaController();
        Date startDate = new Date(2023, 6, 1);
        Date endDate = new Date(2023, 6, 30);

        List<Task> tasks = controller.getEntriesBetweenDates(startDate, endDate, collaborator);
        assertEquals(3, tasks.size());
        assertEquals(task2, tasks.get(0));
        assertEquals(task3, tasks.get(1));
        assertEquals(task1, tasks.get(2));
    }

**Test 3:** Check that you can filter tasks by status.

    @Test
    void ensureTasksCanBeFilteredByStatus() {
        Agenda agenda = new Agenda();
        Collaborator collaborator = new Collaborator("John Doe");
        Task task1 = new Task("Task 1", new Date(2023, 6, 1), "pending", collaborator);
        Task task2 = new Task("Task 2", new Date(2023, 6, 15), "completed", collaborator);
        Task task3 = new Task("Task 3", new Date(2023, 6, 30), "pending", collaborator);

        agenda.addTask(task1);
        agenda.addTask(task2);
        agenda.addTask(task3);

        AgendaController controller = new AgendaController();
        Date startDate = new Date(2023, 6, 1);
        Date endDate = new Date(2023, 6, 30);

        List<Task> tasks = controller.getEntriesBetweenDates(startDate, endDate, collaborator);
        tasks = controller.filterTasksByStatus(tasks, "pending");

        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task3));
        assertFalse(tasks.contains(task2));
    }

## 5. Construction (Implementation)

### ViewTasksTwoDatesUI

```java

public class ViewTasksTwoDatesUI implements Runnable{
    private AgendaController agendaController;

    public ViewTasksTwoDatesUI() {
        this.agendaController = new AgendaController();
    }

    public void run() {
        System.out.println("--View Tasks between two dates:--");
        Date dateBegin = Utils.readDateFromConsole("Enter the begin date (dd-mm-yyy):");
        Date dateEnd = Utils.readDateFromConsole("Enter the end date (dd-mm-yyy):");

        List<Entry> entries = agendaController.getEntriesBetweenDates(dateBegin, dateEnd);
        if(entries.isEmpty()){
            System.out.println("No entries found between the dates.");
            return;
        }
        for (Entry entry : entries) {
            System.out.println(entry.toString());
        }
        System.out.println("---------------\n");
    }
}

```


## 6. Integration and Demo 

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a