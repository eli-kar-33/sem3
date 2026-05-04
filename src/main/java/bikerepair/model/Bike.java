package bikerepair.model;

/**
 * Representerar en elcykel som ägs av en kund.
 */
public class Bike {
    private final String brand;
    private final String model;
    private final String serialNumber;

    /**
     * Skapar en ny cykel.
     *
     * @param brand Cykelns märke.
     * @param model Cykelns modell.
     * @param serialNumber Cykelns serienummer.
     */
    public Bike(String brand, String model, String serialNumber) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    /**
     * Hämtar cykelns märke.
     *
     * @return Cykelns märke.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Hämtar cykelns modell.
     *
     * @return Cykelns modell.
     */
    public String getModel() {
        return model;
    }

    /**
     * Hämtar cykelns serienummer.
     *
     * @return Cykelns serienummer.
     */
    public String getSerialNumber() {
        return serialNumber;
    }
}
