package kz.aitu.tc.exceptionHandler.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    /*
    Class name speaks for itself.
    This exception created to handle NOT_FOUND requests.
    */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}