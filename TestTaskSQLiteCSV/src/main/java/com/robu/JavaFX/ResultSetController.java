package com.robu.JavaFX;

import com.robu.CsvDAO.CsvDaoImpl;
import com.robu.ConnectionDB.DAOImpl;
import com.robu.ConnectionDB.DAOIntf;
import com.robu.ConnectionDB.DatabaseConnection;
import com.robu.ModelTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResultSetController implements Initializable {

    @FXML
    private TextArea textArea;

    @FXML
    private Text note2;

    @FXML
    private Text note3;

    @FXML
    private TableView<ModelTable> table;

    @FXML
    private TableColumn<ModelTable, String> col_id;

    @FXML
    private TableColumn<ModelTable, String> col_a;

    @FXML
    private TableColumn<ModelTable, String> col_b;

    @FXML
    private TableColumn<ModelTable, String> col_c;

    @FXML
    private TableColumn<ModelTable, String> col_d;

    @FXML
    private TableColumn<ModelTable, String> col_e;

    @FXML
    private TableColumn<ModelTable, String> col_f;

    @FXML
    private TableColumn<ModelTable, String> col_g;

    @FXML
    private TableColumn<ModelTable, String> col_h;

    @FXML
    private TableColumn<ModelTable, String> col_i;

    @FXML
    private TableColumn<ModelTable, String> col_j;

    @FXML
    private ComboBox<String> comboBox;

    final String EMPTY = "";
    final String SQLITE = "SQLite data";
    final String BAD_DATA_CSV = "bad-data.csv";
    final String MY_LOG_FILE = "MyLogFile.log";

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        note2.setText(FXMLapp.badDataFile.toString());
        note3.setText(FXMLapp.logFile);
        populateTable();
    }

    @FXML
    private void comboChange() throws IOException, SQLException {
        String option = comboBox.getValue();
        switch (option) {
            case SQLITE:
                showDataFromDb();
                break;
            case BAD_DATA_CSV:
                showBadDataFromCSV();
                break;
            case MY_LOG_FILE:
                showLogFile();
                break;
            case EMPTY:
                clearTextArea();
                break;
        }
    }

    @FXML
    private void goBack() throws IOException, SQLException {
        DAOIntf daoImpl = new DAOImpl();
        daoImpl.deleteData();
        Scene scene = IntroDataController.load();
        Stage stage = FXMLapp.parentWindow;
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showDataFromDb() throws SQLException {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM person;");
        while (!rs.next()) {
            textArea.setText("Table \"person\" from database\"people.db\" is empty!");
            textArea.setOpacity(1);
        }
        textArea.setOpacity(0);
        textArea.setText("");
        table.setVisible(true);
        table.setDisable(false);
        table.toFront();
        table.setOpacity(1);
        textArea.setDisable(true);
    }

    private void populateTable() {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM person;");

            while (rs.next()) {
                oblist.add(new ModelTable(rs.getString("id"), rs.getString("a"),
                        rs.getString("b"), rs.getString("c"), rs.getString("d"),
                        rs.getString("e"), rs.getString("f"), rs.getString("g"),
                        rs.getString("h"), rs.getString("i"), rs.getString("j")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_a.setCellValueFactory(new PropertyValueFactory<>("a"));
        col_b.setCellValueFactory(new PropertyValueFactory<>("b"));
        col_c.setCellValueFactory(new PropertyValueFactory<>("c"));
        col_d.setCellValueFactory(new PropertyValueFactory<>("d"));
        col_e.setCellValueFactory(new PropertyValueFactory<>("e"));
        col_f.setCellValueFactory(new PropertyValueFactory<>("f"));
        col_g.setCellValueFactory(new PropertyValueFactory<>("g"));
        col_h.setCellValueFactory(new PropertyValueFactory<>("h"));
        col_i.setCellValueFactory(new PropertyValueFactory<>("i"));
        col_j.setCellValueFactory(new PropertyValueFactory<>("j"));
        table.setItems(oblist);
        table.toFront();
        table.setDisable(false);
    }

    @FXML
    private void showBadDataFromCSV() throws IOException {
        table.setVisible(false);
        CsvDaoImpl csvDao = new CsvDaoImpl();
        ArrayList<String> arrayList = csvDao.readFromBadDataCSV(FXMLapp.badDataFile);
        StringBuilder sb = new StringBuilder("");
        for (String str : arrayList) {
            sb.append(str + "\n");
        }
        textArea.setText(sb.toString());
        textArea.setOpacity(1);
        textArea.toFront();
        textArea.setDisable(false);
        table.setVisible(true);
        table.setOpacity(0);
        table.setDisable(true);
    }

    @FXML
    private void showLogFile() {
        table.setVisible(false);
        table.setDisable(true);
        table.setOpacity(0);
        textArea.setVisible(true);
        textArea.setOpacity(1);
        textArea.toFront();
        textArea.setDisable(false);
        try{
            FileInputStream fis = new FileInputStream(FXMLapp.logFile);
            System.out.println("logFile: " + FXMLapp.logFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String strLine;
            StringBuilder sb = new StringBuilder("");
            while ((strLine = br.readLine()) != null)   {
                System.out.println (strLine);
                sb.append(strLine + ", \n");
            }
            textArea.setText(sb.toString());
            fis.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @FXML
    private void clearTextArea() {
        table.setVisible(false);
        textArea.setText("");
        comboBox.getSelectionModel().select(0);
    }
}
