package se.kth.iv1350.repairbike.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representerar en reparationsorder i systemet och hanterar dess interna tillstånd.
 */
public class RepairOrder {
    private int orderId;
    private Customer customer;
    private Bike bike;
    private String problemDescription;
    private String diagnosticReport;
    private String state;
    private List<RepairTask> repairTasks = new ArrayList<>();
    private List<RepairOrderObserver> observers = new ArrayList<>();
    private DiscountStrategy discountStrategy = new NoDiscount();

    /**
     * Skapar en ny reparationsorder för en specifik kund och cykel.
     * * @param customer Kunden som äger ordern.
     * @param bike Cykeln som ska repareras.
     */
    public RepairOrder(Customer customer, Bike bike) {
        this.orderId = 12345;
        this.customer = customer;
        this.bike = bike;
        this.state = "Newly Created";
    }

    /**
     * Lägger till en enskild observatör till ordern.
     * * @param observer Observatören som ska registreras.
     */
    public void addObserver(RepairOrderObserver observer) {
        observers.add(observer);
    }

    /**
     * Lägger till en lista av observatörer till ordern.
     * * @param observers Listan med observatörer som ska registreras.
     */
    public void addObservers(List<RepairOrderObserver> observers) {
        this.observers.addAll(observers);
    }

    private void notifyObservers() {
        RepairOrderDTO dto = new RepairOrderDTO(this);
        for (RepairOrderObserver observer : observers) {
            observer.orderUpdated(dto);
        }
    }

    /**
     * Sätter en ny rabattstrategi på ordern och notifierar observatörer.
     * * @param discountStrategy Den valda rabattstrategin.
     */
    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
        notifyObservers();
    }

    /**
     * Lägger till ett nytt arbetsmoment på ordern och notifierar observatörer.
     * * @param description Beskrivning av momentet.
     * @param cost Kostnaden för momentet.
     */
    public void addRepairTask(String description, double cost) {
        repairTasks.add(new RepairTask(description, cost));
        notifyObservers();
    }

    /**
     * Beräknar totalen och drar av rabatten som ges av vald strategi.
     * * @return Slutgiltig kostnad efter rabatt.
     */
    public double getTotalCost() {
        double total = 0;
        for (RepairTask task : repairTasks) {
            total += task.getCost();
        }
        return total - discountStrategy.calculateDiscount(this);
    }

    /**
     * Sätter problembeskrivningen på ordern utan att trigga notifiering.
     * * @param description Kundens beskrivning av problemet.
     */
    public void setProblemDescription(String description) { 
        this.problemDescription = description; 
    }

    /**
     * Sätter diagnosrapporten och notifierar observatörer.
     * * @param report Teknikerns rapport.
     */
    public void setDiagnosticReport(String report) { 
        this.diagnosticReport = report; 
        notifyObservers();
    }

    /**
     * Ändrar orderns status och notifierar observatörer.
     * * @param newState Den nya statusen.
     */
    public void setState(String newState) { 
        this.state = newState; 
        notifyObservers();
    }
    
    /** @return Orderns unika ID. */
    public int getOrderId() { return orderId; }
    /** @return Orderns nuvarande status text. */
    public String getState() { return state; }
    /** @return Kundobjektet kopplat till ordern. */
    public Customer getCustomer() { return customer; }
    /** @return Cykelobjektet kopplat till ordern. */
    public Bike getBike() { return bike; }
    /** @return Problembeskrivningen. */
    public String getProblemDescription() { return problemDescription; }
    /** @return Diagnosrapporten. */
    public String getDiagnosticReport() { return diagnosticReport; }
    /** @return Listan med registrerade arbetsmoment. */
    public List<RepairTask> getRepairTasks() { return repairTasks; }
}