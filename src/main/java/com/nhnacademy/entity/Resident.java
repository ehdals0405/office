package com.nhnacademy.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "resident")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resident_serial_number")
    private Long residentId;

    @Column(name = "name")
    private String name;
    @Column(name = "resident_registration_number")
    private String residentRegistrationNumber;
    @Column(name = "gender_code")
    private String genderCode;
    @Column(name = "birth_date")
    private LocalDateTime birthDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "birth_place_code")
    private BirthPlace birthPlaceCode;
    @Column(name = "registration_base_address")
    private String registrationBaseAddress;
    @Column(name = "death_date")
    private LocalDateTime deathDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "death_place_code")
    private DeathPlace deathPlaceCode;
    @Column(name = "death_place_address")
    private String deathPlaceAddress;

    public enum DeathPlace{
        주택,의료기관,사회복지시설,산업장,공공시설,도로,상업서비스시설,농장,병원_이송중_사망,기타
    }
    public enum BirthPlace{
        자택,병원,기타
    }

}
