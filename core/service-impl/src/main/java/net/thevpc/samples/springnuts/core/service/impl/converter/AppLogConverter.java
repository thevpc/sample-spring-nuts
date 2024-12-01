package net.thevpc.samples.springnuts.core.service.impl.converter;

import net.thevpc.samples.springnuts.core.dal.entity.AppLogEntity;
import net.thevpc.samples.springnuts.core.model.entity.AppLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppLogConverter {
    public AppLogConverter INSTANCE = Mappers.getMapper(AppLogConverter.class);
    public AppLogEntity toEntity(AppLog u);
    public AppLog fromEntity(AppLogEntity u);
}
