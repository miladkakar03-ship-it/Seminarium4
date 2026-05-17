package se.kth.iv1350.repairbike.model;

/**
 * Representerar kundens cykel med dess specifika detaljer.
 */
public class Bike {
    private String brand;
    private String model;
    private String serialNumber;

    public Bike(String brand, String model, String serialNumber) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    /**
     * Formaterar cykelns information för utskrift på kvittot.
     */
    @Override
    public String toString() {
        return brand + " " + model + " (Ramnr: " + serialNumber + ")";
    }
}