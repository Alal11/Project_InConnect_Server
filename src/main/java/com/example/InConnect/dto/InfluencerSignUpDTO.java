package com.example.InConnect.dto;

import com.example.InConnect.entity.InfluencerEntity;
import com.example.InConnect.enums.GenderType;
import com.example.InConnect.enums.SnsType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 인플루언서 회원가입 요청 DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InfluencerSignUpDTO extends SignUpDTO {

    private SnsType snsType;
    private String snsUrl;
    private Integer followerCount;

    @Builder
    public InfluencerSignUpDTO(String username, String password, String email,
                               GenderType genderType, LocalDate birthdate, String region,
                               SnsType snsType, String snsUrl, Integer followerCount) {
        super(username, password, email, genderType, birthdate, region);
        this.snsType = snsType;
        this.snsUrl = snsUrl;
        this.followerCount = followerCount;
    }

    public InfluencerEntity toEntity() {
        return InfluencerEntity.builder()
                .username(getUsername())
                .password(getPassword())
                .email(getEmail())
                .genderType(getGenderType())
                .birthdate(getBirthDate())
                .region(getRegion())
                .snsType(snsType)
                .snsUrl(snsUrl)
                .followerCount(followerCount)
                .build();
    }
}
