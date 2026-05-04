package bikerepair.view;

import bikerepair.controller.Controller;
import bikerepair.dto.CustomerDTO;
import bikerepair.dto.RepairOrderDTO;

/**
 * Simulerar användarinteraktion med hårdkodade anrop till controller.
 */
public class View {
    private final Controller controller;

    /**
     * Skapar en ny view.
     *
     * @param controller Controllern som används för alla systemoperationer.
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Kör en exempelkörning av basic flow.
     */
    public void sampleExecution() {
        String phoneNumber = "0701234567";
        String bikeSerialNo = "BIKE-1001";

        CustomerDTO customer = controller.findCustomer(phoneNumber);
        System.out.println(customer);

        RepairOrderDTO createdOrder = controller.createRepairOrder("Batteriet tar slut ovanligt snabbt", phoneNumber,
                bikeSerialNo);
        System.out.println(createdOrder);

        RepairOrderDTO foundOrder = controller.findRepairOrder(createdOrder.getId());
        System.out.println(foundOrder);

        controller.addDiagnosticReport(createdOrder.getId(), "Batteriets kapacitet är lägre än förväntat.");
        controller.addRepairTask(createdOrder.getId(), "Byt batteripaket", 2495.0);
        controller.addRepairTask(createdOrder.getId(), "Uppdatera mjukvara", 395.0);
        System.out.println(controller.findRepairOrder(createdOrder.getId()));

        controller.acceptRepairOrder(createdOrder.getId());
    }
}
