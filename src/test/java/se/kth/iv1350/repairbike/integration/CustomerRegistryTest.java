package se.kth.iv1350.repairbike.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.repairbike.model.Customer;

/**
 * Enhetstester för klassen CustomerRegistry. Verifierar att söklogiken
 * efter kunder fungerar som förväntat.
 * */
public class CustomerRegistryTest {
    private CustomerRegistry instance;
    
    /**
     * Skapar en instans av testklassen.
     */
    public CustomerRegistryTest() {
    }
    
    /**
     * Sätter upp en ren testmiljö med ett nytt register inför varje enskilt test.
     */
    @BeforeEach
    public void setUp() {
        instance = CustomerRegistry.getInstance();
    }

    /**
     * Test av findCustomer-metoden när telefonnumret existerar i registret.
     */
    @Test
    public void testFindCustomerExisting() throws Exception { 
        String phoneNumber = "12345678";
        Customer result = instance.findCustomer(phoneNumber);
        assertNotNull(result, "Kunden borde hittas eftersom numret existerar.");
        assertEquals("User", result.getName(), "Kunden som hittades hade fel namn.");
    }
    
    /**
     * Test av findCustomer-metoden när telefonnumret inte existerar i registret.
     */
    @Test
    public void testFindCustomerNonExisting() {
        String phoneNumber = "99999999"; // Ett nummer som inte finns
        assertThrows(CustomerNotFoundException.class, () -> {
            instance.findCustomer(phoneNumber);
        });
    }

    /**
     * here is change for seminarium 5 test
     * 
     * Verifierar att det simulerade databasfelet kastar ett DatabaseFailureException.
     */
    @Test
    public void testFindCustomerThrowsDatabaseFailure() {
        String databaseErrorNumber = "999";
        assertThrows(DatabaseFailureException.class, () -> {
            instance.findCustomer(databaseErrorNumber);
        });
    }
}