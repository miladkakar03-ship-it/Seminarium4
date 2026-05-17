package se.kth.iv1350.repairbike.model;

/**
 * Representerar ett specifikt jobb som utförs på cykeln, t.ex. "Byte av däck".
 */
public class RepairTask {
    private String description;
    private double cost;

    /**
     * Skapar ett nytt arbetsmoment.
     * @param description Beskrivning av vad som utförts.
     * @param cost Kostnaden för momentet.
     */
    public RepairTask(String description, double cost) {
        this.description = description;
        this.cost = cost;
    }

    /** @return Beskrivning av arbetet. */
    public String getDescription() { return description; }

    /** @return Vad momentet kostar. */
    public double getCost() { return cost; }
}