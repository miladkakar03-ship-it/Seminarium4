package se.kth.iv1350.repairbike.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.repairbike.model.Customer;

/**
 * Enhetstester för klassen CustomerRegistry. Verifierar att söklogiken
 * efter kunder fungerar som förväntat.
 * 
 */
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
        // ÄNDRAT: Använder getInstance() eftersom klassen nu är en Singleton
        instance = CustomerRegistry.getInstance();
    }

    /**
     * Test av findCustomer-metoden när telefonnumret existerar i registret.
     */
    @Test
    public void testFindCustomerExisting() throws Exception { // ÄNDRAT: Lagt till throws Exception
        String phoneNumber = "12345678"; // Numret från din hårdkodade testdata
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
        
        // ÄNDRAT: Eftersom din findCustomer nu kastar ett checked exception vid fel, 
        // måste vi verifiera det med assertThrows istället för att förvänta oss null.
        assertThrows(CustomerNotFoundException.class, () -> {
            instance.findCustomer(phoneNumber);
        });
    }
}