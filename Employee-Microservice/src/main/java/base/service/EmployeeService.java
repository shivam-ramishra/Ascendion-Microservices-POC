package base.service;

import base.exception.EmployeeNotFoundException;
import base.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addOrUpdateEmployee(Employee emp);

    boolean deleteEmployee(long employeeId) throws EmployeeNotFoundException;

    List<Employee> findAll();

    Employee findEmployeeById(long employeeId) throws EmployeeNotFoundException;

    List<Employee> findByClientName(String clientName);
}
