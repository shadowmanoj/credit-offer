package com.service.creditoffer.services.impl;

import com.service.creditoffer.constants.LimitOfferType;
import com.service.creditoffer.entities.Account;
import com.service.creditoffer.entities.LimitOffer;
import com.service.creditoffer.exceptions.BadRequestException;
import com.service.creditoffer.exceptions.NotFoundException;
import com.service.creditoffer.models.requests.CreateLimitOfferRequest;
import com.service.creditoffer.models.requests.UpdateLimitOfferRequest;
import com.service.creditoffer.repositories.LimitOfferRepository;
import com.service.creditoffer.services.AccountService;
import com.service.creditoffer.services.LimitOfferService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.service.creditoffer.constants.LimitOfferStatus.ACCEPTED;
import static com.service.creditoffer.constants.LimitOfferStatus.PENDING;

/**
 * @author manoj.kumar
 */

@Service
public class LimitOfferServiceImpl implements LimitOfferService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private LimitOfferRepository limitOfferRepository;

    @SneakyThrows
    public String createLimitOffer(CreateLimitOfferRequest createLimitOfferRequest){
        Account account = accountService.getAccountById(createLimitOfferRequest.getAccountId());

        if(account==null || ObjectUtils.isEmpty(account))
            throw new BadRequestException("Account not found for accountId"+createLimitOfferRequest.getAccountId());

        if(createLimitOfferRequest.getLimitType() == LimitOfferType.ACCOUNT_LIMIT &&
                account.getAccountLimit() >= createLimitOfferRequest.getNewLimit())
            throw new BadRequestException("New Limit should be greater than current limit. Current Limit : "+account.getAccountLimit());

        if(createLimitOfferRequest.getLimitType() == LimitOfferType.PER_TRANSACTION_LIMIT &&
                account.getPerTransactionLimit() >= createLimitOfferRequest.getNewLimit())
            throw new BadRequestException("New Limit should be greater than current limit. Current Limit : "+account.getPerTransactionLimit());

        LimitOffer limitOffer = this.limitOfferMapper(createLimitOfferRequest);

        try{
            limitOfferRepository.save(limitOffer);
            return "Offer created successfully";
        } catch (Exception e){
            throw new BadRequestException("Error in creating offer : {}"+e.getMessage());
        }
    }

    public List<LimitOffer> getActiveLimitOffers(Long accountId, Date activeDate){
        List<LimitOffer> limitOffers;
        if(ObjectUtils.isEmpty(activeDate))
             limitOffers = limitOfferRepository.findAllByAccountIdAndStatus(accountId, PENDING.name());
        else
            limitOffers = limitOfferRepository.findByAccountIdAndStatusAndActivationDateLessThanActiveDate(accountId, PENDING.name(), activeDate);
        if(!ObjectUtils.isEmpty(limitOffers) && limitOffers.size()>0)
            return limitOffers;
        throw new NotFoundException("No active offer found for this accountId and active date");
    }

    public String updateLimitOrderStatus(UpdateLimitOfferRequest updateLimitOfferRequest){
        Optional<LimitOffer> limitOffer = limitOfferRepository.findById(updateLimitOfferRequest.getLimitOfferId());
        if(limitOffer.isPresent() && this.checkIfOfferIsActiveOrPending(limitOffer.get())){
            if(Objects.equals(limitOffer.get().getStatus(), updateLimitOfferRequest.getStatus().name()))
                return "Offer already in updated state";
            limitOffer.get().setStatus(updateLimitOfferRequest.getStatus().name());
            try {
                limitOfferRepository.saveAndFlush(limitOffer.get());
                if(updateLimitOfferRequest.getStatus() == ACCEPTED)
                    accountService.updateAccountLimitOffer(limitOffer.get().getAccountId(),
                                                        limitOffer.get().getLimitType(),
                                                        limitOffer.get().getNewLimit());
                return "Limit offer updated successfully";
            } catch (Exception e){
                throw new BadRequestException("Error in updating limit offer : "+e.getMessage());
            }
        }
        else throw new NotFoundException("No Active/Pending offer found with this id : "+updateLimitOfferRequest.getLimitOfferId());
    }

    private LimitOffer limitOfferMapper(CreateLimitOfferRequest createLimitOfferRequest){
        return new LimitOffer(createLimitOfferRequest.getAccountId(),
                            createLimitOfferRequest.getLimitType().name(),
                            createLimitOfferRequest.getNewLimit(),
                            PENDING.name(),
                            createLimitOfferRequest.getOfferActivationTime(),
                            createLimitOfferRequest.getOfferExpiryTime());
    }

    private Boolean checkIfOfferIsActiveOrPending(LimitOffer limitOffer){
        return limitOffer.getStatus().equalsIgnoreCase(PENDING.name()) ||
                limitOffer.getStatus().equalsIgnoreCase(ACCEPTED.name());
    }
}
