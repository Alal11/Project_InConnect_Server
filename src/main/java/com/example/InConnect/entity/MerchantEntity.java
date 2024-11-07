package com.example.InConnect.entity;

import com.example.InConnect.enums.GenderType;
import com.example.InConnect.enums.StoreCategoryType;
import com.example.InConnect.enums.UserType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 소상공인 회원 정보를 담는 자식 엔티티
 * user 테이블과 조인되어 소상공인 추가 정보 관리
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("MARCHANT")  // 구분 컬럼 지정
@Table(name = "merchant")
public class MerchantEntity extends UserEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StoreCategoryType storeCategoryType;  // 가게 카테고리 (CAFE, RESTAURANT, BAR, BAKERY, DESSERT)

    @Column(nullable = false, length = 50)
    private String storeName;  // 가게 이름

    @Column(nullable = false, length = 100)
    private String storeAddress;  // 가게 주소

    @Builder(builderMethodName = "merchantBuilder")
    public MerchantEntity(String username, String password, String email,
                          GenderType genderType, LocalDate birthdate, String region,
                          StoreCategoryType storeCategoryType, String storeName, String storeAddress) {
        super(username, password, email, genderType, birthdate, region, UserType.MERCHANT);
        this.storeCategoryType = storeCategoryType;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }
}
