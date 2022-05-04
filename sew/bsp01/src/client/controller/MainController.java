package client.controller;

import client.model.MainApp;
import client.model.concurrency.DatabaseService;
import client.model.exporter.DatabaseExporter;
import client.model.exporter.NetworkExporter;
import client.model.importer.CSVImporter;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private MainApp app;
    private File csvFile;
    private DatabaseService service;

    @FXML private Button selectFileButton, startButton;
    @FXML private Label selectFileLabel, statusLabel;
    @FXML private TextField serverAddressField;
    @FXML private TextArea exportTextArea;
    @FXML private RadioButton localDatabaseButton, serverDatabaseButton;
    private FileChooser importFileChooser;
    private ToggleGroup databaseToggleGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // file chooser
        importFileChooser = new FileChooser();
        importFileChooser.setTitle("Select CSV File");
        importFileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV File", "*.csv")
        );

        // toggle group of database source buttons
        databaseToggleGroup = new ToggleGroup();
        localDatabaseButton.setToggleGroup(databaseToggleGroup);
        serverDatabaseButton.setToggleGroup(databaseToggleGroup);

        // bindings
        startButton.disableProperty().bind(selectFileLabel.textProperty().greaterThan("").not()); //TODO toggle group
        serverAddressField.visibleProperty().bind(serverDatabaseButton.selectedProperty());

        // database service
        service = new DatabaseService();

        service.setOnSucceeded((WorkerStateEvent event) -> {
            statusLabel.setText("");
            StringBuilder s = new StringBuilder();
            for (Object lesson : service.getValue()) s.append(lesson.toString()).append("\n");
            exportTextArea.setText(s.toString());
        });

        service.setOnFailed((WorkerStateEvent event) -> {
            statusLabel.setText("Ein Fehler wurde festgestellt.");
        });
    }

    public void setMainApp(MainApp app) {
        this.app = app;
    }

    @FXML void onFileButtonPressed(ActionEvent event) {
        csvFile = importFileChooser.showOpenDialog(null);
        selectFileLabel.setText(csvFile.getName());
    }

    @FXML void startButtonPressed(ActionEvent event) {
        if (csvFile.exists()) {
            // set file importer (static)
            service.setImporter(new CSVImporter(csvFile));
            service.reset();

            if (databaseToggleGroup.getSelectedToggle() == localDatabaseButton) {
                service.setExporter(new DatabaseExporter("lessons.properties"));
                service.start();

            } else if (databaseToggleGroup.getSelectedToggle() == serverDatabaseButton) {
                if (!serverAddressField.getText().equals("")) { // field contains text (hopefully ip address or hostname)
                    String[] server = serverAddressField.getText().split(":");
                    service.setExporter(new NetworkExporter(server[0], Integer.parseInt(server[1])));
                    service.start();

                } else
                    statusLabel.setText("Bitte gebe den Server-Host an!");
            } else
                statusLabel.setText("Bitte wähle ein Ziel-System aus!");
        } else
            statusLabel.setText("CSV Datei konnte nicht gefunden werden! Bitte wähle erneut eine CSV-Datei aus.");
    }
}