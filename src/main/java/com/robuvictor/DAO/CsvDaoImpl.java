package com.robuvictor.DAO;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CsvDaoImpl implements CsvDaoIntf {
    public ArrayList<String[]> goodData;
    public ArrayList<String[]> badData;

    private int received = 0;
    private int successful = 0;
    private int failed = 0;

    private static boolean checkIsValid(String[] str) {
        for (String s : str) {
            if ("".equals(s)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String[]> getGoodData() {
        return goodData;
    }

    public ArrayList<String[]> getBadData() {
        return badData;
    }

    @Override
    public void writeToCSV(ArrayList<String[]> data, File csvFile) throws IOException {
        FileWriter fw = new FileWriter(csvFile);
        BufferedWriter bw = new BufferedWriter(fw);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(new Date());

        bw.write("Date: " + dateString);
        bw.newLine();
        bw.write("A,B,C,D,E,F,G,H,I,J");
        bw.newLine();
        for (String[] s : data) {
            for (int i = 0; i < 10; i++) {
                bw.write(s[i]);
                if (i < 9) {
                    bw.write(",");
                }
            }
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    @Override
    public void readFromCSV(File csvFile) throws IOException {
        BufferedReader br = null;
        String line;

        goodData = new ArrayList<>();
        badData = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if ("".equals(line)) {
                    break;
                } else {
                    // use comma as separator
                    String[] value = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    ++received;
                    if (CsvDaoImpl.checkIsValid(value)) {
                        goodData.add(value);
                    } else {
                        badData.add(value);
                    }
                }
            }

            for (String[] row : goodData) {
                for (String data : row) {
                    System.out.println("goodData: " + data);
                }
            }

            for (int i = 0; i < goodData.size(); i++) {
                System.out.println("=================== goodData =========================");

            }

            goodData.remove(0);
            received--;
            successful = goodData.size();
            failed = badData.size();

            System.out.println("============== total =================");

            System.out.println();
            System.out.println("received = " + received);
            System.out.println("successful = " + successful);
            System.out.println("failed = " + failed);

            for (String[] t : badData) {
                System.out.println("================ badData ===================");
                for (String s : t) {
                    System.out.println("> " + s);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        File badDataFile = new File("/Users/user/TestSQLiteCSV/src/main/java/com/robuvictor/bad-data.csv");
        FileWriter fw = new FileWriter(badDataFile);
        BufferedWriter bw = new BufferedWriter(fw);
    }

    @Override
    public void writeToLogFile(String fileForLog) {
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {
            // This block configure the logger with handler and formatter
            fh = new FileHandler(fileForLog);
            logger.addHandler(fh);

            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info("Received rows: " + received);
            logger.info("Successful rows: " + successful);
            logger.info("Failed rows: " + failed);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
