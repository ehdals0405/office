package com.nhnacademy.controller.api;

import com.nhnacademy.dto.BirthReportDto;
import com.nhnacademy.dto.ResidentDto;
import com.nhnacademy.entity.BirthDeathReportResident;

import static com.nhnacademy.entity.BirthDeathReportResident.*;

import com.nhnacademy.entity.Resident;
import com.nhnacademy.repository.ResidentRepository;
import com.nhnacademy.service.BirthService;
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
public class BirthRestController {

    private final BirthService birthService;
    private final ResidentService residentService;

    @PostMapping("/{serialNumber}/birth")
    private ResponseEntity postBirthReport(@PathVariable("serialNumber") Long serialNumber,
                                           @RequestBody @Valid BirthReportDto request) {

        Resident reportResident = residentService.findResidentById(serialNumber);
        Resident targetResident = residentService.updateBirthResident(request);


        BirthDeathReportResident birthDeathReportResident = BirthDeathReportResident.builder()
                .pk(new Pk(request.getResidentSerialNumber(),serialNumber, request.getTypeCode()))
                .reportResident(reportResident)
                .resident(targetResident)
                .birthDeathReportDate(LocalDate.from(request.getBirthReportDate()))
                .brithReportQualificationsCode(request.getBirthReportQualificationsCode())
                .emailAddress(request.getEmailAddress())
                .phoneNumber(request.getPhoneNumber())
                .build();

        BirthReportDto response = birthService.registerReport(birthDeathReportResident);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{serialNumber}/birth/{targetSerialNumber}")
    private ResponseEntity updateBirthReport(@PathVariable("serialNumber") Long serialNumber,
                                             @PathVariable("targetSerialNumber") Long targetSerialNumber,
                                             @RequestBody @Valid BirthReportDto request) {

        Pk pk = new Pk(targetSerialNumber,serialNumber, TypeCode.출생);
        BirthDeathReportResident birthDeathReportResident = BirthDeathReportResident.builder()
                .birthDeathReportDate(LocalDate.from(request.getBirthReportDate()))
                .brithReportQualificationsCode(request.getBirthReportQualificationsCode())
                .emailAddress(request.getEmailAddress())
                .phoneNumber(request.getPhoneNumber())
                .build();

        request.setResidentSerialNumber(targetSerialNumber);
        residentService.updateBirthResident(request);
        BirthReportDto response = birthService.updateReport(pk, birthDeathReportResident);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{serialNumber}/birth/{targetSerialNumber}")
    private ResponseEntity deleteBirthReport(@PathVariable("serialNumber") Long serialNumber,
                                             @PathVariable("targetSerialNumber") Long targetSerialNumber) {

        Pk pk = new Pk(targetSerialNumber,serialNumber, TypeCode.출생);
        birthService.deleteReport(pk);

        return ResponseEntity.noContent().build();

    }
}
