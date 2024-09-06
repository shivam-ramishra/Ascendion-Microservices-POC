package base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlingController {
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> handleClientNotFoundException(ClientNotFoundException e) {
        return new ResponseEntity<>("Client not found.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoClientsFoundException.class)
    public ResponseEntity<String> handleNoClientFoundException(NoClientsFoundException e) {
        return new ResponseEntity<>("No clients found.", HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApplicationException> applicationException(ApplicationException e) {
        return new ResponseEntity<>(e, e.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleInternalServerErrorException(Exception e) {
        return new ResponseEntity<>("Ugh! Something went wrong....", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
