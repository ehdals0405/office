package com.nhnacademy.repository;

import com.nhnacademy.domain.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {

    Optional<FamilyRelationship> findByPk(FamilyRelationship.Pk pk);

    void deleteByPk(FamilyRelationship.Pk pk);
}
