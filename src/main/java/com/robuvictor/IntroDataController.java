package com.robuvictor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.net.URL;

public class IntroDataController {
    @FXML
    private AnchorPane rootPane2;

    @FXML
    private Label title;

    @FXML
    private Label labelTitle;

    @FXML
    private Label labelBadCsv;

    @FXML
    private Label labelLog;

    @FXML
    private TextField textFieldCsv;

    @FXML
    private TextField textFieldBadCsv;

    @FXML
    private TextField textFieldMyLogFile;

    @FXML
    private Button choseCsvFile;

    @FXML
    private Button choseBadFileDirectory;

    @FXML
    private Button choseLogFileDirectory;

    @FXML
    private Button goProcessing;

    @FXML
    private void choseFileCSV() {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            String fileName = FilenameUtils.getName(selectedFile.toString());
            // get the file extension
            String ext = FilenameUtils.getExtension(selectedFile.toString());

            if ("csv".equals(ext)) {
                labelBadCsv.setText("Select the directory for writing \"bad-data.csv\" after parsing \"" + fileName + "\"");
                labelBadCsv.setTextFill(Color.web("red"));
                labelBadCsv.setVisible(true);
                labelTitle.setText("Seelcted CSV file: \"" + fileName + "\"");
                labelTitle.setTextFill(Color.web("black"));
                textFieldCsv.setText(selectedFile.toString());
                textFieldCsv.setEditable(false);
                textFieldBadCsv.setDisable(false);
                textFieldCsv.setDisable(true);
                choseCsvFile.setDisable(true);
                choseBadFileDirectory.setDisable(false);
            } else {
                labelTitle.setText("Please select valid CSV file!");
                labelTitle.setTextFill(Color.web("red"));
            }
        } else {
            return;
        }

    }

    private File choseDirectory(TextField textField, String file) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = new Stage();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            textField.setText(selectedDirectory.getAbsolutePath() + file);
            textField.setEditable(false);
            textField.setDisable(true);
            return selectedDirectory;
        } else {
            return null;
        }
    }

    @FXML
    private void choseDirectoryForBadDataCSV() {
        File directory = choseDirectory(textFieldBadCsv, "/bad-data.csv");
        if (directory != null) {
            labelBadCsv.setText("Directory for \"bad-data.csv\"");
            labelBadCsv.setTextFill(Color.web("black"));
            labelLog.setVisible(true);
            labelLog.setText("Select the directory for writing statistics in \"MyLogFile.log\"");
            labelLog.setTextFill(Color.web("red"));
            choseBadFileDirectory.setDisable(true);
            choseLogFileDirectory.setDisable(false);
            textFieldBadCsv.setDisable(true);
            textFieldMyLogFile.setDisable(false);
        } else {
            return;
        }
    }

    @FXML
    private void choseDirectoryForMyLogFile() {
        File directory = choseDirectory(textFieldMyLogFile, "/MyLogFile.log");
        if (directory != null) {
            labelLog.setText("Directory for \"MyLogFile.log\"");
            labelLog.setTextFill(Color.web("black"));
            labelLog.setVisible(true);
            choseLogFileDirectory.setDisable(true);
            goProcessing.setDisable(false);
            textFieldMyLogFile.setDisable(true);
        } else {
            return;
        }
    }

    @FXML
    public void resetAll() {
        System.out.println("In Reset!!!");
        labelTitle.setText("Select CSV file with 10 column - \"A,B,C,D,E,F,G,H,I,J\" for parsing");
        labelTitle.setTextFill(Color.web("red"));
        choseCsvFile.setDisable(false);
        choseBadFileDirectory.setDisable(true);
        choseLogFileDirectory.setDisable(true);
        goProcessing.setDisable(true);
        textFieldCsv.setText("");
        textFieldCsv.setDisable(false);
        textFieldBadCsv.setText("");
        textFieldBadCsv.setDisable(true);
        textFieldMyLogFile.setText("");
        textFieldMyLogFile.setDisable(true);
        labelBadCsv.setVisible(false);
        labelLog.setVisible(false);
    }

    @FXML
    private void goAway() throws Exception {
        textFieldBadCsv.setDisable(true);
        textFieldMyLogFile.setDisable(true);
        System.out.println("In Run!!!");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:/Users/user/TestSQLiteCSV/src/main/java/com/robuvictor/ResultSet.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = FXMLapplication.parentWindow;
        stage.setScene(scene);
        stage.show();
    }
}
