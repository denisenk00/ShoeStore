package ua.edu.j2ee.shoestore.exceptions;

public class RestServiceException extends RuntimeException{
    private String format;

    public RestServiceException(String message, String format) {
        super(message);
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
