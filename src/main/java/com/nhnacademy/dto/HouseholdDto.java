package com.nhnacademy.dto;

import static com.nhnacademy.entity.Household.*;
import com.nhnacademy.entity.Resident;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdDto {


    private Long householdResidentSerialNumber;

    private LocalDate householdCompositionDate;

    private HouseholdCompositionReasonCode householdCompositionReasonCode;

    private String currentHouseMovementAddress;
}
