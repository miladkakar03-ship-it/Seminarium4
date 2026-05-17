package se.kth.iv1350.repairbike.model;

import java.util.ArrayList;
import java.util.List;

/**
 * En oföränderlig databärare (DTO) för en reparationsorder.
 * Används för att skicka orderdata till presentationslagret utan att exponera domänobjekt.
 */
public class RepairOrderDTO {
    private final int orderId;
    private final String status;
    private final String customerName;
    private final double totalCost;
    private final List<RepairTask> repairTasks;

    /**
     * Skapar en ny RepairOrderDTO genom att kopiera relevant data från en RepairOrder.
     * * @param order Den RepairOrder-instans som data ska hämtas från.
     */
    public RepairOrderDTO(RepairOrder order) {
        this.orderId = order.getOrderId();
        this.status = order.getState();
        this.customerName = (order.getCustomer() != null) ? order.getCustomer().getName() : "Okänd";
        this.totalCost = order.getTotalCost();
        this.repairTasks = new ArrayList<>(order.getRepairTasks());
    }

    /**
     * @return Orderns unika ID.
     */
    public int getOrderId() { return orderId; }

    /**
     * @return Orderns nuvarande status.
     */
    public String getStatus() { return status; }

    /**
     * @return Kundens namn kopplat till ordern.
     */
    public String getCustomerName() { return customerName; }

    /**
     * @return Den totala kostnaden för ordern i kronor.
     */
    public double getTotalCost() { return totalCost; }

    /**
     * @return En kopierad lista med orderns tillhörande arbetsmoment.
     */
    public List<RepairTask> getRepairTasks() { return repairTasks; }
}