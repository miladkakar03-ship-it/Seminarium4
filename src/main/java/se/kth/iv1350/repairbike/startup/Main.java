package se.kth.iv1350.repairbike.startup;

import se.kth.iv1350.repairbike.integration.CustomerRegistry;
import se.kth.iv1350.repairbike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairbike.controller.Controller;
import se.kth.iv1350.repairbike.view.View;

public class Main {
    public static void main(String[] args) {
        // Task 2b: Hämta den unika instansen via Singleton-mönstret
        CustomerRegistry customerRegistry = CustomerRegistry.getInstance();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        
        Controller controller = new Controller(customerRegistry, repairOrderRegistry);
        
        View view = new View(controller);
        view.run();
    }
}