package com.mommin.system.account.service.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class CreateCustomerDto {

    @Size(min = 1, max = 255, message= "name must be greater than 0 and less than 255")
    @NotNull(message = "name is required")
    private final String name;

    @Size(min = 1, max = 255, message= "surname size must be greater than 0 and less than 255")
    @NotNull(message = "surname is required")
    private final String surname;
}
