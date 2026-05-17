package se.kth.iv1350.repairbike.view;

import se.kth.iv1350.repairbike.controller.Controller;
import se.kth.iv1350.repairbike.integration.CustomerNotFoundException;
import se.kth.iv1350.repairbike.model.CustomerDTO;
import se.kth.iv1350.repairbike.model.RepairOrderDTO;
import se.kth.iv1350.repairbike.model.RepairOrder;
import se.kth.iv1350.repairbike.model.OperationFailedException;

/**
 * Representerar applikationens presentationslager.
 * Simulerar användarinteraktioner och fångar arkitektoniskt säkra undantag.
 */
public class View {
    private Controller controller;

    /**
     * Skapar en ny instans av View och registrerar automatiskt dess observatörer i controllern.
     * * @param controller Applikationens centrala controller.
     */
    public View(Controller controller) {
        this.controller = controller;
        controller.addRepairOrderObserver(new RepairOrderView());
        controller.addRepairOrderObserver(new RepairOrderLogger());
    }

    /**
     * Startar och kör testschemat för de tre definierade scenarierna i användargränssnittet.
     */
    public void run() {
        System.out.println("TEST 1: Sökning på befintlig kund med Vinterrabatt");
        executeScenario("12345678", true);

        System.out.println("\nTEST 2: Sökning på kund som inte finns");
        executeScenario("00000000", false);

        System.out.println("\nTEST 3: Sökning som triggar databasfel");
        executeScenario("999", false);
    }

    private void executeScenario(String phoneNumber, boolean useDiscount) {
        try {
            CustomerDTO customerDTO = controller.findCustomer(phoneNumber);
            RepairOrderDTO orderDTO = controller.startNewRepair(customerDTO);
            
            controller.addProblemDescription("Cykeln tappar kraft.");
            controller.addDiagnosticReport("Batterikontakt trasig.");
            controller.addRepairTask("Byte av kontakt", 400.0);
            
            if (useDiscount) {
                System.out.println("[View]: Aktiverar vinterrabatt (10%)...");
                controller.applyWinterDiscount();
            }
            
            controller.confirmRepairOrder();
            printReceipt();

        } catch (CustomerNotFoundException e) {
            System.out.println("ANVÄNDARFEL: " + e.getMessage());
        } catch (OperationFailedException e) {
            System.out.println("SYSTEMFEL: Ett tekniskt fel uppstod. Försök igen senare.");
        }
    }

    private void printReceipt() {
        RepairOrder order = controller.getActiveOrder();
        if (order == null) return;
        
        System.out.println("=== SLUTGILTIGT KVITTO ===");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Kund: " + order.getCustomer().getName());
        System.out.println("Slutpris att betala: " + order.getTotalCost() + " kr");
        System.out.println("==========================");
    }
}