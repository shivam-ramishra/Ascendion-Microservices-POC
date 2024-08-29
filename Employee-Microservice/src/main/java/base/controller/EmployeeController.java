package base.controller;

import base.dto.Employee;
import base.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public void addEmployee(@RequestBody Employee emp) {
        employeeService.addEmployee(emp);
    }

    @PutMapping("/update")
    public void updateEmployee(@RequestBody Employee emp) {
        employeeService.updateEmployee(emp);
    }

    @DeleteMapping("/delete/{employeeId}")
    public void deleteEmployee(@PathVariable long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/findAll")
    public List<Employee> findAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/id/{employeeId}")
    public Employee findEmployeeById(@PathVariable long employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }
}
