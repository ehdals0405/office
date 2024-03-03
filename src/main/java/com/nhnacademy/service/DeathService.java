package com.nhnacademy.service;

import com.nhnacademy.dto.BirthReportDto;
import com.nhnacademy.dto.DeathReportDto;
import com.nhnacademy.entity.BirthDeathReportResident;

import static com.nhnacademy.entity.BirthDeathReportResident.*;

import com.nhnacademy.entity.Resident;
import com.nhnacademy.repository.BirthDeathRepository;
import com.nhnacademy.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class DeathService {

    private final BirthDeathRepository birthDeathRepository;

    public DeathReportDto registerReport(BirthDeathReportResident birthDeathReportResident) {

        BirthDeathReportResident saveReport = birthDeathRepository.save(birthDeathReportResident);

        return DeathReportDto.builder()
                .residentSerialNumber(saveReport.getPk().getResidentSerialNumber())
                .deathDate(saveReport.getBirthDeathReportDate())
                .deathReportQualificationsCode(saveReport.getDeathReportQualificationsCode())
                .emailAddress(saveReport.getEmailAddress())
                .phoneNumber(saveReport.getPhoneNumber())
                .build();
    }

    public DeathReportDto updateReport(Pk pk, BirthDeathReportResident birthDeathReportResident) {

        Optional<BirthDeathReportResident> optionalReport = birthDeathRepository.findById(pk);

        if (optionalReport.isPresent()) {

            BirthDeathReportResident findReport = optionalReport.get();

            Optional.ofNullable(birthDeathReportResident.getBirthDeathReportDate()).ifPresent(findReport::setBirthDeathReportDate);
            Optional.ofNullable(birthDeathReportResident.getEmailAddress()).ifPresent(findReport::setEmailAddress);
            Optional.ofNullable(birthDeathReportResident.getPhoneNumber()).ifPresent(findReport::setPhoneNumber);
            Optional.ofNullable(birthDeathReportResident.getDeathReportQualificationsCode()).ifPresent(findReport::setDeathReportQualificationsCode);

            birthDeathRepository.save(findReport);

            return DeathReportDto.builder()
                    .residentSerialNumber(findReport.getPk().getResidentSerialNumber())
                    .deathDate(findReport.getBirthDeathReportDate())
                    .deathReportQualificationsCode(findReport.getDeathReportQualificationsCode())
                    .emailAddress(findReport.getEmailAddress())
                    .phoneNumber(findReport.getPhoneNumber())
                    .build();
        }
        return null;
    }

    public void deleteReport(Pk pk) {

        birthDeathRepository.deleteById(pk);
    }
}
