package util;

public class PersistenceException extends Exception {
    public PersistenceException(String message) {
        super(message);
    }

    @FunctionalInterface
    public interface PersistenceExceptionConsumer {
        void execute() throws PersistenceException;
    }

    public static void PersistenceException(PersistenceExceptionConsumer checker) {
        try {
            checker.execute();
        } catch (PersistenceException e) {
            System.err.println(e.getMessage());
        }
    }
}
