package se.kth.iv1350.repairbike.integration;

/**
 * Kastas när en sökning görs efter ett telefonnummer som inte finns i kundregistret.
 * Detta motsvarar alternativt flöde 5a.
 */
public class CustomerNotFoundException extends Exception {
    private String phoneNumberNotFound;

    /**
     * Skapar en ny instans som anger vilket nummer som inte kunde hittas.
     * @param phoneNumber Det telefonnummer som sökningen gällde.
     */
    public CustomerNotFoundException(String phoneNumber) {
        super("Kunden med telefonnummer " + phoneNumber + " hittades inte i registret.");
        this.phoneNumberNotFound = phoneNumber;
    }

    /**
     * @return Det telefonnummer som orsakade undantaget.
     */
    public String getPhoneNumberNotFound() {
        return phoneNumberNotFound;
    }
}