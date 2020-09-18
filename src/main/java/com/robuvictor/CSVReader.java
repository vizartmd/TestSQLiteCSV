package com.robuvictor;

import java.io.*;
import java.util.ArrayList;

public class CSVReader {
    public static boolean checkIsValid(String[] str){
        for (String s : str){
            if ("".equals(s)){
                return false;
            }
        }
        return true;
    }

    public void readCSV() throws IOException {

        String csvFile = "/Users/user/TestSQLiteCSV/src/main/java/com/robuvictor/Interview-task-data-osh.csv";
        BufferedReader br = null;
        String line = "";
        int received = 0;
        int successful = 0;
        int failed = 0;
        ArrayList<String[]> goodData = new ArrayList<>();
        ArrayList<String[]> badData = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if ("".equals(line)) {
                    continue;
                } else {
                    // use comma as separator
                    String[] value = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    ++received;
                    if (CSVReader.checkIsValid(value)){
                        System.out.println("A: " + value[0] + " , B: " + value[1] + " , C: " + value[2]
                                + " , D: " + value[3] + " , E: " + value[4]
                                + " , F: " + value[5] + " , G: " + value[6]
                                + " , H: " + value[7] + " , I: " + value[8] + " , J: " + value[9]);
                        goodData.add(value);
                    } else {
                        badData.add(value);
                        continue;
                    }
                }
            }

            successful = goodData.size();
            failed = badData.size();

            System.out.println();
            System.out.println("received = " + received);
            System.out.println("successful = " + successful);
            System.out.println("failed = " + failed);

            System.out.println("============================================");

            for(String[] t : badData) {
                for (String s: t){
                    System.out.println("> "+ s);
                }
                System.out.println("============================================");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

        bw.write("A,B,C,D,E,F,G,H,I,J");
        bw.newLine();
        for (String[] s : badData) {
            for(int i = 0; i < 10; i++){
                bw.write(s[i]);
                if (i < 9){
                    bw.write(",");
                }
            }
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
}
