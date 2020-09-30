package com.robuvictor.Logger;

import com.robuvictor.JavaFX.FXMLapplication;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return "Date :: " + new Date(record.getMillis()) + "\n\n" +
                "==============================================================================\n\n" +
                "Statisics ::\n" +
                "Total received rows: " + FXMLapplication.received + "\n" +
                "Successful rows: " + FXMLapplication.goodData.size() + "\n" +
                "Failed rows: " + FXMLapplication.badData.size() + "\n\n" +
                "==============================================================================\n\n" +
                "File \"bad-data.csv\" is stored at " + FXMLapplication.badDataFile + "\n" +
                "File \"MyLogFile.log\" is stored at " + FXMLapplication.logFile + "\n" +
                "File \"people.db\" is stored at /root-project/people.db";
    }
}
