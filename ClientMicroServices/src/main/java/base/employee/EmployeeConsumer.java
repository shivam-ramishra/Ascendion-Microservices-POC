package base.employee;

import base.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "EMPLOYEE-SERVICE", url = "http://localhost:8889/api/employee")
public interface EmployeeConsumer {

    @GetMapping("/clientId/{clientId}")
    List<Employee> findEmployeeByClientId(@PathVariable String clientId);
}