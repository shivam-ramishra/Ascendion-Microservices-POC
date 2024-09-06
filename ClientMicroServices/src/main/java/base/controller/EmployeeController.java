package base.controller;

import base.employee.EmployeeConsumer;
import base.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeConsumer empConsumer;

    @Autowired
    private RestClient restClient;

    @GetMapping("/findEmployeesForClient/{clientId}")
    public ResponseEntity<List<Employee>> findEmployeesForClient(@PathVariable String clientId) {
        return new ResponseEntity<>(empConsumer.findEmployeeByClientId(clientId), HttpStatus.OK);
    }

    @GetMapping("/findEmployeeDetails/{employeeId}")
    public ResponseEntity<Object> findEmployeeDetails(@PathVariable String employeeId) {
        try {
            Employee employee = restClient.get()
                    .uri("/id/{employeeId}", employeeId)
                    .retrieve()
                    .body(Employee.class);
            if (employee == null) {
                return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}