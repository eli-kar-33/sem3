package bikerepair.integration;

import java.util.HashMap;
import java.util.Map;

import bikerepair.model.RepairOrder;

/**
 * Lagrar reparationsordrar i minnet.
 */
public class RepairOrderRegistry {
    private final Map<String, RepairOrder> repairOrdersById = new HashMap<>();

    /**
     * Skapar ett tomt reparationsorderregistry.
     */
    public RepairOrderRegistry() {
    }

    /**
     * Sparar den angivna reparationsordern.
     *
     * @param repairOrder Reparationsordern som ska sparas.
     */
    public void save(RepairOrder repairOrder) {
        repairOrdersById.put(repairOrder.getId(), repairOrder);
    }

    /**
     * Hämtar en reparationsorder med id.
     *
     * @param repairOrderId Id:t att söka efter.
     * @return Den matchande reparationsordern, eller null om ingen finns.
     */
    public RepairOrder findById(String repairOrderId) {
        return repairOrdersById.get(repairOrderId);
    }
}
