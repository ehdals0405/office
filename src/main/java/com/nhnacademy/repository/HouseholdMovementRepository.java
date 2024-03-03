package com.nhnacademy.repository;

import static com.nhnacademy.entity.HouseholdMovementAddress.*;

import com.nhnacademy.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface HouseholdMovementRepository extends JpaRepository<HouseholdMovementAddress, Pk> {

    @Transactional
    @Modifying
    @Query("UPDATE FROM HouseholdMovementAddress hma SET hma.isLastAddress = false WHERE hma.household.householdSerialNumber = :householdSerialNumber")
    void setOldAddress(@Param("householdSerialNumber") Long householdSerialNumber);

    @Transactional
    @Modifying
    @Query("UPDATE FROM HouseholdMovementAddress hma SET hma.pk.houseMovementReportDate =:reportDate " +
            "WHERE hma.pk =:pk")
    void updateMovementAddress(@Param("pk") Pk pk, @Param("reportDate") LocalDate reportDate);
}
