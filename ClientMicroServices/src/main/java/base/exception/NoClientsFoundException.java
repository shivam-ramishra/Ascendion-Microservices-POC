package base.exception;

import base.model.Client;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class NoClientsFoundException extends RuntimeException {
    private final String msg;

    public NoClientsFoundException(String msg) {
        this.msg = msg;
    }
}
