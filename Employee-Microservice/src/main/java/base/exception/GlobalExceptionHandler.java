package base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EmployeeNotFoundException.class})
    public ResponseEntity<Map<String, String>> handleEmployeeNotFoundException(EmployeeNotFoundException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("No Existing Employee : ", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler({ClientNotFoundException.class})
    public ResponseEntity<Map<String, String>> handleClientNotFoundException(ClientNotFoundException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("No Existing Client : ", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("Ugh! Something went wrong... ", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
