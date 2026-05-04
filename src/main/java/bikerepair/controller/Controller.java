package bikerepair.controller;

import bikerepair.dto.CustomerDTO;
import bikerepair.dto.RepairOrderDTO;
import bikerepair.integration.CustomerRegistry;
import bikerepair.integration.Printer;
import bikerepair.integration.RepairOrderRegistry;
import bikerepair.model.Bike;
import bikerepair.model.Customer;
import bikerepair.model.RepairOrder;

/**
 * Koordinerar anrop från view till model och integration.
 */
public class Controller {
    private final CustomerRegistry customerRegistry;
    private final RepairOrderRegistry repairOrderRegistry;
    private final Printer printer;

    /**
     * Skapar en ny controller.
     *
     * @param customerRegistry Registryt som innehåller kund- och cykeldata.
     * @param repairOrderRegistry Registryt som innehåller reparationsordrar.
     * @param printer Printern som används för utskrift av reparationsordrar.
     */
    public Controller(CustomerRegistry customerRegistry, RepairOrderRegistry repairOrderRegistry, Printer printer) {
        this.customerRegistry = customerRegistry;
        this.repairOrderRegistry = repairOrderRegistry;
        this.printer = printer;
    }

    /**
     * Hämtar kund- och cykeldata för den angivna kunden.
     *
     * @param phoneNumber Kundens telefonnummer.
     * @return Kunddata till view, eller null om kunden saknas.
     */
    public CustomerDTO findCustomer(String phoneNumber) {
        Customer customer = customerRegistry.findCustomer(phoneNumber);
        if (customer == null) {
            return null;
        }
        Bike bike = customerRegistry.findFirstBikeForCustomer(phoneNumber);
        return toCustomerDTO(customer, bike);
    }

    /**
     * Skapar och sparar en reparationsorder.
     *
     * @param problemDescription Kundens problembeskrivning.
     * @param phoneNumber Kundens telefonnummer.
     * @param bikeSerialNo Cykelns serienummer.
     * @return Reparationsorderdata till view, eller null om kund eller cykel saknas.
     */
    public RepairOrderDTO createRepairOrder(String problemDescription, String phoneNumber, String bikeSerialNo) {
        Customer customer = customerRegistry.findCustomer(phoneNumber);
        Bike bike = customerRegistry.findBike(bikeSerialNo);
        if (customer == null || bike == null) {
            return null;
        }
        RepairOrder repairOrder = new RepairOrder(customer, bike, problemDescription);
        repairOrderRegistry.save(repairOrder);
        return toRepairOrderDTO(repairOrder);
    }

    /**
     * Hämtar en reparationsorder med id.
     *
     * @param repairOrderId Reparationsorderns id.
     * @return Reparationsorderdata till view, eller null om ordern saknas.
     */
    public RepairOrderDTO findRepairOrder(String repairOrderId) {
        RepairOrder repairOrder = repairOrderRegistry.findById(repairOrderId);
        if (repairOrder == null) {
            return null;
        }
        return toRepairOrderDTO(repairOrder);
    }

    /**
     * Lägger till en diagnosrapport i en reparationsorder.
     *
     * @param repairOrderId Reparationsorderns id.
     * @param result Diagnosresultatet.
     */
    public void addDiagnosticReport(String repairOrderId, String result) {
        RepairOrder repairOrder = repairOrderRegistry.findById(repairOrderId);
        if (repairOrder != null) {
            repairOrder.addDiagnosticReport(result);
            repairOrderRegistry.save(repairOrder);
        }
    }

    /**
     * Lägger till en reparationsuppgift i en reparationsorder.
     *
     * @param repairOrderId Reparationsorderns id.
     * @param description Reparationsuppgiftens beskrivning.
     * @param cost Reparationsuppgiftens kostnad.
     */
    public void addRepairTask(String repairOrderId, String description, double cost) {
        RepairOrder repairOrder = repairOrderRegistry.findById(repairOrderId);
        if (repairOrder != null) {
            repairOrder.addRepairTask(description, cost);
            repairOrderRegistry.save(repairOrder);
        }
    }

    /**
     * Accepterar en reparationsorder och skriver ut den.
     *
     * @param repairOrderId Reparationsorderns id.
     */
    public void acceptRepairOrder(String repairOrderId) {
        RepairOrder repairOrder = repairOrderRegistry.findById(repairOrderId);
        if (repairOrder != null) {
            repairOrder.accept();
            repairOrderRegistry.save(repairOrder);
            printer.printRepairOrder(repairOrder);
        }
    }

    /**
     * Avvisar en reparationsorder.
     *
     * @param repairOrderId Reparationsorderns id.
     */
    public void rejectRepairOrder(String repairOrderId) {
        RepairOrder repairOrder = repairOrderRegistry.findById(repairOrderId);
        if (repairOrder != null) {
            repairOrder.reject();
            repairOrderRegistry.save(repairOrder);
        }
    }

    private CustomerDTO toCustomerDTO(Customer customer, Bike bike) {
        if (bike == null) {
            return new CustomerDTO(customer.getName(), customer.getEmail(), customer.getPhone(), "", "", "");
        }
        return new CustomerDTO(customer.getName(), customer.getEmail(), customer.getPhone(), bike.getBrand(),
                bike.getModel(), bike.getSerialNumber());
    }

    private RepairOrderDTO toRepairOrderDTO(RepairOrder repairOrder) {
        return new RepairOrderDTO(repairOrder.getId(), repairOrder.getState(), repairOrder.calculateTotalCost(),
                repairOrder.getCustomersProblemDescription());
    }
}
