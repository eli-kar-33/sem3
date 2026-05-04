package bikerepair.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tester för RepairOrder.
 */
public class RepairOrderTest {
    private RepairOrder repairOrder;

    /**
     * Skapar en ny reparationsorder före varje test.
     */
    @BeforeEach
    public void setUp() {
        Customer customer = new Customer("Testkund", "test@example.com", "0700000000");
        Bike bike = new Bike("TestBrand", "TestModel", "TEST-1");
        repairOrder = new RepairOrder("ORDER-1", customer, bike, "Cykeln startar inte", LocalDate.of(2026, 5, 3));
    }

    /**
     * Testar att en ny reparationsorder börjar i tillståndet newly created.
     */
    @Test
    public void testNewRepairOrderHasNewlyCreatedState() {
        assertEquals(RepairOrderState.NEWLY_CREATED, repairOrder.getState(),
                "En ny reparationsorder ska börja i tillståndet newly created.");
    }

    /**
     * Testar att en diagnosrapport sparas och ändrar tillståndet till ready for approval.
     */
    @Test
    public void testAddDiagnosticReportStoresReportAndMarksReadyForApproval() {
        repairOrder.addDiagnosticReport("Fel på batterikontakten");
        assertEquals(1, repairOrder.getDiagnosticReports().size(), "En diagnosrapport ska sparas.");
        assertEquals("Fel på batterikontakten", repairOrder.getDiagnosticReports().get(0).getResult(),
                "Det sparade diagnosresultatet ska stämma med indata.");
        assertEquals(RepairOrderState.READY_FOR_APPROVAL, repairOrder.getState(),
                "En diagnosrapport ska markera ordern som ready for approval.");
    }

    /**
     * Testar att reparationsuppgifter räknas in i totalkostnaden.
     */
    @Test
    public void testCalculateTotalCostSumsRepairTaskCosts() {
        repairOrder.addRepairTask("Byt kontakt", 450.0);
        repairOrder.addRepairTask("Testa batteri", 250.5);
        assertEquals(700.5, repairOrder.calculateTotalCost(), 0.001,
                "Totalkostnaden ska vara summan av alla reparationsuppgifters kostnad.");
    }

    /**
     * Testar att accept ändrar tillståndet till accepted.
     */
    @Test
    public void testAcceptChangesStateToAccepted() {
        repairOrder.accept();
        assertEquals(RepairOrderState.ACCEPTED, repairOrder.getState(),
                "Accept ska ändra reparationsorderns tillstånd till accepted.");
    }

    /**
     * Testar att reject ändrar tillståndet till rejected.
     */
    @Test
    public void testRejectChangesStateToRejected() {
        repairOrder.reject();
        assertEquals(RepairOrderState.REJECTED, repairOrder.getState(),
                "Reject ska ändra reparationsorderns tillstånd till rejected.");
    }

    /**
     * Testar false-grenen i markReadyForApproval genom att behålla tillståndet accepted.
     */
    @Test
    public void testMarkReadyForApprovalDoesNotChangeAcceptedOrder() {
        repairOrder.accept();
        repairOrder.markReadyForApproval();
        assertEquals(RepairOrderState.ACCEPTED, repairOrder.getState(),
                "En accepterad order ska inte ändras till ready for approval.");
    }

    /**
     * Testar att returnerade listor inte exponerar interna ändringsbara samlingar.
     */
    @Test
    public void testRepairTasksListIsUnmodifiable() {
        repairOrder.addRepairTask("Byt batteri", 1000.0);
        assertThrows(UnsupportedOperationException.class,
                () -> repairOrder.getRepairTasks().add(new RepairTask("Extern ändring", 1.0)),
                "Den returnerade listan med reparationsuppgifter ska inte gå att ändra.");
        assertFalse(repairOrder.getRepairTasks().isEmpty(), "Den ursprungliga uppgiften ska fortfarande finnas kvar.");
    }
}
