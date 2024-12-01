package net.thevpc.samples.springnuts.core.service.impl.converter;

import net.thevpc.samples.springnuts.core.dal.entity.AppUserRoleEntity;
import net.thevpc.samples.springnuts.core.model.entity.AppUserRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppUserRoleConverter {
    public AppUserRoleConverter INSTANCE = Mappers.getMapper(AppUserRoleConverter.class);
    public AppUserRoleEntity toEntity(AppUserRole u);
    public AppUserRole fromEntity(AppUserRoleEntity u);
}
