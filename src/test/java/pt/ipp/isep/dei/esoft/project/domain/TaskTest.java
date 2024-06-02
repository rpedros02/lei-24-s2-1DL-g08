package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class

TaskTest {

    @Test
    void ensureTaskIsCreatedSuccessfully() {
        Collaborator collaborator = new Collaborator(123456789);
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory);
    }

    @Test
    void ensureTaskReferenceIsNotNull() {
        //Arrange
        Collaborator collaborator = new Collaborator(123456789);
        TaskCategory taskCategory = new TaskCategory("Task Category Description");

        //Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Task(null, "description", "informal description", "technical description", 1, 1d,
                        taskCategory));
    }

    @Test
    void testEqualsSameObject() {
        Collaborator collaborator = new Collaborator(123456789);
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory);

        assertEquals(task, task);
    }

    @Test
    void testEqualsDifferentClass() {
        Collaborator collaborator = new Collaborator(123456789);
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory);

        assertNotEquals(task, new Object());
    }

    @Test
    void testEqualsNull() {
        Collaborator collaborator = new Collaborator(123456789);
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory);

        assertNotEquals(task, null);
    }

    @Test
    void testEqualsDifferentObject() {
        Collaborator collaborator = new Collaborator(123456789);
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory);
        Task task1 = new Task("reference1", "description1", "informal description1", "technical description1", 2, 2d,
                taskCategory);

        assertNotEquals(task, task1);
    }

    @Test
    void testEqualsSameObjectDifferentDescription() {
        Collaborator collaborator = new Collaborator(123456789);
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory);
        Task task1 = new Task("reference1", "description", "informal description1", "technical description1", 2, 2d,
                taskCategory);

        assertNotEquals(task, task1);
    }

    @Test
    void testEqualsSameObjectSameDescription() {
        Collaborator collaborator = new Collaborator(123456789);
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory);
        Task task1 = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory);

        assertEquals(task, task1);
    }

    @Test
    void testHashCodeSameObject() {
        Collaborator collaborator = new Collaborator(123456789);
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory);

        assertEquals(task.hashCode(), task.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Collaborator collaborator = new Collaborator(123456789);
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory);
        Task task1 = new Task("reference1", "description1", "informal description1", "technical description1", 2, 2d,
                taskCategory);

        assertNotEquals(task.hashCode(), task1.hashCode());
    }

    @Test
    void ensureCloneWorks() {
        Collaborator collaborator = new Collaborator(123456789);
        TaskCategory taskCategory = new TaskCategory("Task Category Description");
        Task task = new Task("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory);
        Task clone = task.clone();
        assertEquals(task, clone);
    }
}