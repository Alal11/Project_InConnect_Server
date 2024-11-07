package com.example.InConnect.dto;

import com.example.InConnect.entity.InfluencerEntity;
import com.example.InConnect.enums.SnsType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 인플루언서 회원가입 요청 DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InfluencerSignUpDTO extends SignUpDTO {

    private SnsType snsType;
    private String snsUrl;
    private Integer followerCount;

    public InfluencerEntity toEntity() {
        return InfluencerEntity.influencerBuilder()
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
