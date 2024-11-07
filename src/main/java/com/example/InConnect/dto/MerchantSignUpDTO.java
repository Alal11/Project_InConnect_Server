package com.example.InConnect.dto;

import com.example.InConnect.entity.MerchantEntity;
import com.example.InConnect.enums.StoreCategoryType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 소상공인 회원가입 요청 DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MerchantSignUpDTO extends SignUpDTO {

    // 소상공인 정보
    private StoreCategoryType storeCategoryType;  // CAFE, RESTAURANT, BAR, BAKERY, DESSERT
    private String storeName;
    private String storeAddress;

    public MerchantEntity toEntity() {
        return MerchantEntity.merchantBuilder()
                .username(getUsername())
                .password(getPassword())
                .email(getEmail())
                .genderType(getGenderType())
                .birthdate(getBirthDate())
                .region(getRegion())
                .storeCategoryType(storeCategoryType)
                .storeName(storeName)
                .storeAddress(storeAddress)
                .build();
    }
}
