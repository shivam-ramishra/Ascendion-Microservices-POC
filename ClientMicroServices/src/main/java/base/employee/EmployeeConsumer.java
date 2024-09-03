package base.employee;

import base.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "EMPLOYEE-SERVICE")
public interface EmployeeConsumer {

    @GetMapping("/api/employee/clientId/{clientId}")
    List<Employee> findEmployeeByClientId(@PathVariable String clientId);
}
