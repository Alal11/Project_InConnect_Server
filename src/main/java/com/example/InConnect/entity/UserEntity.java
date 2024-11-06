package com.example.InConnect.entity;

import com.example.InConnect.enums.GenderType;
import com.example.InConnect.enums.SnsType;
import com.example.InConnect.enums.StoreCategoryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderType genderType;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String region;  // 거주 지역

    // 소상공인 관련 정보
    @Enumerated(EnumType.STRING)
    private StoreCategoryType storeCategoryType;

    private String storeName;
    private String storeAddress;

    // 인플루언서 관련 정보
    @Enumerated(EnumType.STRING)
    private SnsType snsType;

    private String snsUrl;
    private Integer followerCount;

}
