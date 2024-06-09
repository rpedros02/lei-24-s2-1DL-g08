package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * This class represents a menu item in the user interface.
 * Each menu item has a description and a Runnable UI object associated with it.
 */
public class MenuItem {
    // Description of the menu item
    private final String description;
    // Runnable UI object associated with the menu item
    private final Runnable ui;

    /**
     * Constructs a new MenuItem with the given description and Runnable UI object.
     * @param description the description of the menu item
     * @param ui the Runnable UI object associated with the menu item
     * @throws IllegalArgumentException if the description is null or empty, or if the Runnable UI object is null
     */
    public MenuItem(String description, Runnable ui) {
        if (StringUtils.isBlank(description)) {
            throw new IllegalArgumentException("MenuItem description cannot be null or empty.");
        }
        if (Objects.isNull(ui)) {
            throw new IllegalArgumentException("MenuItem does not support a null UI.");
        }

        this.description = description;
        this.ui = ui;
    }

    /**
     * Runs the Runnable UI object associated with this menu item.
     */
    public void run() {
        this.ui.run();
    }

    /**
     * Checks if this menu item has the given description.
     * @param description the description to check
     * @return true if this menu item has the given description, false otherwise
     */
    public boolean hasDescription(String description) {
        return this.description.equals(description);
    }

    /**
     * Returns the description of this menu item.
     * @return the description of this menu item
     */
    public String toString() {
        return this.description;
    }
}