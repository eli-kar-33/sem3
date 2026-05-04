package bikerepair.model;

/**
 * Representerar föreslaget reparationsarbete för en reparationsorder.
 */
public class RepairTask {
    private final String description;
    private final double cost;

    /**
     * Skapar en ny reparationsuppgift.
     *
     * @param description Uppgiftens beskrivning.
     * @param cost Uppgiftens kostnad.
     */
    public RepairTask(String description, double cost) {
        this.description = description;
        this.cost = cost;
    }

    /**
     * Hämtar uppgiftens beskrivning.
     *
     * @return Uppgiftens beskrivning.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Hämtar uppgiftens kostnad.
     *
     * @return Uppgiftens kostnad.
     */
    public double getCost() {
        return cost;
    }
}
