package base.exception;

import base.model.Client;

import java.util.ArrayList;
import java.util.List;

public class NoClientsFoundException extends Exception {
    public List<Client> client;
    public NoClientsFoundException(List<Client> client) {
        this.client = new ArrayList<Client>();
    }
    public String getMessage() {
        return "No clients found";
    }
}
