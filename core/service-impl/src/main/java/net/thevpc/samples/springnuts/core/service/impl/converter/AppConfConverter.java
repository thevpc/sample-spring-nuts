package net.thevpc.samples.springnuts.core.service.impl.converter;

import net.thevpc.samples.springnuts.core.dal.entity.AppConfEntity;
import net.thevpc.samples.springnuts.core.model.entity.AppConf;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppConfConverter {
    public AppConfConverter INSTANCE = Mappers.getMapper(AppConfConverter.class);
    public AppConfEntity toEntity(AppConf u);
    public AppConf fromEntity(AppConfEntity u);
}
