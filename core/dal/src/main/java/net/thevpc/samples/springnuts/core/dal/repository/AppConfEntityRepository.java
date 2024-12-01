package net.thevpc.samples.springnuts.core.dal.repository;

import net.thevpc.samples.springnuts.core.dal.entity.AppConfEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppConfEntityRepository extends JpaRepository<AppConfEntity, Long> {
    List<AppConfEntity> findByTenantAndGroupNameAndName(String tenant, String groupName, String name);
}
