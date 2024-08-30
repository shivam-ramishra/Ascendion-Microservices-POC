package base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(EmployeeNotFoundException.class)
        public ResponseEntity<Map<String, String>> handleEmployeeNotFoundException(EmployeeNotFoundException e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("No Existing Employee : ", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
          }

//    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(EmployeeNotFoundException.class)
//    public ErrorResponse handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
//        return new ErrorResponse(400, ex.getMessage());
//    }

}
