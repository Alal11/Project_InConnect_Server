package com.example.InConnect.dto;

import com.example.InConnect.entity.MerchantEntity;
import com.example.InConnect.enums.GenderType;
import com.example.InConnect.enums.StoreType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 소상공인 회원가입 요청 DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MerchantSignUpDTO extends SignUpDTO {

    // 소상공인 정보
    private StoreType storeType;  // CAFE, RESTAURANT, BAR, BAKERY, DESSERT
    private String storeName;
    private String storeAddress;

    @Builder
    public MerchantSignUpDTO(String username, String password, String email,
                             GenderType genderType, LocalDate birthdate, String region,
                             StoreType storeType, String storeName, String storeAddress) {
        super(username, password, email, genderType, birthdate, region);
        this.storeType = storeType;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }

    public MerchantEntity toEntity() {
        return MerchantEntity.builder()
                .username(getUsername())
                .password(getPassword())
                .email(getEmail())
                .genderType(getGenderType())
                .birthdate(getBirthDate())
                .region(getRegion())
                .storeType(storeType)
                .storeName(storeName)
                .storeAddress(storeAddress)
                .build();
    }
}
