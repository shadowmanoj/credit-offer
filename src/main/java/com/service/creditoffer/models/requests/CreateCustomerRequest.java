package com.service.creditoffer.models.requests;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CreateCustomerRequest {

    @NotNull
    private String name;

    @NotNull
    @Min(1)
    @Max(100)
    private int age;
}
