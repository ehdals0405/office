package com.nhnacademy.controller.api;

import com.nhnacademy.dto.ResidentDto;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.repository.ResidentRepository;
import com.nhnacademy.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents")
public class ResidentRestController {

    private final ResidentService residentService;

    @PostMapping
    public ResponseEntity registerResident(@RequestBody ResidentDto request) {

        Resident resident = Resident.builder()
                .name(request.getName())
                .residentRegistrationNumber(request.getResidentRegistrationNumber())
                .genderCode(request.getGenderCode())
                .build();

        ResidentDto response = residentService.saveResident(resident);

        return new ResponseEntity(response, HttpStatus.CREATED);

    }
    @PutMapping("/{serialNumber}")
    public ResponseEntity updateResident(@PathVariable("serialNumber") Long serialNumber,
                                         @RequestBody ResidentDto residentDto){

        ResidentDto response = residentService.updateResident(serialNumber,residentDto);

        return ResponseEntity.ok(response);

    }
}
