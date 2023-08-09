package com.service.creditoffer.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "limit_offer")
@Getter
@Setter
@NoArgsConstructor
public class LimitOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "limit_type")
    private String limitType;

    @Column(name = "new_limit", nullable = false)
    private Float newLimit;

    @Column(name = "status")
    private String status;

    @Column(name = "offer_activation_time")
    private Date offerActivationTime;

    @Column(name = "offer_expiry_time")
    private Date offerExpiryTime;

    public LimitOffer (Long accountId, String limitType, Float newLimit, String status, Date offerActivationTime, Date offerExpiryTime){
        this.accountId = accountId;
        this.limitType = limitType;
        this.newLimit = newLimit;
        this.status = status;
        this.offerActivationTime = offerActivationTime;
        this.offerExpiryTime = offerExpiryTime;
    }

}
