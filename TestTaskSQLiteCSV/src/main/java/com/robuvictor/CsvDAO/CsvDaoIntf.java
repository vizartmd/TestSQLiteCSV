package com.robuvictor.CsvDAO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface CsvDaoIntf {
    void readFromCSV(File csvFile) throws IOException;
    void writeToCSV(ArrayList<String[]> data, File csvFile) throws IOException;
}
