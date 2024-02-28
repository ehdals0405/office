package com.nhnacademy.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "birth_death_report_resident")
@Getter
@Setter
public class BirthDeathReportResident {

    @EmbeddedId
    private Pk pk;

    @MapsId("residentSerialNumber")
    private Long residentSerialNumber;
    @MapsId("birthDeathTypeCode")
    private Long birthDeathTypeCode;

    @Column(name = "report_resident_serial_number")
    private Long reportResidentSerialNumber;
    @Column(name = "birth_death_report_date")
    private LocalDateTime birthDeathReportDate;
    @Column(name = "death_report_qualifications_code")
    private String deathReportQualificationsCode;
    @Column(name = "birth_report_qualifications_code")
    private String brithReportQualificationsCode;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Embeddable
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pk implements Serializable {

        @Column(name = "resident_serial_number")
        private Long residentSerialNumber;

        @Column(name = "birth_death_type_code")
        private Long birthDeathTypeCode;

    }
}
