package com.nhnacademy.controller.api;

import com.nhnacademy.dto.BirthReportDto;
import com.nhnacademy.dto.DeathReportDto;
import com.nhnacademy.entity.BirthDeathReportResident;

import static com.nhnacademy.entity.BirthDeathReportResident.*;

import com.nhnacademy.entity.Resident;
import com.nhnacademy.repository.ResidentRepository;
import com.nhnacademy.service.BirthService;
import com.nhnacademy.service.DeathService;
import com.nhnacademy.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/residents")
public class DeathRestController {

    private final DeathService deathService;
    private final ResidentService residentService;

    @PostMapping("/{serialNumber}/death")
    private ResponseEntity postDeathReport(@PathVariable("serialNumber") Long serialNumber,
                                           @RequestBody @Valid DeathReportDto request) {

        Resident reportResident = residentService.findResidentById(serialNumber);
        Resident targetResident = residentService.updateDeathResident(request);


        BirthDeathReportResident birthDeathReportResident = BirthDeathReportResident.builder()
                .pk(new Pk(request.getResidentSerialNumber(),serialNumber, request.getTypeCode()))
                .reportResident(reportResident)
                .resident(targetResident)
                .birthDeathReportDate(LocalDate.from(request.getDeathReportDate()))
                .deathReportQualificationsCode(request.getDeathReportQualificationsCode())
                .emailAddress(request.getEmailAddress())
                .phoneNumber(request.getPhoneNumber())
                .build();

        DeathReportDto response = deathService.registerReport(birthDeathReportResident);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{serialNumber}/death/{targetSerialNumber}")
    private ResponseEntity updateDeathReport(@PathVariable("serialNumber") Long serialNumber,
                                             @PathVariable("targetSerialNumber") Long targetSerialNumber,
                                             @RequestBody @Valid DeathReportDto request) {

        Pk pk = new Pk(targetSerialNumber,serialNumber, TypeCode.사망);
        BirthDeathReportResident birthDeathReportResident = BirthDeathReportResident.builder()
                .birthDeathReportDate(LocalDate.from(request.getDeathReportDate()))
                .deathReportQualificationsCode(request.getDeathReportQualificationsCode())
                .emailAddress(request.getEmailAddress())
                .phoneNumber(request.getPhoneNumber())
                .build();

        request.setResidentSerialNumber(targetSerialNumber);
        residentService.updateDeathResident(request);
        DeathReportDto response = deathService.updateReport(pk, birthDeathReportResident);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{serialNumber}/death/{targetSerialNumber}")
    private ResponseEntity deleteDeathReport(@PathVariable("serialNumber") Long serialNumber,
                                             @PathVariable("targetSerialNumber") Long targetSerialNumber) {

        Pk pk = new Pk(targetSerialNumber,serialNumber, TypeCode.사망);
        deathService.deleteReport(pk);

        return ResponseEntity.noContent().build();

    }
}