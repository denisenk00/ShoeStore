package ua.edu.j2ee.shoestore.exceptions;

public class DaoRuntimeException extends RuntimeException{
    public DaoRuntimeException() {
    }

    public DaoRuntimeException(String message) {
        super(message);
    }
}
