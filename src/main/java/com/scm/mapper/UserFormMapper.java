package com.scm.mapper;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface UserFormMapper {

    UserFormMapper ins = Mappers.getMapper(UserFormMapper.class);

    User userFormToUser(UserForm userForm);
}
