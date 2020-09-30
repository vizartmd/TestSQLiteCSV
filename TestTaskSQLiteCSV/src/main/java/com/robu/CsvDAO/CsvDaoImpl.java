package com.robu.CsvDAO;

import com.robu.JavaFX.FXMLapp;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CsvDaoImpl implements CsvDaoIntf {
    private int received = 0;
    private int successful = 0;
    private int failed = 0;
    private ArrayList<String[]> goodData;
    private ArrayList<String[]> badData;

    private static boolean checkIsValid(String[] str) {
        for (String s : str) {
            if ("".equals(s)) {
                return false;
            }
        }
        return true;
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
                    String[] item = new String[10];
                    if (CsvDaoImpl.checkIsValid(value)) {
                        for (int i = 0; i < 10; i++) {
                            item[i] = value[i];
                        }
                        goodData.add(item);
                    } else {
                        badData.add(value);
                    }
                }
            }
            goodData.remove(0);
            received--;
            successful = goodData.size();
            failed = badData.size();
            FXMLapp.received = received;
            FXMLapp.goodData = goodData;
            FXMLapp.badData = badData;
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
    }

    public ArrayList<String> readFromBadDataCSV(File csvFile) throws IOException {
        BufferedReader br = null;
        String line;
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                arrayList.add(line);
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
        return arrayList;
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
}
