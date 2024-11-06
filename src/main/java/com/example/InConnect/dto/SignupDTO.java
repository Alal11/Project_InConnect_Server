package com.example.InConnect.dto;

import com.example.InConnect.enums.GenderType;
import com.example.InConnect.enums.SnsType;
import com.example.InConnect.enums.StoreCategoryType;
import com.example.InConnect.enums.UserType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignupDTO {
    private String username;
    private String password;
    private GenderType genderType;
    private LocalDate birthDate;
    private String region;
    private UserType userType;

    // 소상공인 정보
    private StoreCategoryType storeCategoryType;
    private String storeName;
    private String storeAddress;

    // 인플루언서 정보
    private SnsType snsType;
    private String snsUrl;
    private Integer followerCount;

}
