package base.controller;

import base.exception.EmployeeNotFoundException;
import base.model.Employee;
import base.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("/addOrUpdate")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee emp) {
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
