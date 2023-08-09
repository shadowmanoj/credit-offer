package com.service.creditoffer.models.requests;

import com.service.creditoffer.constants.LimitOfferStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateLimitOfferRequest {
    @NotNull
    private Long limitOfferId;
    @NotNull
    private LimitOfferStatus status;
}
