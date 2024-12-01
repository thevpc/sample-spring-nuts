package net.thevpc.samples.springnuts.modulea.service.impl.converter;

import net.thevpc.samples.springnuts.modulea.dal.entity.EmployeeEntity;
import net.thevpc.samples.springnuts.modulea.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeConverter {
    public EmployeeConverter INSTANCE = Mappers.getMapper(EmployeeConverter.class);
    public EmployeeEntity toEntity(Employee e);
    public Employee fromEntity(EmployeeEntity e);
}
