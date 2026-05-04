package bikerepair.dto;

/**
 * Oföränderlig kund- och cykeldata som returneras från controller till view.
 */
public class CustomerDTO {
    private final String name;
    private final String email;
    private final String phone;
    private final String bikeBrand;
    private final String bikeModel;
    private final String bikeSerialNo;

    /**
     * Skapar en ny kund-DTO.
     *
     * @param name Kundens namn.
     * @param email Kundens e-postadress.
     * @param phone Kundens telefonnummer.
     * @param bikeBrand Kundens cykelmärke.
     * @param bikeModel Kundens cykelmodell.
     * @param bikeSerialNo Kundens cykels serienummer.
     */
    public CustomerDTO(String name, String email, String phone, String bikeBrand, String bikeModel,
            String bikeSerialNo) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bikeBrand = bikeBrand;
        this.bikeModel = bikeModel;
        this.bikeSerialNo = bikeSerialNo;
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

    /**
     * Hämtar kundens cykelmärke.
     *
     * @return Kundens cykelmärke.
     */
    public String getBikeBrand() {
        return bikeBrand;
    }

    /**
     * Hämtar kundens cykelmodell.
     *
     * @return Kundens cykelmodell.
     */
    public String getBikeModel() {
        return bikeModel;
    }

    /**
     * Hämtar kundens cykels serienummer.
     *
     * @return Kundens cykels serienummer.
     */
    public String getBikeSerialNo() {
        return bikeSerialNo;
    }

    /**
     * Hämtar en läsbar beskrivning av denna DTO.
     *
     * @return En läsbar beskrivning av denna DTO.
     */
    @Override
    public String toString() {
        return "CustomerDTO{namn='" + name + "', epost='" + email + "', telefon='" + phone + "', cykel='"
                + bikeBrand + " " + bikeModel + "', cykelSerienummer='" + bikeSerialNo + "'}";
    }
}
