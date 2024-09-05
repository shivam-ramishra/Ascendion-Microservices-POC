package base.exception;

public class NoClientsFoundException extends RuntimeException {
    public NoClientsFoundException(String msg) {
        super(msg);
    }
}
