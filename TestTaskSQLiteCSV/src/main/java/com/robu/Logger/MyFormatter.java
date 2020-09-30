package com.robu.Logger;

import com.robu.JavaFX.FXMLapp;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return "Date :: " + new Date(record.getMillis()) + "\n\n" +
                "==============================================================================\n\n" +
                "Statisics ::\n" +
                "Total received rows: " + FXMLapp.received + "\n" +
                "Successful rows: " + FXMLapp.goodData.size() + "\n" +
                "Failed rows: " + FXMLapp.badData.size() + "\n\n" +
                "==============================================================================\n\n" +
                "File \"bad-data.csv\" is stored at " + FXMLapp.badDataFile + "\n" +
                "File \"MyLogFile.log\" is stored at " + FXMLapp.logFile + "\n" +
                "File \"people.db\" is stored at /root-project/people.db";
    }
}
