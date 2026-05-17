package se.kth.iv1350.repairbike.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import se.kth.iv1350.repairbike.model.RepairOrderDTO;
import se.kth.iv1350.repairbike.model.RepairOrderObserver;

/**
 * Loggar orderändringar till en textfil i realtid via Observer-mönstret.
 */
public class RepairOrderLogger implements RepairOrderObserver {
    private static final String FILE_NAME = "repair-orders-log.txt";
    private PrintWriter logFile;

    /**
     * Skapar en ny instans av RepairOrderLogger och öppnar loggfilen.
     */
    public RepairOrderLogger() {
        try {
            logFile = new PrintWriter(new FileWriter(FILE_NAME, true));
        } catch (IOException e) {
            System.out.println("Kunde inte starta RepairOrderLogger: " + e.getMessage());
        }
    }

    /**
     * Loggar den uppdaterade orderns data till textfilen baserat på mottagen DTO.
     * * @param orderDTO Det oföränderliga dataobjektet med orderinformation.
     */
    @Override
    public void orderUpdated(RepairOrderDTO orderDTO) {
        if (logFile == null) return;
        logFile.println(LocalDateTime.now() + " - ORDERUPPDATERING");
        logFile.println("Order ID: " + orderDTO.getOrderId());
        logFile.println("Ny status: " + orderDTO.getStatus());
        logFile.println("Totalpris: " + orderDTO.getTotalCost() + " kr");
        logFile.println("------------------------------------------------");
        logFile.flush();
    }
}