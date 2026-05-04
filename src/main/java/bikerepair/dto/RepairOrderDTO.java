package bikerepair.dto;

import bikerepair.model.RepairOrderState;

/**
 * Oföränderlig reparationsorderdata som returneras från controller till view.
 */
public class RepairOrderDTO {
    private final String id;
    private final RepairOrderState state;
    private final double totalCost;
    private final String problemDescription;

    /**
     * Skapar en ny reparationsorder-DTO.
     *
     * @param id Reparationsorderns id.
     * @param state Reparationsorderns status.
     * @param totalCost Den aktuella totalkostnaden.
     * @param problemDescription Kundens problembeskrivning.
     */
    public RepairOrderDTO(String id, RepairOrderState state, double totalCost, String problemDescription) {
        this.id = id;
        this.state = state;
        this.totalCost = totalCost;
        this.problemDescription = problemDescription;
    }

    /**
     * Hämtar reparationsorderns id.
     *
     * @return Reparationsorderns id.
     */
    public String getId() {
        return id;
    }

    /**
     * Hämtar reparationsorderns status.
     *
     * @return Reparationsorderns status.
     */
    public RepairOrderState getState() {
        return state;
    }

    /**
     * Hämtar den aktuella totalkostnaden.
     *
     * @return Den aktuella totalkostnaden.
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Hämtar kundens problembeskrivning.
     *
     * @return Kundens problembeskrivning.
     */
    public String getProblemDescription() {
        return problemDescription;
    }

    /**
     * Hämtar en läsbar beskrivning av denna DTO.
     *
     * @return En läsbar beskrivning av denna DTO.
     */
    @Override
    public String toString() {
        return "RepairOrderDTO{orderId='" + id + "', status=" + state + ", totalKostnad=" + totalCost
                + ", problembeskrivning='" + problemDescription + "'}";
    }
}
