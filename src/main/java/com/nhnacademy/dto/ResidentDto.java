package com.nhnacademy.dto;

import com.nhnacademy.entity.Resident;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResidentDto {

    private String name;
    private String residentRegistrationNumber;
    private String genderCode;
}
