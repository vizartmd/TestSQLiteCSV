package com.robuvictor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ResultSetController {
    public Stage primaryStage3;

    @FXML
    private Button back;

    @FXML
    private void goBack() throws IOException {
        System.out.println("In goBack!!!");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:/Users/user/TestSQLiteCSV/src/main/java/com/robuvictor/IntroData.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = FXMLapplication.parentWindow;
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showSqlData() {
        System.out.println("showSqlData was clicked!!!");
    }

    @FXML
    private void showBadCsvData() {
        System.out.println("showBadCsvData was clicked!!!");
    }

    @FXML
    private void showLogFile() {
        System.out.println("showLogFile was clicked!!!");
    }

    @FXML
    private void clearTextArea() {
        System.out.println("clearTextArea was clicked!!!");
    }
}
