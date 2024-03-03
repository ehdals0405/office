package com.nhnacademy.service;

import com.nhnacademy.dto.BirthReportDto;
import com.nhnacademy.dto.ResidentDto;
import com.nhnacademy.entity.BirthDeathReportResident;

import static com.nhnacademy.entity.BirthDeathReportResident.*;

import com.nhnacademy.entity.Resident;
import com.nhnacademy.repository.BirthDeathRepository;
import com.nhnacademy.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BirthService {

    private final BirthDeathRepository birthDeathRepository;
    public BirthReportDto registerReport(BirthDeathReportResident birthDeathReportResident) {

        BirthDeathReportResident saveReport = birthDeathRepository.save(birthDeathReportResident);

        return BirthReportDto.builder()
                .residentSerialNumber(saveReport.getPk().getResidentSerialNumber())
                .birthDate(saveReport.getBirthDeathReportDate())
                .birthReportQualificationsCode(saveReport.getBrithReportQualificationsCode())
                .emailAddress(saveReport.getEmailAddress())
                .phoneNumber(saveReport.getPhoneNumber())
                .build();
    }

    public BirthReportDto updateReport(Pk pk, BirthDeathReportResident birthDeathReportResident) {

        Optional<BirthDeathReportResident> optionalReport = birthDeathRepository.findById(pk);

        if (optionalReport.isPresent()) {

            BirthDeathReportResident findReport = optionalReport.get();

            Optional.ofNullable(birthDeathReportResident.getBirthDeathReportDate()).ifPresent(findReport::setBirthDeathReportDate);
            Optional.ofNullable(birthDeathReportResident.getEmailAddress()).ifPresent(findReport::setEmailAddress);
            Optional.ofNullable(birthDeathReportResident.getPhoneNumber()).ifPresent(findReport::setPhoneNumber);
            Optional.ofNullable(birthDeathReportResident.getBrithReportQualificationsCode()).ifPresent(findReport::setBrithReportQualificationsCode);

            birthDeathRepository.save(findReport);

            return BirthReportDto.builder()
                    .residentSerialNumber(findReport.getPk().getResidentSerialNumber())
                    .birthDate(findReport.getBirthDeathReportDate())
                    .birthReportQualificationsCode(findReport.getBrithReportQualificationsCode())
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
