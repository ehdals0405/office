package com.nhnacademy.entity;

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
    @ManyToOne
    @JoinColumn(name = "base_resident_serial_number")
    private Resident resident;

    @Column(name = "family_relationship_code")
    private String familyRelationshipCode;

    @Embeddable
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {

       @Column(name = "base_resident_serial_number")
        private Long baseResidentSerialNumber;
       @Column(name = "family_resident_serial_number")
        private Long familyResidentSerialNumber;
    }


    @Getter
    public enum RelationshipCode {

        father("부"), mother("모"), spouse("배우자"), child("자녀");

        private String relation;

        RelationshipCode(String relation) {
            this.relation = relation;
        }

        public static RelationshipCode getByRelation(String relation) {
            for (RelationshipCode code : RelationshipCode.values()) {
                if (code.relation.equals(relation)) {
                    return code;
                }
            }
            return null;
        }
    }
}
