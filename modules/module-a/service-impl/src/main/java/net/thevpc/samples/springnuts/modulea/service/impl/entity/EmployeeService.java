package net.thevpc.samples.springnuts.modulea.service.impl.entity;

import net.thevpc.nuts.util.NAssert;
import net.thevpc.samples.springnuts.modulea.dal.entity.EmployeeEntity;
import net.thevpc.samples.springnuts.modulea.dal.repository.EmployeeRepository;
import net.thevpc.samples.springnuts.modulea.model.Employee;
import net.thevpc.samples.springnuts.modulea.service.impl.converter.EmployeeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public Employee addEmployee(Employee employee) {
        NAssert.requireTrue(employee != null, "employee must not be null");
        EmployeeEntity employeeEntity = EmployeeConverter.INSTANCE.toEntity(employee);
        employeeRepository.save(employeeEntity) ;
        employee.setId(employeeEntity.getId());
        return employee;
    }

}
