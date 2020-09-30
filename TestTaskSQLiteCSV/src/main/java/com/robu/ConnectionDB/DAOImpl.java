package com.robu.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;

public class DAOImpl implements DAOIntf {
    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    Connection conn = databaseConnection.getConnection();

    public DAOImpl() throws SQLException {
    }

    @Override
    public void createTable(String string){
        String sql = "CREATE TABLE IF NOT EXISTS person (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	a text NOT NULL,\n"
                + "	b text NOT NULL,\n"
                + "	c text NOT NULL,\n"
                + "	d text NOT NULL,\n"
                + "	e text NOT NULL,\n"
                + "	f text NOT NULL,\n"
                + "	g text NOT NULL,\n"
                + "	h text NOT NULL,\n"
                + "	i text NOT NULL,\n"
                + "	j text NOT NULL\n"
                + ");";

        try (Statement statement = conn.createStatement()) {
            // create a new table
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insertData(ArrayList<String[]> arrayList) throws SQLException {
        String sql = "INSERT INTO person(id, a, b, c, d, e, f, g, h, i, j) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int index = 1;
        for (String[] str : arrayList) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    try {
                        pstmt.setInt(1, index);
                        pstmt.setString(2, str[0]);
                        pstmt.setString(3, str[1]);
                        pstmt.setString(4, str[2]);
                        pstmt.setString(5, str[3]);
                        pstmt.setString(6, str[4]);
                        pstmt.setString(7, str[5]);
                        pstmt.setString(8, str[6]);
                        pstmt.setString(9, str[7]);
                        pstmt.setString(10, str[8]);
                        pstmt.setString(11, str[9]);
                        pstmt.executeUpdate();
                        index++;
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
            }
        }
        index++;
    }

    @Override
    public void deleteData() {
        String sql = "DELETE FROM person";

        try (Statement statement = conn.createStatement()) {
            // delete table person
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
