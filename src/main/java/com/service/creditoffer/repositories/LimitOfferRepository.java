package com.service.creditoffer.repositories;

import com.service.creditoffer.entities.LimitOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface LimitOfferRepository extends JpaRepository<LimitOffer, Long> {

    @Query(value = "SELECT * FROM limit_offer " +
            "WHERE account_id = :accountId " +
            "AND status = :status ", nativeQuery = true)
    List<LimitOffer> findAllByAccountIdAndStatus(@Param("accountId") Long accountId, @Param("status") String status);

    @Query(value = "SELECT * FROM limit_offer " +
            "WHERE account_id = :accountId " +
            "AND status = :status " +
            "AND offer_activation_time < :activeDate " +
            "AND offer_expiry_time > :activeDate", nativeQuery = true)
    List<LimitOffer> findByAccountIdAndStatusAndActivationDateLessThanActiveDate(
            @Param("accountId") Long accountId,
            @Param("status") String status,
            @Param("activeDate") Date activeDate);


}
