package com.example.InConnect.dto;

import com.example.InConnect.enums.StoreType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MerchantUpdateDTO {
    private String password;
    private String email;
    private String region;
    private StoreType storeType;
    private String storeName;
    private String storeAddress;

    @Builder
    public MerchantUpdateDTO(String password, String email, String region, StoreType storeType,
                             String storeName, String storeAddress) {
        this.password = password;
        this.email = email;
        this.region = region;
        this.storeType = storeType;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }
}
