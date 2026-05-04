package bikerepair.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bikerepair.dto.CustomerDTO;
import bikerepair.dto.RepairOrderDTO;
import bikerepair.integration.CustomerRegistry;
import bikerepair.integration.Printer;
import bikerepair.integration.RepairOrderRegistry;
import bikerepair.model.RepairOrderState;

/**
 * Tester för Controller.
 */
public class ControllerTest {
    private Controller controller;

    /**
     * Skapar en ny controller före varje test.
     */
    @BeforeEach
    public void setUp() {
        controller = new Controller(new CustomerRegistry(), new RepairOrderRegistry(), new SilentPrinter());
    }

    /**
     * Testar att kunddata returneras för ett känt telefonnummer.
     */
    @Test
    public void testFindExistingCustomerReturnsDTO() {
        CustomerDTO customerDTO = controller.findCustomer("0701234567");
        assertNotNull(customerDTO, "En känd kund ska returnera en DTO.");
        assertEquals("Elias Karch", customerDTO.getName(), "DTO:n ska innehålla kundens namn.");
        assertEquals("BIKE-1001", customerDTO.getBikeSerialNo(), "DTO:n ska innehålla kundens cykel.");
    }

    /**
     * Testar att en okänd kund returnerar null.
     */
    @Test
    public void testFindMissingCustomerReturnsNull() {
        assertNull(controller.findCustomer("0000000000"), "En okänd kund ska returnera null.");
    }

    /**
     * Testar att en reparationsorder skapas och returneras som DTO.
     */
    @Test
    public void testCreateRepairOrderReturnsDTO() {
        RepairOrderDTO repairOrderDTO = controller.createRepairOrder("Batteriproblem", "0701234567", "BIKE-1001");
        assertNotNull(repairOrderDTO, "Giltig kund- och cykeldata ska skapa en reparationsorder.");
        assertEquals(RepairOrderState.NEWLY_CREATED, repairOrderDTO.getState(),
                "En skapad reparationsorder ska ha tillståndet newly created.");
        assertEquals("Batteriproblem", repairOrderDTO.getProblemDescription(),
                "DTO:n ska innehålla problembeskrivningen.");
    }

    /**
     * Testar att en saknad kund förhindrar att en reparationsorder skapas.
     */
    @Test
    public void testCreateRepairOrderWithMissingCustomerReturnsNull() {
        assertNull(controller.createRepairOrder("Batteriproblem", "0000000000", "BIKE-1001"),
                "Saknad kunddata ska förhindra att en reparationsorder skapas.");
    }

    /**
     * Testar att en saknad cykel förhindrar att en reparationsorder skapas.
     */
    @Test
    public void testCreateRepairOrderWithMissingBikeReturnsNull() {
        assertNull(controller.createRepairOrder("Batteriproblem", "0701234567", "MISSING"),
                "Saknad cykeldata ska förhindra att en reparationsorder skapas.");
    }

    /**
     * Testar att en sparad reparationsorder kan hittas med id.
     */
    @Test
    public void testFindRepairOrderReturnsStoredOrder() {
        RepairOrderDTO createdOrder = controller.createRepairOrder("Batteriproblem", "0701234567", "BIKE-1001");
        RepairOrderDTO foundOrder = controller.findRepairOrder(createdOrder.getId());
        assertEquals(createdOrder.getId(), foundOrder.getId(), "Den hittade ordern ska ha samma id.");
    }

    /**
     * Testar att en saknad reparationsorder returnerar null.
     */
    @Test
    public void testFindMissingRepairOrderReturnsNull() {
        assertNull(controller.findRepairOrder("MISSING"), "En saknad reparationsorder ska returnera null.");
    }

    /**
     * Testar att diagnosrapport och reparationsuppgift uppdaterar returnerad DTO.
     */
    @Test
    public void testAddDiagnosticReportAndRepairTaskUpdatesOrder() {
        RepairOrderDTO createdOrder = controller.createRepairOrder("Batteriproblem", "0701234567", "BIKE-1001");
        controller.addDiagnosticReport(createdOrder.getId(), "Svaga battericeller");
        controller.addRepairTask(createdOrder.getId(), "Byt batteri", 2495.0);
        RepairOrderDTO updatedOrder = controller.findRepairOrder(createdOrder.getId());
        assertEquals(RepairOrderState.READY_FOR_APPROVAL, updatedOrder.getState(),
                "Diagnos och uppgifter ska göra ordern ready for approval.");
        assertEquals(2495.0, updatedOrder.getTotalCost(), 0.001, "Uppgiftens kostnad ska räknas in i totalkostnaden.");
    }

    /**
     * Testar att acceptRepairOrder ändrar tillståndet.
     */
    @Test
    public void testAcceptRepairOrderChangesState() {
        RepairOrderDTO createdOrder = controller.createRepairOrder("Batteriproblem", "0701234567", "BIKE-1001");
        controller.acceptRepairOrder(createdOrder.getId());
        assertEquals(RepairOrderState.ACCEPTED, controller.findRepairOrder(createdOrder.getId()).getState(),
                "AcceptRepairOrder ska ändra tillståndet till accepted.");
    }

    /**
     * Testar att rejectRepairOrder ändrar tillståndet.
     */
    @Test
    public void testRejectRepairOrderChangesState() {
        RepairOrderDTO createdOrder = controller.createRepairOrder("Batteriproblem", "0701234567", "BIKE-1001");
        controller.rejectRepairOrder(createdOrder.getId());
        assertEquals(RepairOrderState.REJECTED, controller.findRepairOrder(createdOrder.getId()).getState(),
                "RejectRepairOrder ska ändra tillståndet till rejected.");
    }

    /**
     * Testar grenar utan effekt för saknade order-id.
     */
    @Test
    public void testUpdateMethodsIgnoreMissingRepairOrder() {
        controller.addDiagnosticReport("MISSING", "Ingen order");
        controller.addRepairTask("MISSING", "Ingen uppgift", 1.0);
        controller.acceptRepairOrder("MISSING");
        controller.rejectRepairOrder("MISSING");
        assertNull(controller.findRepairOrder("MISSING"), "Saknade ordrar ska fortfarande saknas efter uppdateringar.");
    }

    private static class SilentPrinter extends Printer {
        /**
         * Ignorerar utskrifter under controllertester.
         */
        @Override
        public void printRepairOrder(bikerepair.model.RepairOrder repairOrder) {
        }
    }
}
