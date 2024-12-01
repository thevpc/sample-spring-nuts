package net.thevpc.samples.springnuts.core.dal.repository;

import net.thevpc.samples.springnuts.core.dal.entity.AppLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppLogEntityRepository extends JpaRepository<AppLogEntity, Long> {
}
