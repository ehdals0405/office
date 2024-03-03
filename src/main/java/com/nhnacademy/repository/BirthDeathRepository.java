package com.nhnacademy.repository;


import static com.nhnacademy.entity.BirthDeathReportResident.*;
import com.nhnacademy.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathRepository extends JpaRepository<BirthDeathReportResident, Pk> {
}
