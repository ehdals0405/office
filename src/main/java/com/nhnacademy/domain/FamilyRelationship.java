package com.nhnacademy.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "family_relationship")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FamilyRelationship {

    @EmbeddedId
    private Pk pk;

    @MapsId("baseResidentSerialNumber")
    private Long baseResidentSerialNumber;

    @MapsId("familyResidentSerialNumber")
    private Long familyResidentSerialNumber;

    private String familyRelationshipCode;

    @Embeddable
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pk implements Serializable {

        @Column(name = "base_resident_serial_number")
        private Long baseResidentSerialNumber;
        @Column(name = "family_resident_serial_number")
        private Long familyResidentSerialNumber;
    }
}
