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
public class DeathReportDto {

    private Long residentSerialNumber;
    private TypeCode typeCode;
    private LocalDateTime deathReportDate;
    private LocalDate deathDate;
    private Resident.DeathPlace deathPlace;
    private String address;
    private QualificationsCode deathReportQualificationsCode;
    @Email
    private String emailAddress;
    private String phoneNumber;
}
