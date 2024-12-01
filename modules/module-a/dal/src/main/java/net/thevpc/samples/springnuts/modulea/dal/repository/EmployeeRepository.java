package net.thevpc.samples.springnuts.modulea.dal.repository;

import net.thevpc.samples.springnuts.modulea.dal.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
