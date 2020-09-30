package com.robu.ConnectionDB;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAOIntf {
    void createTable(String string) throws SQLException;
    void insertData(ArrayList<String[]> arrayList) throws SQLException;
    //String showData();
    void deleteData();
}
