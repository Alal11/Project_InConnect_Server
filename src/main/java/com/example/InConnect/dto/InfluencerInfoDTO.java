package com.example.InConnect.dto;

import com.example.InConnect.enums.SnsType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InfluencerInfoDTO implements UserInfoDTO {

    private final String email;
    private final String region;
    private final SnsType snsType;
    private final String snsUrl;
    private final Integer followerCount;
}
