package com.service.creditoffer.services;

import com.service.creditoffer.entities.LimitOffer;
import com.service.creditoffer.models.requests.CreateLimitOfferRequest;
import com.service.creditoffer.models.requests.UpdateLimitOfferRequest;

import java.util.Date;
import java.util.List;

public interface LimitOfferService {

    String createLimitOffer(CreateLimitOfferRequest createLimitOfferRequest);

    List<LimitOffer> getActiveLimitOffers(Long accountId, Date activeDate);

    String updateLimitOrderStatus(UpdateLimitOfferRequest updateLimitOfferRequest);
}
