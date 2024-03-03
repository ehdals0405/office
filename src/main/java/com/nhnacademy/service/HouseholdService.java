package com.nhnacademy.service;

import com.nhnacademy.dto.HouseholdDto;
import com.nhnacademy.entity.Household;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.repository.HouseholdRepository;
import com.nhnacademy.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class HouseholdService {

    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    public HouseholdDto saveHousehold(Long householdResidentSerialNumber, Household household){

        Resident resident = residentRepository.findById(householdResidentSerialNumber).orElse(null);
        household.setHouseholdResidentSerialNumber(resident);
        Household saveHousehold = householdRepository.save(household);

        return HouseholdDto.builder()
                .householdResidentSerialNumber(resident.getResidentId())
                .householdCompositionDate(saveHousehold.getHouseholdCompositionDate())
                .householdCompositionReasonCode(saveHousehold.getHouseholdCompositionReasonCode())
                .currentHouseMovementAddress(saveHousehold.getCurrentHouseMovementAddress())
                .build();
    }

    public void deleteHousehold(Long householdSerialNumber){

        householdRepository.deleteById(householdSerialNumber);
    }
}
