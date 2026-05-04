package bikerepair.model;

/**
 * Representerar möjliga statusar för en reparationsorder.
 */
public enum RepairOrderState {
    /**
     * Reparationsordern har skapats men ingen diagnos har lagts till.
     */
    NEWLY_CREATED,

    /**
     * Reparationsordern är redo för att kunden ska acceptera eller avvisa den.
     */
    READY_FOR_APPROVAL,

    /**
     * Kunden har accepterat reparationsordern.
     */
    ACCEPTED,

    /**
     * Kunden har avvisat reparationsordern.
     */
    REJECTED,

    /**
     * Reparationsarbetet är slutfört.
     */
    COMPLETED,

    /**
     * Reparationsordern har betalats.
     */
    PAYED
}
