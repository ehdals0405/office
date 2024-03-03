package com.nhnacademy.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdMovementDto {

    private LocalDate houseMovementReportDate;
    private String houseAddressMovement;
}
