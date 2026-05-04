package bikerepair.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Representerar en reparationsorder och innehåller affärslogiken för att uppdatera den.
 */
public class RepairOrder {
    private final String id;
    private final LocalDate date;
    private final Customer customer;
    private final Bike bike;
    private final String customersProblemDescription;
    private final List<DiagnosticReport> diagnosticReports = new ArrayList<>();
    private final List<RepairTask> repairTasks = new ArrayList<>();
    private RepairOrderState state;
    private LocalDate estimatedCompletionDate;

    /**
     * Skapar en ny reparationsorder med dagens datum och ett genererat id.
     *
     * @param customer Kunden som begärde reparationen.
     * @param bike Cykeln som ska repareras.
     * @param customersProblemDescription Kundens problembeskrivning.
     */
    public RepairOrder(Customer customer, Bike bike, String customersProblemDescription) {
        this(UUID.randomUUID().toString(), customer, bike, customersProblemDescription, LocalDate.now());
    }

    /**
     * Skapar en ny reparationsorder.
     *
     * @param id Reparationsorderns id.
     * @param customer Kunden som begärde reparationen.
     * @param bike Cykeln som ska repareras.
     * @param customersProblemDescription Kundens problembeskrivning.
     * @param date Datumet då ordern skapades.
     */
    public RepairOrder(String id, Customer customer, Bike bike, String customersProblemDescription, LocalDate date) {
        this.id = id;
        this.customer = customer;
        this.bike = bike;
        this.customersProblemDescription = customersProblemDescription;
        this.date = date;
        this.state = RepairOrderState.NEWLY_CREATED;
    }

    /**
     * Lägger till en diagnosrapport i denna reparationsorder.
     *
     * @param result Diagnosresultatet.
     */
    public void addDiagnosticReport(String result) {
        diagnosticReports.add(new DiagnosticReport(result));
        markReadyForApproval();
    }

    /**
     * Lägger till en reparationsuppgift i denna reparationsorder.
     *
     * @param description Uppgiftens beskrivning.
     * @param cost Uppgiftens kostnad.
     */
    public void addRepairTask(String description, double cost) {
        repairTasks.add(new RepairTask(description, cost));
        markReadyForApproval();
    }

    /**
     * Markerar denna reparationsorder som redo för kundens godkännande.
     */
    public void markReadyForApproval() {
        if (state == RepairOrderState.NEWLY_CREATED) {
            state = RepairOrderState.READY_FOR_APPROVAL;
        }
    }

    /**
     * Accepterar denna reparationsorder.
     */
    public void accept() {
        state = RepairOrderState.ACCEPTED;
    }

    /**
     * Avvisar denna reparationsorder.
     */
    public void reject() {
        state = RepairOrderState.REJECTED;
    }

    /**
     * Beräknar den totala reparationskostnaden.
     *
     * @return Summan av alla reparationsuppgifters kostnader.
     */
    public double calculateTotalCost() {
        double total = 0;
        for (RepairTask repairTask : repairTasks) {
            total += repairTask.getCost();
        }
        return total;
    }

    /**
     * Anger uppskattat färdigdatum.
     *
     * @param estimatedCompletionDate Det uppskattade färdigdatumet.
     */
    public void setEstimatedCompletionDate(LocalDate estimatedCompletionDate) {
        this.estimatedCompletionDate = estimatedCompletionDate;
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
     * Hämtar datumet då ordern skapades.
     *
     * @return Datumet då ordern skapades.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Hämtar kunden som begärde reparationen.
     *
     * @return Kunden som begärde reparationen.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Hämtar cykeln som ska repareras.
     *
     * @return Cykeln som ska repareras.
     */
    public Bike getBike() {
        return bike;
    }

    /**
     * Hämtar kundens problembeskrivning.
     *
     * @return Kundens problembeskrivning.
     */
    public String getCustomersProblemDescription() {
        return customersProblemDescription;
    }

    /**
     * Hämtar reparationsorderns aktuella status.
     *
     * @return Reparationsorderns aktuella status.
     */
    public RepairOrderState getState() {
        return state;
    }

    /**
     * Hämtar det uppskattade färdigdatumet.
     *
     * @return Det uppskattade färdigdatumet.
     */
    public LocalDate getEstimatedCompletionDate() {
        return estimatedCompletionDate;
    }

    /**
     * Hämtar en oföränderlig lista med diagnosrapporter.
     *
     * @return En oföränderlig lista med diagnosrapporter.
     */
    public List<DiagnosticReport> getDiagnosticReports() {
        return Collections.unmodifiableList(diagnosticReports);
    }

    /**
     * Hämtar en oföränderlig lista med reparationsuppgifter.
     *
     * @return En oföränderlig lista med reparationsuppgifter.
     */
    public List<RepairTask> getRepairTasks() {
        return Collections.unmodifiableList(repairTasks);
    }
}
