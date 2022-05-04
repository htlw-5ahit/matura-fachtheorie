package client.model;

import client.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception{
        this.stage = stage;
        ((MainController) loadViewRoutine("../view/main.fxml").getController()).setMainApp(this);
    }

    public FXMLLoader loadViewRoutine(String file) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(file));
        stage.setTitle("Database-Importer");
        stage.setScene(new Scene(loader.load()));
        stage.show();
        return loader;
    }

    public static void main(String[] args) {
        launch(args);
    }

}