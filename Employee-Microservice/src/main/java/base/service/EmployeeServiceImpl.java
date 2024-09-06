package base.service;

import base.client.ClientConsumer;
import base.dto.EmployeeEntity;
import base.exception.ClientNotFoundException;
import base.exception.EmployeeNotFoundException;
import base.exception.InvalidInputException;
import base.model.Client;
import base.model.Employee;
import base.repo.EmployeeRepo;
import base.utils.DtoConverter;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static base.utils.DtoConverter.entityToModel;
import static base.utils.DtoConverter.modelToEntity;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepo employeeRepo;

    private ClientConsumer clientConsumer;

    @Override
    public Employee addOrUpdateEmployee(Employee emp) {
        if (emp != null && emp.getClientName() != null) {
            try {
                Client client = clientConsumer.findClientByClientName(emp.getClientName());
                if (client == null) {
                    throw new ClientNotFoundException(emp.getClientName());
                }
            } catch (Exception e) {
                log.error("Error occurred while fetching client details");
                throw new ClientNotFoundException("Client not found.");
            }
            try {
                EmployeeEntity empEntity = modelToEntity(emp);
                EmployeeEntity savedEmp = employeeRepo.save(empEntity);
                Employee empModel = entityToModel(savedEmp);

                log.info("Employee Saved to DB, emp data: {}", empModel);
                return empModel;
            } catch (Exception e) {
                throw new EmployeeNotFoundException("Something went wrong to save or update employee Id: " + emp.getEmployeeId() + " with error: " + e.getMessage());
            }
        }
        throw new InvalidInputException("Couldn't save employee details. Bad Request");
    }

    @Override
    public boolean deleteEmployee(long employeeId) throws EmployeeNotFoundException {
        return employeeRepo.findById(employeeId)
                .map(employeeEntity -> {
                    try {
                        employeeRepo.deleteById(employeeEntity.getEmployeeId());
                        log.info("Employee deleted from DB, empId:: {} ", employeeId);
                        return true;
                    } catch (Exception e) {
                        log.error("Something went wrong to delete employee:: {}, with error:: {}", employeeEntity, e.getMessage());
                        return false;
                    }
                })
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with with Id: " + employeeId));
    }

    @Override
    public List<Employee> findAll() {
        try {
            return employeeRepo.findAll()
                    .stream()
                    .map(DtoConverter::entityToModel)
                    .toList();
        } catch (Exception e) {
            throw new EmployeeNotFoundException("something went wrong to fetch Employees with Error: " + e.getMessage());
        }
    }

    @Override
    public Employee findEmployeeById(long employeeId) throws EmployeeNotFoundException {
        return employeeRepo.findById(employeeId)
                .map(DtoConverter::entityToModel)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with with Id: " + employeeId));
    }

    @Override
    public List<Employee> findByClientName(String clientName) {
        try {
            return employeeRepo.findByClientName(clientName)
                    .stream()
                    .map(DtoConverter::entityToModel)
                    .toList();
        } catch (Exception e) {
            log.error("something went wrong to fetch Employees for Client ID with Error:: {}", e.getMessage());
            throw new ClientNotFoundException("Ugh! Client Not Found");
        }
    }
}
