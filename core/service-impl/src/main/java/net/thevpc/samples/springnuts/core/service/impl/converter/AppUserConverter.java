package net.thevpc.samples.springnuts.core.service.impl.converter;

import net.thevpc.samples.springnuts.core.dal.entity.AppUserEntity;
import net.thevpc.samples.springnuts.core.model.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppUserConverter {
    public AppUserConverter INSTANCE = Mappers.getMapper(AppUserConverter.class);
    public AppUserEntity toEntity(AppUser u);
    public AppUser fromEntity(AppUserEntity u);
    static AppUser fromEntityNoPassword(AppUserEntity u){
        AppUser e = INSTANCE.fromEntity(u);
        if(e!=null){
            e.setPassword(null);
        }
        return e;
    }
}
