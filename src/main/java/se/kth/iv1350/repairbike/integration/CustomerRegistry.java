package se.kth.iv1350.repairbike.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.repairbike.model.Bike;
import se.kth.iv1350.repairbike.model.Customer;

public class CustomerRegistry {
    // Singleton-instansen
    private static final CustomerRegistry INSTANCE = new CustomerRegistry();
    private List<Customer> customers = new ArrayList<>();

    // Privat konstruktör hindrar extern instansiering
    private CustomerRegistry() {
        Bike testBike = new Bike("Yosemite", "Electric S1", "SN12345");
        customers.add(new Customer("User", "12345678", "Usern@gmail.se", testBike));
    }

    /**
     * @return Den globala unika instansen av kundregistret.
     */
    public static CustomerRegistry getInstance() {
        return INSTANCE;
    }

    public Customer findCustomer(String phoneNumber) throws CustomerNotFoundException {
        if (phoneNumber.equals("999")) {
            throw new DatabaseFailureException("Databasen går inte att nå.");
        }

        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        
        throw new CustomerNotFoundException(phoneNumber);
    }
}