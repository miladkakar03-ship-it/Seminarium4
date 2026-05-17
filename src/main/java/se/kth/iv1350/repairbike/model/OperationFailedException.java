package se.kth.iv1350.repairbike.model;

/**
 * Kastas när en systemoperation misslyckas på grund av underliggande tekniska fel,
 * till exempel när databasservicen inte är tillgänglig.
 */
public class OperationFailedException extends Exception {
    /**
     * Skapar en ny instans med ett beskrivande felmeddelande och den underliggande orsaken.
     * * @param message Beskrivning av felet avsett för applikationen.
     * @param cause Den faktiska underliggande system-exceptionen som utlöste felet.
     */
    public OperationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}