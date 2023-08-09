package com.service.creditoffer.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "account_limit", nullable = false)
    private Float accountLimit;

    @Column(name = "per_transaction_limit", nullable = false)
    private Float perTransactionLimit;

    @Column(name = "last_account_limit")
    private Float lastAccountLimit;

    @Column(name = "last_per_transaction_limit")
    private Float lastPerTransactionLimit;

    @Column(name = "account_limit_update_time")
    private Date accountLimitUpdateTime;

    @Column(name = "per_transaction_limit_update_time")
    private Date perTransactionLimitUpdateTime;

}
