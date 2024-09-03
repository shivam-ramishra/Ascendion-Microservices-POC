package base.exception;

public class ClientNotFoundException extends Exception {
    private int id;
    private String name;

    public ClientNotFoundException(int id) {
        this.id = id;
    }

    public ClientNotFoundException(String name) {
        this.name = name;
    }

    public String getMessage() {
        return "Client with id " + id + " not found";
    }
}

