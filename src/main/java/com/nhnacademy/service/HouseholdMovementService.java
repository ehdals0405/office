package com.nhnacademy.service;

import com.nhnacademy.dto.HouseholdMovementDto;
import com.nhnacademy.entity.BirthDeathReportResident;
import com.nhnacademy.entity.Household;

import static com.nhnacademy.entity.HouseholdMovementAddress.*;

import com.nhnacademy.entity.HouseholdMovementAddress;
import com.nhnacademy.repository.HouseholdMovementRepository;
import com.nhnacademy.repository.HouseholdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class HouseholdMovementService {

    private final HouseholdMovementRepository householdMovementRepository;
    private final HouseholdRepository householdRepository;

    public HouseholdMovementDto saveHouseholdMovement(Long householdSerialNumber, HouseholdMovementAddress householdMovementAddress) {

        Household household = householdRepository.findById(householdSerialNumber).orElse(null);
        householdMovementAddress.setHousehold(household);

        householdMovementRepository.setOldAddress(householdSerialNumber);
        HouseholdMovementAddress saveMovementAddress = householdMovementRepository.save(householdMovementAddress);

        return HouseholdMovementDto.builder()
                .houseMovementReportDate(saveMovementAddress.getPk().getHouseMovementReportDate())
                .houseAddressMovement(saveMovementAddress.getHouseMovementAddress())
                .build();
    }

    public HouseholdMovementDto updateHouseholdMovement(Pk pk, HouseholdMovementAddress householdMovementAddress) {

        Optional<HouseholdMovementAddress> optionalMovementAddress = householdMovementRepository.findById(pk);

        if (optionalMovementAddress.isPresent()) {

            HouseholdMovementAddress findMovementAddress = optionalMovementAddress.get();

            Optional.ofNullable(householdMovementAddress.getHouseMovementAddress()).ifPresent(findMovementAddress::setHouseMovementAddress);

            householdMovementRepository.updateMovementAddress(pk, findMovementAddress.getPk().getHouseMovementReportDate());
            householdMovementRepository.save(findMovementAddress);

            return HouseholdMovementDto.builder()
                    .houseMovementReportDate(findMovementAddress.getPk().getHouseMovementReportDate())
                    .houseAddressMovement(findMovementAddress.getHouseMovementAddress())
                    .build();
        }
        return null;
    }

    public void deleteHouseholdMovement(Pk pk) {

        householdMovementRepository.deleteById(pk);
    }
}
