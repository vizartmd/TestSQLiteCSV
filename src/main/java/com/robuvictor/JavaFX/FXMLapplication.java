package com.robuvictor.JavaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.io.File;

public class FXMLapplication extends Application {

    public static Stage parentWindow;
    public static File badDataFile;
    public static File csvFile;
    public static String logFile;
    public static int received;
    public static ArrayList<String[]> goodData;
    public static ArrayList<String[]> badData;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        Scene scene = IntroDataController.load();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Java application as a test task for the Java junior position (by Robu Victor)");
        primaryStage.show();
        parentWindow = primaryStage;
    }
}
