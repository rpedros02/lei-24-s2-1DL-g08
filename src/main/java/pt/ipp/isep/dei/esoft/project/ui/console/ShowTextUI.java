package pt.ipp.isep.dei.esoft.project.ui.console;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
/**
 * This class provides a user interface for displaying a text message.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class ShowTextUI implements Runnable {

    // The text message to be displayed
    private String text;

    /**
     * Constructs a new instance of ShowTextUI.
     * @param text the text message to be displayed
     * @throws IllegalArgumentException if the text is null or empty
     */
    public ShowTextUI(String text) {
        if (StringUtils.isBlank(text))
            throw new IllegalArgumentException("ShowTextUI does not support null or empty text");

        this.text = text;
    }

    /**
     * Starts the user interface for displaying the text message.
     * It prints the text message to the console.
     */
    public void run() {
        System.out.println("\n");
        System.out.println(this.text);
        System.out.println("\n");
    }
}