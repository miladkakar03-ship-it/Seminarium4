package se.kth.iv1350.repairbike.model;

/**
 * Innehåller information om kunden och kopplingen till kundens cykel.
 */
public class Customer {
    private String name;
    private String phoneNumber;
    private String email;
    private Bike bike;

    /**
     * Skapar en ny instans av en kund.
     * @param name Kundens namn.
     * @param phoneNumber Kundens telefonnummer.
     * @param email Kundens e-postadress.
     * @param bike Cykeln som tillhör kunden.
     */
    public Customer(String name, String phoneNumber, String email, Bike bike) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bike = bike;
    }

    /** @return Kundens namn. */
    public String getName() { return name; }
    
    /** @return Kundens telefonnummer. */
    public String getPhoneNumber() { return phoneNumber; }
    
    /** @return Kundens e-postadress. */
    public String getEmail() { return email; }
    
    /** @return Cykeln som kunden äger. */
    public Bike getBike() { return bike; }
}