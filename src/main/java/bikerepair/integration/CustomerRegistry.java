package bikerepair.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bikerepair.model.Bike;
import bikerepair.model.Customer;

/**
 * Lagrar kund- och cykeldata i minnet.
 */
public class CustomerRegistry {
    private final Map<String, Customer> customersByPhone = new HashMap<>();
    private final Map<String, Bike> bikesBySerialNumber = new HashMap<>();
    private final Map<String, List<Bike>> bikesByCustomerPhone = new HashMap<>();

    /**
     * Skapar ett nytt kundregistry med exempeldata.
     */
    public CustomerRegistry() {
        Customer customer = new Customer("Elias Karch", "elias.karch@example.com", "0701234567");
        Bike bike = new Bike("Monark", "Elcykel", "BIKE-1001");
        addCustomerWithBike(customer, bike);
        addCustomerWithBike(new Customer("Havar Karakas", "havar.karakas@example.com", "0707654321"),
                new Bike("Specialized", "Turbo Vado", "BIKE-2002"));
    }

    /**
     * Hämtar en kund med telefonnummer.
     *
     * @param phoneNumber Telefonnumret att söka efter.
     * @return Den matchande kunden, eller null om ingen finns.
     */
    public Customer findCustomer(String phoneNumber) {
        return customersByPhone.get(phoneNumber);
    }

    /**
     * Hämtar en cykel med serienummer.
     *
     * @param bikeSerialNo Serienumret att söka efter.
     * @return Den matchande cykeln, eller null om ingen finns.
     */
    public Bike findBike(String bikeSerialNo) {
        return bikesBySerialNumber.get(bikeSerialNo);
    }

    /**
     * Hämtar den första cykeln som ägs av kunden med det angivna telefonnumret.
     *
     * @param phoneNumber Kundens telefonnummer.
     * @return Kundens första cykel, eller null om ingen cykel finns.
     */
    public Bike findFirstBikeForCustomer(String phoneNumber) {
        List<Bike> bikes = bikesByCustomerPhone.get(phoneNumber);
        if (bikes == null || bikes.isEmpty()) {
            return null;
        }
        return bikes.get(0);
    }

    /**
     * Lägger till en kund och en cykel i registryt.
     *
     * @param customer Kunden som ska läggas till.
     * @param bike Kundens cykel.
     */
    public void addCustomerWithBike(Customer customer, Bike bike) {
        customersByPhone.put(customer.getPhone(), customer);
        bikesBySerialNumber.put(bike.getSerialNumber(), bike);
        bikesByCustomerPhone.computeIfAbsent(customer.getPhone(), phone -> new ArrayList<>()).add(bike);
    }
}
