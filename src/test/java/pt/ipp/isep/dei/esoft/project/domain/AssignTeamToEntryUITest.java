package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;

import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.AssignTeamToEntryUI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.PrintStream;
import java.lang.reflect.Field;


import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssignTeamToEntryUITest {

    private AssignTeamToEntryUI ui;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        ui = new AssignTeamToEntryUI();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testRun_EntryNotFound() throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("NonExistentEntry\nTeamName\n".getBytes());
        System.setIn(inputStream);
        AgendaController agendaController = new AgendaController();
        ToDoListController toDoListController = new ToDoListController();
        ui = new AssignTeamToEntryUI(toDoListController, agendaController);
        ui.run();
        assertTrue(outputStream.toString().contains("Entry not found in the Agenda."));
    }

    @Test
    public void testRun_TeamNotFound() throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("ExistingEntry\nNonExistentTeam\n".getBytes());
        System.setIn(inputStream);
        AgendaController agendaController = new AgendaController();
        ToDoListController toDoListController = new ToDoListController();
        ui = new AssignTeamToEntryUI(toDoListController, agendaController);
        ui.run();
        assertTrue(outputStream.toString().contains("Team not found."));
    }

    @Test
    public void testRun_EntryAssignedSuccessfully() throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("ExistingEntry\nExistingTeam\n".getBytes());
        System.setIn(inputStream);
        AgendaController agendaController = new AgendaController();
        ToDoListController toDoListController = new ToDoListController();
        ui = new AssignTeamToEntryUI(toDoListController, agendaController);
        ui.run();
        assertTrue(outputStream.toString().contains("Team assigned to the entry successfully."));
    }

    @Test
    public void testGetToDoListController() throws Exception {
        Field field = AssignTeamToEntryUI.class.getDeclaredField("toDoListController");
        field.setAccessible(true);
        assertInstanceOf(ToDoListController.class, field.get(ui));
    }

    @Test
    public void testGetAgendaController() throws Exception {
        Field field = AssignTeamToEntryUI.class.getDeclaredField("agendaController");
        field.setAccessible(true);
        assertInstanceOf(AgendaController.class, field.get(ui));
    }
}
