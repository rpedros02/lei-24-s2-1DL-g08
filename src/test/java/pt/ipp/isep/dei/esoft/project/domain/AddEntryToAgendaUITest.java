package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.AddEntryToAgendaUI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AddEntryToAgendaUITest {

    private AddEntryToAgendaUI ui;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        ui = new AddEntryToAgendaUI();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testRun() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("Entry Title\n5\n".getBytes());
        System.setIn(inputStream);
        try {
            ui.run();
            String expectedOutput = "Enter the title of the entry to add to the agenda:\n" + "Entry added to the Agenda successfully.\n" + "Enter the number of members for the team:\n" + "Team assigned to the entry successfully.\n";assertEquals(expectedOutput, outputStream.toString());
        } finally {System.setIn(System.in);}
    }


    @Test
    public void testGetToDoListController() {
        assertNotNull(ui.getToDoListController());
    }

    @Test
    public void testGetAgendaController() {
        assertNotNull(ui.getAgendaController());
    }

    @Test
    public void testGetGenerateTeamController() {
        assertNotNull(ui.getGenerateTeamController());
    }

    @Test
    public void testGetScanner() {
        assertInstanceOf(Scanner.class, ui.getScanner());
    }
}

