package bikerepair.model;

/**
 * Representerar en kund som äger en eller flera elcyklar.
 */
public class Customer {
    private final String name;
    private final String email;
    private final String phone;

    /**
     * Skapar en ny kund.
     *
     * @param name Kundens namn.
     * @param email Kundens e-postadress.
     * @param phone Kundens telefonnummer.
     */
    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Hämtar kundens namn.
     *
     * @return Kundens namn.
     */
    public String getName() {
        return name;
    }

    /**
     * Hämtar kundens e-postadress.
     *
     * @return Kundens e-postadress.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Hämtar kundens telefonnummer.
     *
     * @return Kundens telefonnummer.
     */
    public String getPhone() {
        return phone;
    }
}
