package bikerepair.startup;

import bikerepair.controller.Controller;
import bikerepair.integration.CustomerRegistry;
import bikerepair.integration.Printer;
import bikerepair.integration.RepairOrderRegistry;
import bikerepair.view.View;

/**
 * Startar cykelreparationsprogrammet.
 */
public class Main {
    private Main() {
    }

    /**
     * Startar exempelkörningen.
     *
     * @param args Kommandoradsargument, används inte.
     */
    public static void main(String[] args) {
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();
        Controller controller = new Controller(customerRegistry, repairOrderRegistry, printer);
        View view = new View(controller);
        view.sampleExecution();
    }
}
