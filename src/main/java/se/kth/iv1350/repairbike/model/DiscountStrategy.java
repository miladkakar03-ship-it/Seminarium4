package se.kth.iv1350.repairbike.model;

/**
 * Strategigränssnitt för beräkning av kundrabatter.
 */
public interface DiscountStrategy {
    /**
     * Beräknar rabatten för en specifik order.
     * @param order Ordern som rabatten ska baseras på.
     * @return Rabattbeloppet i kronor.
     */
    double calculateDiscount(RepairOrder order);
}