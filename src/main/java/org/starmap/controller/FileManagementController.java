package org.starmap.controller;

import org.starmap.utils.DataLoader;
import org.starmap.utils.DataWriter;

public class FileManagementController {

    private final StarMapController starMapController;

    public FileManagementController(StarMapController starMapController) {

        this.starMapController = starMapController;
    }

    public void saveFile(String filePath) {
        DataWriter.saveData(filePath, starMapController.getStars(), starMapController.getConstellations());
    }

    public void loadFile(String filePath) {
        starMapController.setStars(DataLoader.loadStars(filePath));
        starMapController.setConstellations(DataLoader.loadConstellations(filePath, starMapController.getStars()));
    }


}
