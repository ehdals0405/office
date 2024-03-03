package com.nhnacademy.dto;

import static com.nhnacademy.entity.BirthDeathReportResident.*;

import com.nhnacademy.entity.Resident;
import lombok.*;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BirthReportDto {

    private Long residentSerialNumber;
    private TypeCode typeCode;
    private LocalDateTime birthReportDate;
    private LocalDate birthDate;
    private Resident.BirthPlace birthPlace;
    private String address;
    private QualificationsCode birthReportQualificationsCode;
    @Email
    private String emailAddress;
    private String phoneNumber;
}
