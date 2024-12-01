package net.thevpc.samples.springnuts.core.dal.repository;

import net.thevpc.samples.springnuts.core.dal.entity.AppUserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRoleEntityRepository extends JpaRepository<AppUserRoleEntity, Long> {
  Optional<AppUserRoleEntity> findByName(String name);
}
