package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

public class LoadDataUI implements Runnable {
    public LoadDataUI() {
    }

    @Override
    public void run() {
        System.out.println("Load Data");
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();
        return;
    }
}
