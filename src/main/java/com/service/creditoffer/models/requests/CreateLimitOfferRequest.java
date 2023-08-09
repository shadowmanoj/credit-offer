package com.service.creditoffer.models.requests;

import com.service.creditoffer.constants.LimitOfferType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CreateLimitOfferRequest {

    @NotNull
    private Long accountId;
    @NotNull
    private LimitOfferType limitType;
    @NotNull
    private Float newLimit;
    @NotNull
    private Date offerActivationTime;
    @NotNull
    private Date offerExpiryTime;
}
