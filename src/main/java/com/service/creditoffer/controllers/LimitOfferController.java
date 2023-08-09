package com.service.creditoffer.controllers;

import com.service.creditoffer.models.requests.CreateLimitOfferRequest;
import com.service.creditoffer.models.requests.UpdateLimitOfferRequest;
import com.service.creditoffer.services.LimitOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/v1/limit-offer")
public class LimitOfferController {

    @Autowired
    private LimitOfferService limitOfferService;

    @PostMapping("/create")
    public ResponseEntity<?> createOffer(@Valid @RequestBody CreateLimitOfferRequest createLimitOfferRequest){
        return new ResponseEntity<>(limitOfferService.createLimitOffer(createLimitOfferRequest), HttpStatus.OK);
    }

    @GetMapping("/active-offer")
    public ResponseEntity<?> getActiveOffer(@RequestParam(name = "accountId") Long accountId,
                                            @RequestParam(name = "activeDate", required = false)
                                            @DateTimeFormat(pattern = "yyyy-MM-dd") Date activeDate){
        return new ResponseEntity<>(limitOfferService.getActiveLimitOffers(accountId, activeDate), HttpStatus.OK);
    }

    @PutMapping("/update-offer")
    public ResponseEntity<?> updateOffer(@Valid @RequestBody UpdateLimitOfferRequest updateLimitOfferRequest){
        return new ResponseEntity<>(limitOfferService.updateLimitOrderStatus(updateLimitOfferRequest), HttpStatus.OK);
    }
}
