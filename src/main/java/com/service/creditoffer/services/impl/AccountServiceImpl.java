package com.service.creditoffer.services.impl;

import com.service.creditoffer.constants.LimitOfferType;
import com.service.creditoffer.entities.Account;
import com.service.creditoffer.exceptions.BadRequestException;
import com.service.creditoffer.exceptions.NotFoundException;
import com.service.creditoffer.models.requests.CreateAccountRequest;
import com.service.creditoffer.repositories.AccountRepository;
import com.service.creditoffer.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author manoj.kumar
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountById(Long accountId){
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()) return account.get();
        throw new NotFoundException("Account not found with accountId : "+accountId);
    }

    public String createAccount(CreateAccountRequest createAccountRequest){
        Account account = new Account();
        account.setCustomerId(createAccountRequest.getCustomerId());
        account.setAccountLimit(createAccountRequest.getAccountLimit());
        account.setPerTransactionLimit(createAccountRequest.getPerTransactionLimit());
        try {
            accountRepository.save(account);
            return "Account created successfully";
        } catch (Exception e){
            throw new BadRequestException("Error in creating account : "+e.getMessage());
        }
    }

    public void updateAccountLimitOffer(Long accountId, String limitType, Float newLimit){
        Account account = this.getAccountById(accountId);
        if(account != null){
            switch (LimitOfferType.valueOf(limitType)){
                case ACCOUNT_LIMIT:
                    account.setLastAccountLimit(account.getAccountLimit());
                    account.setAccountLimit(newLimit);
                    account.setAccountLimitUpdateTime(new Date());
                    accountRepository.save(account);
                    break;
                case PER_TRANSACTION_LIMIT:
                    account.setLastPerTransactionLimit(account.getPerTransactionLimit());
                    account.setPerTransactionLimit(newLimit);
                    account.setPerTransactionLimitUpdateTime(new Date());
                    accountRepository.save(account);
                    break;
                default:
                    throw new BadRequestException("Unexpected limit type: " + limitType);
            }
        }
    }
}
