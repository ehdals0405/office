package com.nhnacademy.service;

import com.nhnacademy.entity.FamilyRelationship;
import com.nhnacademy.dto.RelationShipDto;
import com.nhnacademy.repository.FamilyRelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.nhnacademy.entity.FamilyRelationship.*;

@Service
@RequiredArgsConstructor
@Transactional
public class FamilyRelationshipService {

    private final FamilyRelationshipRepository familyRelationshipRepository;

    public RelationShipDto saveRelationShip(FamilyRelationship familyRelationship) {

        FamilyRelationship fr = familyRelationshipRepository.save(familyRelationship);

        return RelationShipDto.builder()
                .familySerialNumber(fr.getPk().getFamilyResidentSerialNumber())
                .serialNumber(fr.getPk().getBaseResidentSerialNumber())
                .relationShip(RelationshipCode.getByRelation(fr.getFamilyRelationshipCode()))
                .build();
    }

    public RelationShipDto updateRelationShip(Pk pk, RelationshipCode relationship) {

        FamilyRelationship fr = familyRelationshipRepository.findById(pk).orElse(null);
        fr.setFamilyRelationshipCode(relationship.getRelation());

        familyRelationshipRepository.save(fr);

        return RelationShipDto.builder()
                .serialNumber(pk.getBaseResidentSerialNumber())
                .familySerialNumber(pk.getFamilyResidentSerialNumber())
                .relationShip(RelationshipCode.getByRelation(fr.getFamilyRelationshipCode()))
                .build();

    }

    public void deleteRelationShip(Pk pk) {

        familyRelationshipRepository.deleteById(pk);
    }

}
