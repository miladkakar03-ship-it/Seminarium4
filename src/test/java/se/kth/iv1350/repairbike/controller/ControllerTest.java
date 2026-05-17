package se.kth.iv1350.repairbike.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.repairbike.integration.CustomerRegistry;
import se.kth.iv1350.repairbike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairbike.integration.CustomerNotFoundException; 
import se.kth.iv1350.repairbike.model.CustomerDTO;
import se.kth.iv1350.repairbike.model.RepairOrderDTO;
import se.kth.iv1350.repairbike.model.OperationFailedException;

/**
 * Enhetstester för Controller-klassen för att verifiera flödeskoordineringen.
 */
public class ControllerTest {
    private Controller instance;

    /**
     * Sätter upp testmiljön före varje enskilt test.
     */
    @BeforeEach
    public void setUp() {
        CustomerRegistry custReg = CustomerRegistry.getInstance();
        RepairOrderRegistry repReg = new RepairOrderRegistry();
        instance = new Controller(custReg, repReg);
    }

    /**
     * Testar att kunden kan hittas utan problem under normala omständigheter.
     */
    @Test
    public void testFindCustomer() throws Exception {
        String phoneNumber = "12345678"; 
        CustomerDTO result = instance.findCustomer(phoneNumber);
        assertNotNull(result);
    }

    /**
     * Testar att en ny reparation kan startas på rätt sätt.
     */
    @Test
    public void testStartNewRepair() throws Exception {
        CustomerDTO customer = instance.findCustomer("12345678");
        RepairOrderDTO result = instance.startNewRepair(customer);
        assertNotNull(result);
    }

    /**
     * Testar att en diagnosrapport kan läggas till.
     */
    @Test
    public void testAddDiagnosticReport() throws Exception {
        CustomerDTO customer = instance.findCustomer("12345678");
        RepairOrderDTO newOrder = instance.startNewRepair(customer); 
        instance.findRepairOrder(newOrder.getOrderId());
        RepairOrderDTO result = instance.addDiagnosticReport("Test-diagnos");
        assertNotNull(result);
    }

    /**
     * Testar att bekräftelsen av ordern sätter statusen till Accepted.
     */
    @Test
    public void testConfirmRepairOrder() throws Exception {
        CustomerDTO customer = instance.findCustomer("12345678");
        RepairOrderDTO newOrder = instance.startNewRepair(customer);
        instance.findRepairOrder(newOrder.getOrderId());
        RepairOrderDTO confirmed = instance.confirmRepairOrder();
        assertEquals("Accepted", confirmed.getStatus());
    }

    /**
     * Verifierar att rätt undantag kastas när numret saknas.
     */
    @Test
    public void testFindCustomerThrowsNotFound() {
        String invalidNumber = "00000000";
        assertThrows(CustomerNotFoundException.class, () -> {
            instance.findCustomer(invalidNumber);
        });
    }

    /**
     * Verifierar att databasstrul packas om till ett OperationFailedException.
     */
    @Test
    public void testFindCustomerThrowsOperationFailed() {
        String databaseErrorNumber = "999";
        assertThrows(OperationFailedException.class, () -> {
            instance.findCustomer(databaseErrorNumber);
        });
    }
}