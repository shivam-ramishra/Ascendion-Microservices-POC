package base.service;

import base.dto.EmployeeEntity;
import base.exception.ClientNotFoundException;
import base.exception.EmployeeNotFoundException;
import base.model.Employee;
import base.repo.EmployeeRepo;
import base.utils.DtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static base.utils.DtoConverter.entityToModel;
import static base.utils.DtoConverter.modelToEntity;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Employee addOrUpdateEmployee(Employee emp) {
        try {
            EmployeeEntity empEntity = modelToEntity(emp);
            EmployeeEntity savedEmp = employeeRepo.save(empEntity);
            Employee empModel = entityToModel(savedEmp);

            log.info("Employee Saved to DB: {}", empModel);
            return empModel;
        } catch (Exception e) {
            log.error("Something went wrong to save|update employee:: {} with error:: {}", emp, e.getMessage());
            return null;
        }
    }


    @Override
    public boolean deleteEmployee(long employeeId) throws EmployeeNotFoundException {
        Employee emp = findEmployeeById(employeeId);
        if (emp != null && emp.getEmployeeId() != null) {
            try {
                employeeRepo.deleteById(emp.getEmployeeId());
                log.info("Employee deleted from DB, empId:: {} ", employeeId);
                return true;
            } catch (Exception e) {
                log.error("Something went wrong to delete employee:: {}, with error:: {}", emp, e.getMessage());
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
                    .map(DtoConverter::entityToModel)
                    .toList();
        } catch (Exception e) {
            log.error("something went wrong to fetch Employees with Error:: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Employee findEmployeeById(long employeeId) throws EmployeeNotFoundException {
        return employeeRepo.findById(employeeId)
                .map(DtoConverter::entityToModel)
                .orElseThrow(() -> new EmployeeNotFoundException(" with Id: " + employeeId));
    }

    @Override
    public List<Employee> findByClientId(String clientId) {
        try {
            List<Employee> empList = employeeRepo.findByClientId(clientId)
                    .stream()
                    .map(DtoConverter::entityToModel)
                    .toList();

            if (empList.isEmpty())
                throw new ClientNotFoundException("Ugh! Client Not Found");

            return empList;
        } catch (ClientNotFoundException e) {
            log.error("Client not found:: {}", e.getMessage());
            throw new ClientNotFoundException("Client not found.");
        } catch (Exception e) {
            log.error("something went wrong to fetch Employees for Client ID with Error:: {}", e.getMessage());
            throw new RuntimeException("Ugh! Something went wrong");
        }
    }
}
