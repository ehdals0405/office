package com.nhnacademy.repository;

import com.nhnacademy.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static com.nhnacademy.entity.FamilyRelationship.*;

import java.util.Optional;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, Pk> {


}
