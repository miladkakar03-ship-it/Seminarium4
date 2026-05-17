package se.kth.iv1350.repairbike.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.repairbike.model.Bike;
import se.kth.iv1350.repairbike.model.Customer;
import se.kth.iv1350.repairbike.model.RepairOrder;

/**
 * Simulerar lagring av alla reparationsordrar.
 */
public class RepairOrderRegistry {
    private List<RepairOrder> repairOrders = new ArrayList<>();

    // Systemoperation 3: Instansierar en ny order och sparar den i listan
    public RepairOrder createRepairOrder(Customer customer, Bike bike) {
        RepairOrder repairOrder = new RepairOrder(customer, bike);
        repairOrders.add(repairOrder);
        return repairOrder;
    }

    public RepairOrder getOrder(int orderId) {
        for (RepairOrder order : repairOrders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }
}