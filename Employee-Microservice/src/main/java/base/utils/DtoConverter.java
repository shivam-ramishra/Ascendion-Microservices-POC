package base.utils;

import base.dto.EmployeeEntity;
import base.model.Employee;
import org.springframework.beans.BeanUtils;

public class DtoConverter {

    private DtoConverter() {
    }

    public static EmployeeEntity modelToEntity(Employee empModel) {
        EmployeeEntity entity = EmployeeEntity.builder().build();
        BeanUtils.copyProperties(empModel, entity);
        return entity;
    }

    public static Employee entityToModel(EmployeeEntity empEntity) {
        Employee model = Employee.builder().build();
        BeanUtils.copyProperties(model, empEntity);
        return model;
    }
}
