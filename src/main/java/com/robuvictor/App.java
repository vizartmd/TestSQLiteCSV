package com.robuvictor;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
        public static void main(String[] args) throws ClassNotFoundException, IOException {
            String csvFile = "/Users/user/TestSQLiteCSV/src/main/java/com/robuvictor/Interview-task-data-osh.csv";
            File badDataFile = new File("/Users/user/TestSQLiteCSV/src/main/java/com/robuvictor/bad-data.csv");
            File goodDataFile = new File("/Users/user/TestSQLiteCSV/src/main/java/com/robuvictor/good-data.csv");


            CSVReader csvReader = new CSVReader();
            csvReader.readCSV(new File(csvFile));
            ArrayList<String[]> goodData = csvReader.goodData;
            csvReader.writeToLogFile();
            csvReader.writeToCSV(csvReader.badData, badDataFile);
            csvReader.writeToCSV(goodData, goodDataFile);

            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");

            Connection connection = null;
            try
            {
                // create a database connection
                connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);  // set timeout to 30 sec.

                statement.executeUpdate("drop table if exists person");
                statement.executeUpdate("create table person (id integer, name string)");
                statement.executeUpdate("insert into person values(1, 'Bill')");
                statement.executeUpdate("insert into person values(2, 'Gregor')");
                ResultSet rs = statement.executeQuery("select * from person");
                while(rs.next())
                {
                    // read the result set
                    System.out.println("name = " + rs.getString("name"));
                    System.out.println("id = " + rs.getInt("id"));
                }
            }
            catch(SQLException e)
            {
                // if the error message is "out of memory",
                // it probably means no database file is found
                System.err.println(e.getMessage());
            }
            finally
            {
                try
                {
                    if(connection != null)
                        connection.close();
                }
                catch(SQLException e)
                {
                    // connection close failed.
                    System.err.println(e);
                }
            }
        }
}
