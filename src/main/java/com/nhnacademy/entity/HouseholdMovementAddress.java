package com.nhnacademy.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "household_movement_address")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdMovementAddress {

    @EmbeddedId
    private Pk pk;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number")
    private Household household;

    @Column(name = "house_movement_address")
    private String houseMovementAddress;

    @Column(name = "last_address_yn")
    private boolean isLastAddress;


    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {

        @Column(name = "household_serial_number")
        private Long householdSerialNumber;

        @Column(name = "house_movement_report_date")
        private LocalDate houseMovementReportDate;
    }
}
