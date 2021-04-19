package com.khovaylo.app.rest;

import com.khovaylo.app.dto.AuthenticationUserDto;
import com.khovaylo.app.dto.UserDto;
import com.khovaylo.app.dto.converter.ConverterToDto;
import com.khovaylo.app.dto.converter.ConverterToModel;
import com.khovaylo.app.model.User;
import com.khovaylo.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Pavel Khovaylo
 */
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;
    private final ConverterToModel<User, AuthenticationUserDto> converterToModel;
    private final ConverterToDto<User, UserDto> converterToDto;

    public UserRestController(UserService userService, ConverterToModel<User, AuthenticationUserDto> converterToModel, ConverterToDto<User, UserDto> converterToDto) {
        this.userService = userService;
        this.converterToModel = converterToModel;
        this.converterToDto = converterToDto;
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> get() {
        List<UserDto> dtoList = userService.get().stream().map(converterToDto::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationUserDto> create(@NotNull @RequestBody AuthenticationUserDto dto) {
        User user = converterToModel.toModel(dto);
        userService.create(user);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
