package se.kth.iv1350.repairbike.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Ansvarar för att skriva felrapporter till en fil.
 */
public class FileLogger {
    private static final String LOG_FILE_NAME = "repair-bike-log.txt";
    private PrintWriter logFile;

    public FileLogger() {
        try {
            logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, true));
        } catch (IOException e) {
            System.out.println("Kunde inte skapa loggfil.");
            e.printStackTrace();
        }
    }

    /**
     * Loggar detaljer om ett undantag till filen.
     * @param exception Det undantag som ska loggas.
     */
    public void logException(Exception exception) {
        logFile.println(LocalDateTime.now() + " - Exception fångat:");
        exception.printStackTrace(logFile);
        logFile.println(); // Tom rad för tydlighet
        logFile.flush();
    }
}