package com.khovaylo.app.dto.converter.impl;

import com.khovaylo.app.dto.UserDto;
import com.khovaylo.app.dto.converter.ConverterToDto;
import com.khovaylo.app.model.User;
import org.springframework.stereotype.Component;

/**
 * @author Pavel Khovaylo
 */
@Component
public class UserDtoConverter implements ConverterToDto<User, UserDto> {
    @Override
    public UserDto toDto(User model) {
        return new UserDto(model.getLogin());
    }
}
