package com.nhnacademy.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "household")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "household_serial_number")
    private Long householdSerialNumber;

    @ManyToOne
    @JoinColumn(name = "household_resident_serial_number")
    private Resident householdResidentSerialNumber;

    @Column(name = "household_composition_date")
    private LocalDate householdCompositionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "household_composition_reason_code")
    private HouseholdCompositionReasonCode householdCompositionReasonCode;

    @Column(name = "current_house_movement_address")
    private String currentHouseMovementAddress;

    public enum HouseholdCompositionReasonCode{

        세대분리, 전입, 출생등록
    }

}
