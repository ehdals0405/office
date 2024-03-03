package com.nhnacademy.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "household_composition_resident")
@Getter
@Setter
public class HouseholdCompositionResident {

    @EmbeddedId
    private Pk pk;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number")
    private Household household;
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    @MapsId("residentSerialNumber")
    private Resident resident;

    @Column(name = "report_date")
    private LocalDateTime reportDate;
    @Column(name = "household_relationship_code")
    private String householdRelationshipCode;
    @Column(name = "household_composition_change_reason_code")
    private String householdCompositionChangeReasonCode;


    @Embeddable
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {

        @Column(name = "household_serial_number")
        private Long householdSerialNumber;

        @Column(name = "resident_serial_number")
        private Long residentSerialNumber;
    }
}
