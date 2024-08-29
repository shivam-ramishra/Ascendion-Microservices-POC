package base.service;

import base.dao.EmployeeRepo;
import base.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        employeeRepo.save(emp);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        employeeRepo.deleteById(employeeId);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee findEmployeeById(long employeeId) {
        return employeeRepo.findById(employeeId).get();
    }
}
