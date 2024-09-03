package base.controller;

import base.employee.EmployeeConsumer;
import base.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeConsumer empConsumer;


    @GetMapping("/findEmployeesForClient/{clientId}")
    public ResponseEntity<List<Employee>> findAllEmployeesForClient(@PathVariable String clientId) {
        return new ResponseEntity<>(empConsumer.findEmployeeByClientId(clientId), HttpStatus.OK);
    }


}
