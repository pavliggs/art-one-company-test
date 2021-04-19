package com.khovaylo.app.dto.converter.impl;

import com.khovaylo.app.dto.AuthenticationUserDto;
import com.khovaylo.app.dto.converter.ConverterToModel;
import com.khovaylo.app.model.User;
import org.springframework.stereotype.Component;

/**
 * @author Pavel Khovaylo
 */
@Component
public class AuthenticationUserConverter implements ConverterToModel<User, AuthenticationUserDto> {
    @Override
    public User toModel(AuthenticationUserDto dto) {
        if (dto == null) return null;

        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());

        return user;
    }
}
