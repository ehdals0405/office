package com.nhnacademy.dto;


import static com.nhnacademy.entity.FamilyRelationship.*;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RelationShipDto {

    private Long serialNumber;
    private Long familySerialNumber;
    private RelationshipCode relationShip;
}
