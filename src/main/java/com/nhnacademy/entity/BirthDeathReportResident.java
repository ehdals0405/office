package com.nhnacademy.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "birth_death_report_resident")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BirthDeathReportResident {

    @EmbeddedId
    private Pk pk;


    @MapsId("reportResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "report_resident_serial_number")
    private Resident reportResident;
    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;
    @Column(name = "birth_death_report_date")
    private LocalDate birthDeathReportDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "death_report_qualifications_code")
    private QualificationsCode deathReportQualificationsCode;
    @Enumerated(EnumType.STRING)
    @Column(name = "birth_report_qualifications_code")
    private QualificationsCode brithReportQualificationsCode;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {

        @Column(name = "resident_serial_number")
        private Long residentSerialNumber;

        @Column(name = "report_resident_serial_number")
        private Long reportResidentSerialNumber;

        @Enumerated(EnumType.STRING)
        @Column(name = "birth_death_type_code")
        private TypeCode typeCode;


    }

    public enum QualificationsCode {

        부, 모, 호주, 동거친족, 비동거친족, 동거자, 기타
    }

    public enum TypeCode {

        출생, 사망
    }
}
