package com.nhnacademy.controller.api;

import com.nhnacademy.dto.HouseholdMovementDto;

import static com.nhnacademy.entity.HouseholdMovementAddress.*;

import com.nhnacademy.entity.HouseholdMovementAddress;
import com.nhnacademy.service.HouseholdMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/household")
public class HouseholdMovementRestController {

    private final HouseholdMovementService householdMovementService;

    @PostMapping("/{householdSerialNumber}/movement")
    public ResponseEntity registerHouseholdMovement(@PathVariable("householdSerialNumber") Long householdSerialNumber,
                                                    @RequestBody HouseholdMovementDto request) {

        HouseholdMovementAddress householdMovementAddress = HouseholdMovementAddress.builder()
                .pk(new Pk(householdSerialNumber,request.getHouseMovementReportDate()))
                .houseMovementAddress(request.getHouseAddressMovement())
                .isLastAddress(true)
                .build();

        HouseholdMovementDto response = householdMovementService.saveHouseholdMovement(householdSerialNumber,householdMovementAddress);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping("/{householdSerialNumber}/movement/{reportDate}")
    private ResponseEntity updateHouseholdMovement(@PathVariable("householdSerialNumber") Long householdSerialNumber,
                                                   @PathVariable("reportDate")String reportDate,
                                                   @RequestBody HouseholdMovementDto request){

        Pk pk = new Pk(householdSerialNumber,LocalDate.parse(reportDate));
        HouseholdMovementAddress householdMovementAddress = builder()
                .pk(new Pk(householdSerialNumber,request.getHouseMovementReportDate()))
                .houseMovementAddress(request.getHouseAddressMovement())
                .build();

        HouseholdMovementDto response = householdMovementService.updateHouseholdMovement(pk, householdMovementAddress);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{householdSerialNumber}/movement/{reportDate}")
    private ResponseEntity deleteHouseholdMovement(@PathVariable("householdSerialNumber") Long householdSerialNumber,
                                                   @PathVariable("reportDate") String reportDate){

        householdMovementService.deleteHouseholdMovement(new Pk(householdSerialNumber,LocalDate.parse(reportDate)));

        return ResponseEntity.noContent().build();
    }
}
