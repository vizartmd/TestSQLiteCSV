Application for parsing CSV file, writing good data in SQLite database, failed data in CSV file
and statistics about the total number of rows received, complete rows and incomplete rows in the log file.

I downloaded SQLite from https://www.sqlite.org/download.html.
I created the SQLite In-Memory database "people.db".

First I tried to analyze the CSV file and see the information in the file in the console.
I noticed that sometimes the spaces between the commas are empty.
I filtered and separated complete (successful) rows from incomplete (failed) rows and grouped them into
two arrays: "ArrayList <String[]> goodData" and "ArrayList <String[]> badData" where an element in "String[]"
represents a row in the input CSV file.

Then I wrote the good data in the table "person" in "people.db" and "bad-data" in "bad-data<timestamp>.csv".
In this way I obtained the number of all rows received, the size of the good data and the size of the bad data.
I wrote these statistics in "MyLogFile.log"

I used JavaFX technology to develop the user interface of this application.

Here is the github link to the files in the TestSQLiteCSV project:
https://github.com/vizartmd/TestSQLiteCSV/tree/master/TestTaskSQLiteCSV

Download TestSQLiteCSV.jar from email. Run and test application.
