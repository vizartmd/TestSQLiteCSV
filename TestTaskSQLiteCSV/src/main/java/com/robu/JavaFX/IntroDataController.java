package com.robu.JavaFX;

import com.robu.CsvDAO.CsvDaoImpl;
import com.robu.CsvDAO.CsvDaoIntf;
import com.robu.ConnectionDB.DAOImpl;
import com.robu.ConnectionDB.DAOIntf;
import com.robu.Logger.MyFormatter;
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

import java.io.*;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IntroDataController {

    private File badDataFile;

    @FXML
    private Label labelTitle;

    @FXML
    private Label labelBadCsv;

    @FXML
    private Label labelLog;

    @FXML
    private TextField textFieldCsv;

    @FXML
    public  TextField textFieldBadCsv;

    @FXML
    public  TextField textFieldMyLogFile;

    @FXML
    private Button choseCsvFile;

    @FXML
    private Button choseBadFileDirectory;

    @FXML
    private Button choseLogFileDirectory;

    @FXML
    private Button goProcessing;

    @FXML
    private Button reset;

    @FXML
    public void choseFileCSV() {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        File csvFile = fileChooser.showOpenDialog(stage);
        if (csvFile != null) {
            String fileName = FilenameUtils.getName(csvFile.toString());
            FXMLapp.csvFile = csvFile;
            // get the file extension
            String ext = FilenameUtils.getExtension(csvFile.toString());

            if ("csv".equals(ext)) {
                labelBadCsv.setText("Select the directory for writing \"bad-data.csv\" after parsing \"" + fileName + "\"");
                labelBadCsv.setTextFill(Color.web("red"));
                labelBadCsv.setVisible(true);
                labelTitle.setText("Seelcted CSV file: \"" + fileName + "\"");
                labelTitle.setTextFill(Color.web("black"));
                textFieldCsv.setText(csvFile.toString());
                textFieldCsv.setEditable(false);
                textFieldBadCsv.setDisable(false);
                textFieldCsv.setDisable(true);
                choseCsvFile.setDisable(true);
                choseBadFileDirectory.setDisable(false);
                reset.setDisable(false);
            } else {
                labelTitle.setText("Please select valid CSV file!");
                labelTitle.setTextFill(Color.web("red"));
            }
        } else {
            labelTitle.setText("Please select valid CSV file!");
            labelTitle.setTextFill(Color.web("red"));
        }
    }

    static Scene load() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:/Users/user/TestTaskSQLiteCSV/TestTaskSQLiteCSV/src/main/java/com/robu/JavaFX/IntroData.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        return scene;
    }

    @FXML
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
        badDataFile = choseDirectory(textFieldBadCsv, "/bad-data.csv");
        if (badDataFile != null) {
            FXMLapp.badDataFile = new File(badDataFile + "/bad-data.csv");
            labelBadCsv.setText("Directory for \"bad-data.csv\"");
            labelBadCsv.setTextFill(Color.web("black"));
            labelLog.setVisible(true);
            labelLog.setText("Select the directory for writing statistics in \"MyLogFile.log\"");
            labelLog.setTextFill(Color.web("red"));
            choseBadFileDirectory.setDisable(true);
            choseLogFileDirectory.setDisable(false);
            textFieldBadCsv.setDisable(true);
            textFieldMyLogFile.setDisable(false);
        }
    }

    @FXML
    private void choseDirectoryForMyLogFile() {
        File fileForLog = choseDirectory(textFieldMyLogFile, "/MyLogFile.log");
        if (fileForLog != null) {
            FXMLapp.logFile = (fileForLog + "/MyLogFile.log");
            labelLog.setText("Directory for \"MyLogFile.log\"");
            labelLog.setTextFill(Color.web("black"));
            labelLog.setVisible(true);
            choseLogFileDirectory.setDisable(true);
            textFieldMyLogFile.setDisable(true);
            goProcessing.setDisable(false);
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
    private void goToResultSet() throws Exception {
        DAOIntf daoImpl = new DAOImpl();
        daoImpl.createTable("person");
        daoImpl.deleteData();
        CsvDaoIntf csvDaoImpl = new CsvDaoImpl();
        csvDaoImpl.readFromCSV(FXMLapp.csvFile);
        csvDaoImpl.writeToCSV(FXMLapp.badData, FXMLapp.badDataFile);
        daoImpl.insertData(FXMLapp.goodData);
        Logger logger = Logger.getLogger(IntroDataController.class.getName());
        FileHandler fh;
        fh = new FileHandler(FXMLapp.logFile);
        fh.setFormatter(new MyFormatter());
        logger.addHandler(fh);
        logger.log(Level.INFO, "Message: ");
        textFieldBadCsv.setDisable(true);
        textFieldMyLogFile.setDisable(true);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:/Users/user/TestTaskSQLiteCSV/TestTaskSQLiteCSV/src/main/java/com/robu/JavaFX/ResultSet.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage stage = FXMLapp.parentWindow;
        stage.setResizable(false);
        double width = 1366;
        double height = 768;
        final Scene scene = new Scene( anchorPane, width, height);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
