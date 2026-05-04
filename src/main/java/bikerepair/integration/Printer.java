package bikerepair.integration;

import bikerepair.model.DiagnosticReport;
import bikerepair.model.RepairOrder;
import bikerepair.model.RepairTask;

/**
 * Representerar den externa printern som används för utskrift av reparationsordrar.
 */
public class Printer {
    /**
     * Skapar en printer.
     */
    public Printer() {
    }

    /**
     * Skriver ut en reparationsorder till System.out.
     *
     * @param repairOrder Reparationsordern som ska skrivas ut.
     */
    public void printRepairOrder(RepairOrder repairOrder) {
        System.out.println("----- REPARATIONSORDER -----");
        System.out.println("Order-id: " + repairOrder.getId());
        System.out.println("Datum: " + repairOrder.getDate());
        System.out.println("Kund: " + repairOrder.getCustomer().getName());
        System.out.println("Cykel: " + repairOrder.getBike().getBrand() + " " + repairOrder.getBike().getModel());
        System.out.println("Problem: " + repairOrder.getCustomersProblemDescription());
        System.out.println("Status: " + repairOrder.getState());
        printDiagnosticReports(repairOrder);
        printRepairTasks(repairOrder);
        System.out.println("Total kostnad: " + repairOrder.calculateTotalCost());
        System.out.println("------------------------");
    }

    private void printDiagnosticReports(RepairOrder repairOrder) {
        System.out.println("Diagnosrapporter:");
        for (DiagnosticReport report : repairOrder.getDiagnosticReports()) {
            System.out.println("- " + report.getDate() + ": " + report.getResult());
        }
    }

    private void printRepairTasks(RepairOrder repairOrder) {
        System.out.println("Reparationsuppgifter:");
        for (RepairTask task : repairOrder.getRepairTasks()) {
            System.out.println("- " + task.getDescription() + ", kostnad: " + task.getCost());
        }
    }
}
