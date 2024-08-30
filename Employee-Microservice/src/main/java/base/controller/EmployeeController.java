package base.controller;

import base.dto.Employee;
import base.exception.EmployeeNotFoundException;
import base.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee emp) {
        employeeService.addEmployee(emp);
        return ResponseEntity.ok("Employee added successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee emp) {
        employeeService.updateEmployee(emp);
        return ResponseEntity.ok("Employee updated successfully");
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long employeeId) throws EmployeeNotFoundException {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @GetMapping("/findAll")
    public List<Employee> findAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/id/{employeeId}")
    public Employee findEmployeeById(@PathVariable long employeeId) throws EmployeeNotFoundException {
            return employeeService.findEmployeeById(employeeId);
    }
}
