package bikerepair.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bikerepair.model.Bike;
import bikerepair.model.Customer;

/**
 * Tester för CustomerRegistry.
 */
public class CustomerRegistryTest {
    private CustomerRegistry registry;

    /**
     * Skapar ett nytt registry före varje test.
     */
    @BeforeEach
    public void setUp() {
        registry = new CustomerRegistry();
    }

    /**
     * Testar att en känd kund kan hittas.
     */
    @Test
    public void testFindExistingCustomer() {
        Customer customer = registry.findCustomer("0701234567");
        assertNotNull(customer, "Exempelkunden ska finnas.");
        assertEquals("Elias Karch", customer.getName(), "Den hittade kunden ska ha förväntat namn.");
    }

    /**
     * Testar att en okänd kund returnerar null.
     */
    @Test
    public void testFindMissingCustomerReturnsNull() {
        assertNull(registry.findCustomer("0000000000"), "Ett okänt telefonnummer ska returnera null.");
    }

    /**
     * Testar att en känd cykel kan hittas.
     */
    @Test
    public void testFindExistingBike() {
        Bike bike = registry.findBike("BIKE-1001");
        assertNotNull(bike, "Exempelcykeln ska finnas.");
        assertEquals("Monark", bike.getBrand(), "Den hittade cykeln ska ha förväntat märke.");
    }

    /**
     * Testar att en okänd cykel returnerar null.
     */
    @Test
    public void testFindMissingBikeReturnsNull() {
        assertNull(registry.findBike("MISSING"), "Ett okänt serienummer ska returnera null.");
    }

    /**
     * Testar att tillagd kund- och cykeldata kan hittas.
     */
    @Test
    public void testAddCustomerWithBikeMakesDataFindable() {
        registry.addCustomerWithBike(new Customer("Ny kund", "new@example.com", "0711111111"),
                new Bike("Cube", "Kathmandu Hybrid", "BIKE-3003"));
        assertEquals("Ny kund", registry.findCustomer("0711111111").getName(),
                "Den tillagda kunden ska hittas med telefonnummer.");
        assertEquals("Cube", registry.findBike("BIKE-3003").getBrand(),
                "Den tillagda cykeln ska hittas med serienummer.");
    }

    /**
     * Testar grenen där kunden saknar registrerad cykel.
     */
    @Test
    public void testFindFirstBikeForMissingCustomerReturnsNull() {
        assertNull(registry.findFirstBikeForCustomer("0711111111"),
                "En kund utan sparade cyklar ska returnera null.");
    }
}
