package se.kth.iv1350.repairbike.integration;

/**
 * Kastas för att simulera att databasen inte kan nås (t.ex. servern är nere).
 * Detta är ett unchecked undantag eftersom det indikerar ett hårdvaru- eller systemfel.
 */
public class DatabaseFailureException extends RuntimeException {

    /**
     * Skapar en ny instans med ett felmeddelande.
     * @param message Beskrivning av det tekniska felet.
     */
    public DatabaseFailureException(String message) {
        super(message);
    }
}