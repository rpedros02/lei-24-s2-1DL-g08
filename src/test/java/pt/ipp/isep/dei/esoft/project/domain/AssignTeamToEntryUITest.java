package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;

import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.AssignTeamToEntryUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.EmailService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.PrintStream;
import java.lang.reflect.Field;


import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Test class for AssignTeamToEntryUI.
 */
public class AssignTeamToEntryUITest {

    private AssignTeamToEntryUI ui;
    private ByteArrayOutputStream outputStream;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        ui = new AssignTeamToEntryUI();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Tests the behavior when the entry is not found.
     *
     * @throws Exception if an error occurs.
     */
    @Test
    public void testRun_EntryNotFound() throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("NonExistentEntry\nTeamName\n".getBytes());
        System.setIn(inputStream);
        AgendaController agendaController = new AgendaController();
        ToDoListController toDoListController = new ToDoListController();
        EmailService emailService = mock(EmailService.class);
        ui = new AssignTeamToEntryUI(toDoListController, agendaController, emailService);
        ui.run();
        assertTrue(outputStream.toString().contains("Entry not found in the Agenda."));
    }


    /**
     * Tests the behavior when the team is not found.
     *
     * @throws Exception if an error occurs.
     */
    @Test
    public void testRun_TeamNotFound() throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("ExistingEntry\nNonExistentTeam\n".getBytes());
        System.setIn(inputStream);
        AgendaController agendaController = new AgendaController();
        ToDoListController toDoListController = new ToDoListController();
        EmailService emailService = mock(EmailService.class);
        ui = new AssignTeamToEntryUI(toDoListController, agendaController, emailService);
        ui.run();
        assertTrue(outputStream.toString().contains("Team not found."));
    }

    /**
     * Tests the behavior when the entry is assigned successfully.
     *
     * @throws Exception if an error occurs.
     */
    @Test
    public void testRun_EntryAssignedSuccessfully() throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("ExistingEntry\nExistingTeam\n".getBytes());
        System.setIn(inputStream);
        AgendaController agendaController = new AgendaController();
        ToDoListController toDoListController = new ToDoListController();
        EmailService emailService = mock(EmailService.class);
        ui = new AssignTeamToEntryUI(toDoListController, agendaController, emailService);
        ui.run();
        assertTrue(outputStream.toString().contains("Team assigned to the entry successfully."));
    }
    /**
     * Tests the retrieval of the ToDoListController.
     *
     * @throws Exception if an error occurs.
     */
    @Test
    public void testGetToDoListController() throws Exception {
        Field field = AssignTeamToEntryUI.class.getDeclaredField("toDoListController");
        field.setAccessible(true);
        assertInstanceOf(ToDoListController.class, field.get(ui));
    }

    /**
     * Tests the retrieval of the AgendaController.
     *
     * @throws Exception if an error occurs.
     */
    @Test
    public void testGetAgendaController() throws Exception {
        Field field = AssignTeamToEntryUI.class.getDeclaredField("agendaController");
        field.setAccessible(true);
        assertInstanceOf(AgendaController.class, field.get(ui));
    }
}
