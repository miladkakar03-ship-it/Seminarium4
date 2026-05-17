package se.kth.iv1350.repairbike.model;

/**
 * Standardstrategi som inte ger någon rabatt alls.
 */
public class NoDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(RepairOrder order) {
        return 0.0;
    }
}