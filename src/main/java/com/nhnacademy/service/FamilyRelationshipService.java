package com.nhnacademy.service;

import com.nhnacademy.domain.FamilyRelationship;
import com.nhnacademy.repository.FamilyRelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FamilyRelationshipService {

    private final FamilyRelationshipRepository familyRelationshipRepository;

    public void saveRelationShip(FamilyRelationship familyRelationship) {

        familyRelationshipRepository.save(familyRelationship);
    }

    public void updateRelationShip(FamilyRelationship.Pk pk, String relationship){

        FamilyRelationship familyRelationship = familyRelationshipRepository.findByPk(pk).orElse(null);
        familyRelationship.setFamilyRelationshipCode(relationship);
    }

    public void deleteRelationShip(FamilyRelationship.Pk pk){

        familyRelationshipRepository.deleteByPk(pk);

    }

}
