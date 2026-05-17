
package se.kth.iv1350.repairbike.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.repairbike.model.Bike;
import se.kth.iv1350.repairbike.model.Customer;
import se.kth.iv1350.repairbike.model.RepairOrder;

/**
 * Enhetstester för klassen RepairOrderRegistry. Verifierar att lagring
 * och hämtning av reparationsordrar fungerar korrekt.
 * 
 */
public class RepairOrderRegistryTest {
    private RepairOrderRegistry instance;
    private Customer testCustomer;
    private Bike testBike;
    
    /**
     * Skapar en instans av testklassen.
     */
    public RepairOrderRegistryTest() {
    }
    
    /**
     * Sätter upp testmiljön samt testobjekt inför varje enskilt test.
     */
    @BeforeEach
    public void setUp() {
        instance = new RepairOrderRegistry();
        testBike = new Bike("Yosemite", "Electric S1", "SN12345");
        testCustomer = new Customer("TestUser", "55555", "test@kth.se", testBike);
    }

    /**
     * Test av createRepairOrder-metoden. Verifierar att ordern skapas korrekt.
     */
    @Test
    public void testCreateRepairOrder() {
        RepairOrder result = instance.createRepairOrder(testCustomer, testBike);
        
        assertNotNull(result, "Den skapade ordern får inte vara null.");
        assertEquals("Newly Created", result.getState(), "Ordern har fel startstatus.");
    }

    /**
     * Test av getOrder-metoden när ett existerande order-ID skickas in.
     */
    @Test
    public void testGetOrderExisting() {
        // Skapar först en order i registret (den får ID 12345 enligt din kod)
        RepairOrder savedOrder = instance.createRepairOrder(testCustomer, testBike);
        int orderId = savedOrder.getOrderId();
        
        RepairOrder result = instance.getOrder(orderId);
        
        assertNotNull(result, "Ordern borde hittas i registret med rätt ID.");
        assertEquals(orderId, result.getOrderId(), "Det hämtade objektet har fel order-ID.");
    }

    /**
     * Test av getOrder-metoden när ett icke-existerande order-ID skickas in.
     */
    @Test
    public void testGetOrderNonExisting() {
        int nonExistingId = 99999; // Ett ID som aldrig lagrats
        RepairOrder result = instance.getOrder(nonExistingId);
        
        assertNull(result, "Metoden ska returnera null när man söker på ett ID som inte finns.");
    }
}