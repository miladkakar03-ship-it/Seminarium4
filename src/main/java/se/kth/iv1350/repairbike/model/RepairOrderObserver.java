package se.kth.iv1350.repairbike.model;

/**
 * Interface som implementeras av klasser som vill prenumerera på 
 * uppdateringar av en reparationsorder via säkra dataöverföringsobjekt (DTO).
 */
public interface RepairOrderObserver {
    /**
     * Anropas när en reparationsorder har uppdaterats.
     * * @param orderDTO Ett oföränderligt DTO-objekt som innehåller den uppdaterade informationen.
     */
    void orderUpdated(RepairOrderDTO orderDTO);
}