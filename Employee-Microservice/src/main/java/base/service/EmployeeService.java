package base.service;

import base.dto.Employee;
import base.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {

    public void addEmployee(Employee emp);

    public void updateEmployee(Employee emp);

    public void deleteEmployee(long employeeId) throws EmployeeNotFoundException;

    public List<Employee> findAll();

    public Employee findEmployeeById(long employeeId) throws EmployeeNotFoundException;
}
