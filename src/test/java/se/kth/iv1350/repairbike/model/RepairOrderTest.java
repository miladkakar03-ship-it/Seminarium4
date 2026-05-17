package se.kth.iv1350.repairbike.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Enhetstester för RepairOrder-klassen för att säkerställa att domänlogik
 * kring statusändringar och kostnadsberäkningar fungerar.
 */
public class RepairOrderTest {
    private RepairOrder order;

    /**
     * Initierar en ny RepairOrder med en testkund inför varje testmetod.
     */
    @BeforeEach
    public void setUp() {
        Customer testCustomer = new Customer("Sven", "123", "a@b.com", null);
        order = new RepairOrder(testCustomer, null);
    }

    /**
     * Testar att orderns tillstånd (state) uppdateras korrekt vid anrop.
     */
    @Test
    public void testStatusChange() {
        assertEquals("Newly Created", order.getState(), "Startstatus var fel.");
        
        order.setState("Accepted");
        assertEquals("Accepted", order.getState(), "Status ändrades inte korrekt.");
    }

    /**
     * Verifierar att arbetsmoment (RepairTasks) läggs till korrekt och att 
     * den totala kostnaden summeras på rätt sätt.
     */
    @Test
    public void testAddRepairTask() {
        order.addRepairTask("Service", 100.0);
        assertNotNull(order, "Ordern bör fortfarande existera efter tillagt moment.");
        
        assertEquals(100.0, order.getTotalCost(), 0.01, "Kostnaden för momentet registrerades inte korrekt.");
    }
}