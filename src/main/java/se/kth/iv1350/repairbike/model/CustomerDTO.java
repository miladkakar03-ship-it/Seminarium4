package se.kth.iv1350.repairbike.model;

/**
 * En oföränderlig databärare för kundinformation. 
 * Används för att skicka data mellan lager utan att exponera domänobjektet.
 */
public class CustomerDTO {
    private final String name;
    private final String phoneNumber;

    /**
     * Skapar en ny CustomerDTO baserat på ett kundobjekt.
     * @param customer Kunden vars data ska kopieras.
     */
    public CustomerDTO(Customer customer) {
        this.name = customer.getName();
        this.phoneNumber = customer.getPhoneNumber();
    }

    /** @return Kundens namn. */
    public String getName() { return name; }

    /** @return Kundens telefonnummer. */
    public String getPhoneNumber() { return phoneNumber; }
}