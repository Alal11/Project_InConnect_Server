package com.example.InConnect.dto;

import com.example.InConnect.enums.SnsType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InfluencerUpdateDTO {
    private String password;
    private String email;
    private String region;
    private SnsType snsType;
    private String snsUrl;
    private Integer followerCount;

    @Builder
    public InfluencerUpdateDTO(String password, String email, String region,
                               SnsType snsType, String snsUrl, Integer followerCount) {
        this.password = password;
        this.email = email;
        this.region = region;
        this.snsType = snsType;
        this.snsUrl = snsUrl;
        this.followerCount = followerCount;
    }
}
