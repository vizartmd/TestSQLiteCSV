package com.robuvictor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

public class FXMLapplication extends Application {

    public static Stage parentWindow;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        parentWindow = primaryStage;

//        String csvFile = "/Users/user/TestSQLiteCSV/src/main/java/com/robuvictor/Interview-task-data-osh.csv";
//        File badDataFile = new File("/Users/user/TestSQLiteCSV/src/main/java/com/robuvictor/bad-data.csv");
//        File goodDataFile = new File("/Users/user/TestSQLiteCSV/src/main/java/com/robuvictor/good-data.csv");
//        String fileForLog = "/Users/user/TestSQLiteCSV/src/main/java/com/robuvictor/MyLogFile.log";
//        Class.forName("org.sqlite.JDBC");

//        CsvDaoIntf csvDaoIntf = new CsvDaoImpl();
//        csvDaoIntf.readFromCSV(new File(csvFile));
//        ArrayList<String[]> goodData = ((CsvDaoImpl) csvDaoIntf).getGoodData();
//        csvDaoIntf.writeToCSV(((CsvDaoImpl) csvDaoIntf).getBadData(), badDataFile);
//        csvDaoIntf.writeToCSV(goodData, goodDataFile);
//        csvDaoIntf.writeToLogFile(fileForLog);MyLogFile.log

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:/Users/user/TestSQLiteCSV/src/main/java/com/robuvictor/IntroData.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        parentWindow.setScene(scene);
        parentWindow.setTitle("Java application as a test task for the Java junior position in Optimal Solutions Hub SRL");
        parentWindow.setResizable(false);
        parentWindow.show();

    }
}
