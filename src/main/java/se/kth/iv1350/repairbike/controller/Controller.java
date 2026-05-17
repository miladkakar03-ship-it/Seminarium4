package se.kth.iv1350.repairbike.controller;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.repairbike.integration.CustomerRegistry;
import se.kth.iv1350.repairbike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairbike.integration.CustomerNotFoundException;
import se.kth.iv1350.repairbike.integration.DatabaseFailureException;
import se.kth.iv1350.repairbike.model.RepairOrder;
import se.kth.iv1350.repairbike.model.RepairOrderObserver;
import se.kth.iv1350.repairbike.model.CustomerDTO;
import se.kth.iv1350.repairbike.model.RepairOrderDTO;
import se.kth.iv1350.repairbike.model.WinterDiscount;
import se.kth.iv1350.repairbike.model.OperationFailedException;
import se.kth.iv1350.repairbike.util.FileLogger;

/**
 * Applikationens controller. Detta är systemets enda controller som koordinerar 
 * operationerna mellan presentationslagret (View) och domänmodellen/integrationslagret.
 */
public class Controller {
    private CustomerRegistry customerRegistry;
    private RepairOrderRegistry repairOrderRegistry;
    private RepairOrder currentOrder;
    private List<RepairOrderObserver> orderObservers = new ArrayList<>();
    private FileLogger logger = new FileLogger();

    /**
     * Skapar en ny instans av Controller och kopplar den till systemets register.
     * * @param customerRegistry Kundregistret som hanterar kunddata.
     * @param repairOrderRegistry Orderregistret som sparar och hanterar reparationer.
     */
    public Controller(CustomerRegistry customerRegistry, RepairOrderRegistry repairOrderRegistry) {
        this.customerRegistry = customerRegistry;
        this.repairOrderRegistry = repairOrderRegistry;
    }

    /**
     * Registrerar en observatör (t.ex. en vy eller logger) som ska lyssna 
     * på och få uppdateringar från framtida ordrar som skapas.
     * * @param observer Den observatör som ska läggas till i listan.
     */
    public void addRepairOrderObserver(RepairOrderObserver observer) {
        orderObservers.add(observer);
    }

    /**
     * Söker efter en kund i kundregistret med hjälp av ett telefonnummer.
     * Kapslar in tekniska systemfel och loggar dem för utvecklare.
     * * @param phoneNumber Telefonnumret till kunden som ska sökas efter.
     * @return En CustomerDTO med kundens information om sökningen lyckas.
     * @throws CustomerNotFoundException Om telefonnumret inte kan hittas i registret.
     * @throws OperationFailedException Om operationen misslyckas på grund av databasfel.
     */
    public CustomerDTO findCustomer(String phoneNumber) throws CustomerNotFoundException, OperationFailedException {
        try {
            var customer = customerRegistry.findCustomer(phoneNumber);
            return new CustomerDTO(customer);
        } catch (DatabaseFailureException e) {
            logger.logException(e);
            throw new OperationFailedException("Kunde inte slutföra sökningen då databasservicen inte svarar.", e);
        }
    }

    /**
     * Startar en ny reparationsorder för en specifik kund.
     * * @param customerDTO Dataöverföringsobjektet som innehåller kundens information.
     * @return En RepairOrderDTO som representerar den nyskapade ordern.
     * @throws CustomerNotFoundException Om kunden inte hittas under processen.
     * @throws OperationFailedException Om operationen misslyckas på grund av underliggande systemfel.
     */
    public RepairOrderDTO startNewRepair(CustomerDTO customerDTO) throws CustomerNotFoundException, OperationFailedException {
        try {
            var customer = customerRegistry.findCustomer(customerDTO.getPhoneNumber());
            this.currentOrder = repairOrderRegistry.createRepairOrder(customer, customer.getBike());
            this.currentOrder.addObservers(orderObservers);
            return new RepairOrderDTO(currentOrder);
        } catch (DatabaseFailureException e) {
            logger.logException(e);
            throw new OperationFailedException("Kunde inte starta ny reparation på grund av ett databasfel.", e);
        }
    }

    /**
     * Applicerar vinterrabatt (Strategy-mönstret) på den aktuella pågående ordern.
     */
    public void applyWinterDiscount() {
        if (currentOrder != null) {
            currentOrder.setDiscountStrategy(new WinterDiscount());
        }
    }

    /**
     * Lägger till en beskrivning av problemet på cykeln till den aktuella ordern.
     * * @param description Textbeskrivning av felet som kunden upplever.
     */
    public void addProblemDescription(String description) {
        currentOrder.setProblemDescription(description);
    }

    /**
     * Lägger till en teknisk diagnosrapport på den aktuella ordern.
     * * @param report Teknikerns sammanfattning av felet efter undersökning.
     * @return En uppdaterad RepairOrderDTO som visar den nya statusen.
     */
    public RepairOrderDTO addDiagnosticReport(String report) {
        currentOrder.setDiagnosticReport(report);
        currentOrder.setState("Ready for approval");
        return new RepairOrderDTO(currentOrder);
    }

    /**
     * Lägger till ett specifikt arbetsmoment med en tillhörande kostnad till ordern.
     * * @param description Beskrivning av vad som har reparerats.
     * @param cost Kostnaden för just detta arbetsmoment i kronor.
     */
    public void addRepairTask(String description, double cost) {
        currentOrder.addRepairTask(description, cost);
    }

    /**
     * Bekräftar och godkänner reparationsordern samt sätter dess status till "Accepted".
     * * @return En uppdaterad RepairOrderDTO som visar att ordern nu är godkänd.
     */
    public RepairOrderDTO confirmRepairOrder() {
        currentOrder.setState("Accepted");
        return new RepairOrderDTO(currentOrder);
    }

    /**
     * Hämtar den aktuella aktiva reparationsordern i sin helhet.
     * * @return Det pågående RepairOrder-objektet.
     */
    public RepairOrder getActiveOrder() {
        return currentOrder;
    }

    /**
     * Söker upp en redan sparad reparationsorder i orderregistret via dess ID.
     * * @param orderId Det unika ID-numret för ordern som ska sökas fram.
     * @return En RepairOrderDTO som representerar den hittade ordern.
     */
    public RepairOrderDTO findRepairOrder(int orderId) {
        this.currentOrder = repairOrderRegistry.getOrder(orderId);
        return new RepairOrderDTO(currentOrder);
    }
}