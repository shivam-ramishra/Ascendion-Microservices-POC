package base.service;

import base.controller.EmployeeController;
import base.dao.EmployeeRepo;
import base.dto.Employee;
import base.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepo employeeRepo;


    @Override
    public void addEmployee(Employee emp) {
        employeeRepo.save(emp);
    }

    @Override
    public void updateEmployee(Employee emp) {
        Optional<Employee> employee = employeeRepo.findById(emp.getEmployeeId());
        employeeRepo.save(emp);
    }

    @Override
    public void deleteEmployee(long employeeId) throws EmployeeNotFoundException {
        Employee emp = findEmployeeById(employeeId);
        employeeRepo.deleteById(employeeId);
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = employeeRepo.findAll();
        if(employees.isEmpty()){
            throw new EmployeeNotFoundException("Employee not found");
        }
        return employeeRepo.findAll();
    }

    @Override
    public Employee findEmployeeById(long employeeId) throws EmployeeNotFoundException  {
        Optional<Employee> emp = employeeRepo.findById(employeeId);
        if(emp.isEmpty()) {
            throw new EmployeeNotFoundException(" with Id: " + employeeId);
        }
        return emp.get();
    }
}
