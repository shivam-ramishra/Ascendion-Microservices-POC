package base.exception;

public class ClientNotFoundException extends Exception {
    private int id;

    public ClientNotFoundException(int id) {
        this.id = id;
    }

    public ClientNotFoundException(String name) {
        super(name);
    }

    @Override
    public String getMessage() {
        return "Client with id " + id + " not found";
    }
}

