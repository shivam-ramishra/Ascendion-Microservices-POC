package base.service;

import base.dto.EmployeeEntity;
import base.exception.EmployeeNotFoundException;
import base.model.Employee;
import base.repo.EmployeeRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Employee addOrUpdateEmployee(Employee emp) {
        try {
            EmployeeEntity entity = EmployeeEntity.builder().build();
            BeanUtils.copyProperties(emp, entity);

            EmployeeEntity savedEmp = employeeRepo.save(entity);

            Employee empModel = Employee.builder().build();
            BeanUtils.copyProperties(savedEmp, empModel);

            System.out.println("Employee Saved to DB: " + empModel);
            return empModel;

        } catch (Exception e) {
            System.out.println("Something went wrong to save|update employee:: " + emp + ", with error: " + e.getMessage());
            return null;
        }
    }


    @Override
    public boolean deleteEmployee(long employeeId) throws EmployeeNotFoundException {
        Employee emp = findEmployeeById(employeeId);
        if (emp != null && emp.getEmployeeId() != null) {
            try {
                employeeRepo.deleteById(emp.getEmployeeId());
                System.out.println("Employee deleted from DB, empId: " + employeeId);
                return true;
            } catch (Exception e) {
                System.out.println("Something went wrong to delete employee:: " + emp + ", with error: " + e.getMessage());
                return false;
            }
        }
        throw new EmployeeNotFoundException("Employee not found with ID :" + employeeId);
    }

    @Override
    public List<Employee> findAll() {
        try {
            return employeeRepo.findAll()
                    .stream()
                    .map(entity -> {
                        Employee empModel = Employee.builder().build();
                        BeanUtils.copyProperties(entity, empModel);
                        return empModel;
                    })
                    .toList();
        } catch (Exception e) {
            System.out.println("something went wrong to fetch Employees with Error: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Employee findEmployeeById(long employeeId) throws EmployeeNotFoundException {
        return employeeRepo.findById(employeeId)
                .map(entity -> {
                    Employee empModel = Employee.builder().build();
                    BeanUtils.copyProperties(entity, empModel);
                    return empModel;
                })
                .orElseThrow(() -> new EmployeeNotFoundException(" with Id: " + employeeId));
    }
}
