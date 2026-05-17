package se.kth.iv1350.repairbike.model;

/**
 * Ger 10% vinterrabatt på reparationens totalbelopp.
 */
public class WinterDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(RepairOrder order) {
        // Räknar ut 10% av summan av alla uppgifter före rabatt
        double rawTotal = 0;
        for (RepairTask task : order.getRepairTasks()) {
            rawTotal += task.getCost();
        }
        return rawTotal * 0.10;
    }
}