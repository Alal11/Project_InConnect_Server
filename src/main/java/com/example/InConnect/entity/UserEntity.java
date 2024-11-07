package com.example.InConnect.entity;

import com.example.InConnect.enums.GenderType;
import com.example.InConnect.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * 회원 기본 정보를 담는 부모 엔티티
 * JOINED 전략으로 자식 테이블과 조인하여 사용
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)  // 상속 매핑은 조인 전략 사용
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)  // 구분 컬럼 지정
@Table(name = "users")
public abstract class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;  // 회원 고유 번호

    @Column(name = "username", nullable = false, unique = true, length = 30)
    private String username;  // 회원 아이디

    @Column(name = "password", nullable = false, length = 100)
    private String password;  // 비밀번호

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;  // 이메일

    @Enumerated(EnumType.STRING)
    @Column(name = "gender_type", nullable = false)
    private GenderType genderType;  // 성별 (MALE, FEMALE)

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthDate;  // 생년월일

    @Column(name = "region", nullable = false, length = 50)
    private String region;  // 거주 지역

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false, length = 20)
    private UserType userType;  // 회원 유형 (MERCHANT, INFLUENCER)

    protected UserEntity(String username, String password, String email, GenderType genderType,
                         LocalDate birthDate, String region, UserType userType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.genderType = genderType;
        this.birthDate = birthDate;
        this.region = region;
        this.userType = userType;
    }
}
