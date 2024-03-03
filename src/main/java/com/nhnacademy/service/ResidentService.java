package com.nhnacademy.service;

import com.nhnacademy.dto.BirthReportDto;
import com.nhnacademy.dto.DeathReportDto;
import com.nhnacademy.dto.ResidentDto;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ResidentService {

    private final ResidentRepository residentRepository;

    public ResidentDto saveResident(Resident resident) {

        Resident saveResident = residentRepository.save(resident);
        return ResidentDto.builder()
                .name(saveResident.getName())
                .residentRegistrationNumber(saveResident.getResidentRegistrationNumber())
                .genderCode(saveResident.getGenderCode())
                .build();

    }

    public ResidentDto updateResident(Long residentId, ResidentDto residentDto) {

        Optional<Resident> optionalResident = residentRepository.findById(residentId);

        if (optionalResident.isPresent()) {
            Resident resident = optionalResident.get();
            Optional.ofNullable(residentDto.getName())
                    .ifPresent(resident::setName);
            Optional.ofNullable(residentDto.getResidentRegistrationNumber())
                    .ifPresent(resident::setResidentRegistrationNumber);
            Optional.ofNullable(residentDto.getGenderCode())
                    .ifPresent(resident::setGenderCode);

            residentRepository.save(resident);

            return ResidentDto.builder()
                    .name(resident.getName())
                    .residentRegistrationNumber(resident.getResidentRegistrationNumber())
                    .genderCode(resident.getGenderCode())
                    .build();
        }
        return null;
    }

    public Resident findResidentById(Long serialNumber){

        return residentRepository.findById(serialNumber).orElse(null);
    }
    public Resident updateBirthResident(BirthReportDto request){

        Resident resident = residentRepository.findById(request.getResidentSerialNumber()).orElse(null);
        resident.setBirthDate(request.getBirthReportDate());
        resident.setBirthPlaceCode(request.getBirthPlace());
        resident.setRegistrationBaseAddress(request.getAddress());

        return residentRepository.save(resident);

    }

    public Resident updateDeathResident(DeathReportDto request){

        Resident resident = residentRepository.findById(request.getResidentSerialNumber()).orElse(null);
        resident.setDeathDate(request.getDeathReportDate());
        resident.setDeathPlaceCode(request.getDeathPlace());
        resident.setDeathPlaceAddress(request.getAddress());

        return residentRepository.save(resident);

    }

}
