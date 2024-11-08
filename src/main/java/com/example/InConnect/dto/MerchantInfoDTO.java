package com.example.InConnect.dto;

import com.example.InConnect.enums.StoreType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MerchantInfoDTO implements UserInfoDTO {
    private final String email;
    private final String region;
    private final StoreType storeType;
    private final String storeName;
    private final String storeAddress;
}
