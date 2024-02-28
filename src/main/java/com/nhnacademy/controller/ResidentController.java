package com.nhnacademy.controller;

import com.nhnacademy.domain.FamilyRelationship;
import com.nhnacademy.dto.RelationShipDto;
import com.nhnacademy.service.FamilyRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/residents")
public class ResidentController {

    private FamilyRelationshipService familyRelationshipService;

    @PostMapping("/{serialNumber}/relationship")
    public String registerResident(@PathVariable("serialNumber") Long serialNumber,
                                   @ModelAttribute RelationShipDto request) {

        FamilyRelationship familyRelationship = FamilyRelationship.builder()
                .pk(new FamilyRelationship.Pk(serialNumber, request.getFamilySerialNumber()))
                .familyResidentSerialNumber(serialNumber)
                .baseResidentSerialNumber(request.getFamilySerialNumber())
                .familyRelationshipCode(request.getRelationShip())
                .build();

        familyRelationshipService.saveRelationShip(familyRelationship);

        return null;

    }

    @PutMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public String updateRelationship(@PathVariable("serialNumber") Long serialNumber,
                                     @PathVariable("familySerialNumber") Long familySerialNumber,
                                     @ModelAttribute RelationShipDto request) {

        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(serialNumber, familySerialNumber);
        String relationship = request.getRelationShip();
        familyRelationshipService.updateRelationShip(pk, relationship);

        return null;
    }

    @DeleteMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public String deleteRelationship(@PathVariable("serialNumber") Long serialNumber,
                                     @PathVariable("familySerialNumber") Long familySerialNumber) {

        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(serialNumber, familySerialNumber);
        return null;
    }
}
