package com.robuvictor.DAO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface CsvDaoIntf {
    void writeToCSV(ArrayList<String[]> data, File csvFile) throws IOException;

    void readFromCSV(File csvFile) throws IOException;

    void writeToLogFile(String fileForLog);
}
