package com.nhnacademy.controller.api;

import com.nhnacademy.entity.FamilyRelationship;
import com.nhnacademy.dto.RelationShipDto;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.repository.ResidentRepository;
import com.nhnacademy.service.FamilyRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.nhnacademy.entity.FamilyRelationship.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents")
public class RelationshipRestController {

    private final FamilyRelationshipService familyRelationshipService;
    private final ResidentRepository residentRepository;

    @PostMapping("/{serialNumber}/relationship")
    public ResponseEntity<RelationShipDto> registerResident(@PathVariable("serialNumber") Long residentId,
                                                            @RequestBody RelationShipDto request) {

        Resident resident = residentRepository.findById(residentId).orElse(null);


        FamilyRelationship familyRelationship = FamilyRelationship.builder()
                .pk(new FamilyRelationship.Pk(residentId, request.getFamilySerialNumber()))
                .resident(resident)
                .familyRelationshipCode(request.getRelationShip().getRelation())
                .build();

        RelationShipDto response = familyRelationshipService.saveRelationShip(familyRelationship);
        response.setSerialNumber(resident.getResidentId());

        return ResponseEntity.ok().body(response);

    }

    @PutMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<RelationShipDto> updateRelationship(@PathVariable("serialNumber") Long serialNumber,
                                                              @PathVariable("familySerialNumber") Long familySerialNumber,
                                                              @RequestBody RelationShipDto request) {

        Pk pk = new FamilyRelationship.Pk(serialNumber, familySerialNumber);
        RelationshipCode relationship = request.getRelationShip();

        RelationShipDto response = familyRelationshipService.updateRelationShip(pk, relationship);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity deleteRelationship(@PathVariable("serialNumber") Long serialNumber,
                                             @PathVariable("familySerialNumber") Long familySerialNumber) {

        Pk pk = new Pk(serialNumber, familySerialNumber);
        familyRelationshipService.deleteRelationShip(pk);

        return ResponseEntity.noContent().build();
    }
}
