package org.starmap.view;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.starmap.controller.FileManagementController;

import java.io.File;

public class FileManagementView {

    private final FileManagementController controller;

    public FileManagementView(FileManagementController controller) {
        this.controller = controller;
    }

    public void openFile(Stage stage) {
        FileChooser fileChooser = createFileChooser("Open File");
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            controller.loadFile(selectedFile.getPath());
        }
    }

    public void saveToFile(Stage stage) {
        FileChooser fileChooser = createFileChooser("Save File");
        File selectedFile = fileChooser.showSaveDialog(stage);

        if (selectedFile != null) {
            controller.saveFile(selectedFile.getPath());
        }
    }

    private FileChooser createFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File JSON", "*.json"));
        return fileChooser;
    }
}
