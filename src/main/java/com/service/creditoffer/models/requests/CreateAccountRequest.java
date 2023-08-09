package com.service.creditoffer.models.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateAccountRequest {
    @NotNull
    private Long customerId;

    @NotNull
    private Float accountLimit;

    @NotNull
    private Float perTransactionLimit;

}
