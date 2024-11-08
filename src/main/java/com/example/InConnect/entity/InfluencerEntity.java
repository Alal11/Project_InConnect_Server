package com.example.InConnect.entity;

import com.example.InConnect.enums.GenderType;
import com.example.InConnect.enums.SnsType;
import com.example.InConnect.enums.UserType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 인플루언서 회원 정보를 담는 자식 엔티티
 * user 테이블과 조인되어 인플루언서 추가 정보 관리
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("INFLUENCER")  // 구분 컬럼에 입력될 값
@Table(name = "influencer")
public class InfluencerEntity extends UserEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "sns_type", nullable = false, length = 20)
    private SnsType snsType;  // SNS 유형 (YOUTUBE, INSTAGRAM, BLOG, TIKTOK)

    @Column(name = "sns_url", nullable = false, length = 200)
    private String snsUrl;  // SNS 계정 URL

    @Column(name = "follower_count", nullable = false)
    private Integer followerCount;  // 팔로워 수

    @Builder
    public InfluencerEntity(String username, String password, String email,
                            GenderType genderType, LocalDate birthdate, String region,
                            SnsType snsType, String snsUrl, Integer followerCount) {
        super(username, password, email, genderType, birthdate, region, UserType.INFLUENCER);
        this.snsType = snsType;
        this.snsUrl = snsUrl;
        this.followerCount = followerCount;
    }

    public void updateSnsInfo(SnsType snsType, String snsUrl, Integer followerCount) {
        if (snsType != null) this.snsType = snsType;
        if (snsUrl != null) this.snsUrl = snsUrl;
        if (followerCount != null) this.followerCount = followerCount;
    }
}
