package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpaceController;

public class ListGreenSpacesUI implements Runnable {
    private final GreenSpaceController controller;

    public ListGreenSpacesUI() {
        this.controller = new GreenSpaceController();
    }

    @Override
    public void run() {
        System.out.println("Green Spaces:");
        controller.getAllGreenSpaces().forEach(System.out::println);
        System.out.println("--------------------");
    }
}
