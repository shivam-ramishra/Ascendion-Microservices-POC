package base.controller;

import base.exception.EmployeeNotFoundException;
import base.model.Employee;
import base.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addOrUpdate")
    public ResponseEntity<?> addEmployee(@RequestBody Employee emp) {
        Employee employee = employeeService.addOrUpdateEmployee(emp);

        return (employee != null)
                ? new ResponseEntity<>(employee, HttpStatus.CREATED) :
                new ResponseEntity<>("Couldn't save employee details.", HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        boolean isDeleted = employeeService.deleteEmployee(employeeId);

        if (isDeleted) {
            return ResponseEntity.ok("Employee deleted successfully");
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/findAll")
    public List<Employee> findAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/id/{employeeId}")
    public Employee findEmployeeById(@PathVariable long employeeId) throws EmployeeNotFoundException {
        return employeeService.findEmployeeById(employeeId);
    }


    @GetMapping("/clientId/{clientId}")
    public List<Employee> findEmployeeByClientId(@PathVariable String clientId) {
        return employeeService.findByClientName(clientId);
    }
}
