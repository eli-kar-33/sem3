package bikerepair.integration;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bikerepair.model.Bike;
import bikerepair.model.Customer;
import bikerepair.model.RepairOrder;

/**
 * Tester för RepairOrderRegistry.
 */
public class RepairOrderRegistryTest {
    private RepairOrderRegistry registry;

    /**
     * Skapar ett nytt registry före varje test.
     */
    @BeforeEach
    public void setUp() {
        registry = new RepairOrderRegistry();
    }

    /**
     * Testar att en sparad reparationsorder kan hittas med id.
     */
    @Test
    public void testSaveAndFindById() {
        RepairOrder repairOrder = createRepairOrder("ORDER-1");
        registry.save(repairOrder);
        assertSame(repairOrder, registry.findById("ORDER-1"),
                "Registryt ska returnera samma reparationsorderobjekt som sparades.");
    }

    /**
     * Testar att en saknad reparationsorder returnerar null.
     */
    @Test
    public void testFindMissingOrderReturnsNull() {
        assertNull(registry.findById("MISSING"), "Ett okänt order-id ska returnera null.");
    }

    private RepairOrder createRepairOrder(String id) {
        Customer customer = new Customer("Testkund", "test@example.com", "0700000000");
        Bike bike = new Bike("TestBrand", "TestModel", "TEST-1");
        return new RepairOrder(id, customer, bike, "Cykeln startar inte", LocalDate.of(2026, 5, 3));
    }
}
