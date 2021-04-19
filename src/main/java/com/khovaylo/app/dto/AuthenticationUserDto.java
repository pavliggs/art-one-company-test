package com.khovaylo.app.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Pavel Khovaylo
 */
@Data
public class AuthenticationUserDto {

    @NotBlank
    @Size(max = 100)
    private String login;

    @NotBlank
    @Size(max = 255)
    private String password;
}
