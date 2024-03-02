package kz.aitu.tc.exceptionHandler.exceptions;

public class AlreadyExistsException extends RuntimeException {
    /*
    Class name speaks for itself.
    This exception created to handle CONFLICT requests.
    */
    public AlreadyExistsException(String message) {
        super(message);
    }
}