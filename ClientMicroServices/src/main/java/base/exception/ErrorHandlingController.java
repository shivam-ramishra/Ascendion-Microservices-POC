package base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlingController {
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorMessages> handleClientNotFoundException(ClientNotFoundException e){
        ErrorMessages errorMessages = new ErrorMessages(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoClientsFoundException.class)
    public ResponseEntity<ErrorMessages> handleNoClientFoundException(NoClientsFoundException e){
        ErrorMessages errorMessages = new ErrorMessages(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }
}
