package bikerepair.model;

import java.time.LocalDate;

/**
 * Representerar resultatet av en diagnos av en cykel.
 */
public class DiagnosticReport {
    private final String result;
    private final LocalDate date;

    /**
     * Skapar en ny diagnosrapport med dagens datum.
     *
     * @param result Diagnosresultatet.
     */
    public DiagnosticReport(String result) {
        this(result, LocalDate.now());
    }

    /**
     * Skapar en ny diagnosrapport.
     *
     * @param result Diagnosresultatet.
     * @param date Datumet då diagnosen gjordes.
     */
    public DiagnosticReport(String result, LocalDate date) {
        this.result = result;
        this.date = date;
    }

    /**
     * Hämtar diagnosresultatet.
     *
     * @return Diagnosresultatet.
     */
    public String getResult() {
        return result;
    }

    /**
     * Hämtar diagnosdatumet.
     *
     * @return Diagnosdatumet.
     */
    public LocalDate getDate() {
        return date;
    }
}
