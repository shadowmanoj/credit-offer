package com.service.creditoffer.services;

import com.service.creditoffer.entities.Account;
import com.service.creditoffer.models.requests.CreateAccountRequest;

public interface AccountService {

    Account getAccountById(Long accountId);

    String createAccount(CreateAccountRequest createAccountRequest);

    void updateAccountLimitOffer(Long accountId, String limitType, Float newLimit);
}
