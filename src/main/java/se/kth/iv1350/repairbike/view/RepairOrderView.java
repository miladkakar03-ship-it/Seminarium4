package se.kth.iv1350.repairbike.view;

import se.kth.iv1350.repairbike.model.RepairOrderDTO;
import se.kth.iv1350.repairbike.model.RepairOrderObserver;
import se.kth.iv1350.repairbike.model.RepairTask;

/**
 * Visar information i realtid till tekniker och receptionister när en order uppdateras.
 */
public class RepairOrderView implements RepairOrderObserver {

    /**
     * Anropas automatiskt via Observer-mönstret när en order ändrats.
     * * @param orderDTO Det oföränderliga dataobjektet med orderinformation.
     */
    @Override
    public void orderUpdated(RepairOrderDTO orderDTO) {
        System.out.println("\n--- MEDDELANDE TILL TEKNIKER & RECEPTIONIST ---");
        System.out.println("Order ID " + orderDTO.getOrderId() + " har uppdaterats!");
        System.out.println("Status: " + orderDTO.getStatus());
        System.out.println("Kund: " + orderDTO.getCustomerName());
        System.out.println("Aktuella arbetsmoment:");
        for (RepairTask task : orderDTO.getRepairTasks()) {
            System.out.println("  - " + task.getDescription() + ": " + task.getCost() + " kr");
        }
        System.out.println("Totalbelopp (inkl. eventuell rabatt): " + orderDTO.getTotalCost() + " kr");
        System.out.println("-----------------------------------------------\n");
    }
}