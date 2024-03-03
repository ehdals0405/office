package com.nhnacademy.controller.api;


import com.nhnacademy.dto.HouseholdDto;
import com.nhnacademy.entity.Household;
import com.nhnacademy.service.HouseholdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/household")
public class HouseholdRestController {

    private final HouseholdService householdService;
    @PostMapping
    public ResponseEntity registerHousehold(@RequestBody HouseholdDto request){

        Household household = Household.builder()
                .householdCompositionDate(request.getHouseholdCompositionDate())
                .householdCompositionReasonCode(request.getHouseholdCompositionReasonCode())
                .currentHouseMovementAddress(request.getCurrentHouseMovementAddress())
                .build();

        HouseholdDto response = householdService.saveHousehold(request.getHouseholdResidentSerialNumber(),household);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{householdSerialNumber}")
    public ResponseEntity deleteHousehold(@PathVariable("householdSerialNumber") Long householdSerialNumber){

        householdService.deleteHousehold(householdSerialNumber);

        return ResponseEntity.noContent().build();
    }
}
