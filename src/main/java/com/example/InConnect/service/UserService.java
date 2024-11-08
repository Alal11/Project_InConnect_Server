package com.example.InConnect.service;

import com.example.InConnect.dto.*;
import com.example.InConnect.entity.InfluencerEntity;
import com.example.InConnect.entity.MerchantEntity;
import com.example.InConnect.entity.UserEntity;
import com.example.InConnect.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    /**
     * 소상공인 회원가입
     */
    @Transactional
    public Long signupMerchant(MerchantSignUpDTO merchantSignUpDTO) {
        validateDuplicateMember(merchantSignUpDTO);
        MerchantEntity merchantEntity = merchantSignUpDTO.toEntity();
        userRepository.save(merchantEntity);
        return merchantEntity.getId();
    }

    /**
     * 인플루언서 회원가입
     */
    @Transactional
    public Long signupInfluencer(InfluencerSignUpDTO influencerSignUpDTO) {
        validateDuplicateMember(influencerSignUpDTO);
        InfluencerEntity influencerEntity = influencerSignUpDTO.toEntity();
        userRepository.save(influencerEntity);
        return influencerEntity.getId();
    }


    /**
     * 로그인
     */
    @Transactional
    public LoginResponseDTO login(LoginDTO loginDTO, HttpSession session) {
        UserEntity userEntity = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));

        if (!userEntity.getPassword().equals(loginDTO.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        session.setAttribute("username", userEntity.getUsername());

        String userType = userEntity instanceof MerchantEntity ? "소상공인" : "인플루언서";

        return LoginResponseDTO.builder()
                .username(userEntity.getUsername())
                .userType(userType)
                .message("로그인 성공")
                .build();
    }

    /**
     * 회원 정보 조회
     */
    public UserInfoDTO getCurrentUser(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (userEntity instanceof MerchantEntity) {
            MerchantEntity merchant = (MerchantEntity) userEntity;
            return MerchantInfoDTO.builder()
                    .email(merchant.getEmail())
                    .region(merchant.getRegion())
                    .storeType(merchant.getStoreType())
                    .storeName(merchant.getStoreName())
                    .storeAddress(merchant.getStoreAddress())
                    .build();
        } else {
            InfluencerEntity influencer = (InfluencerEntity) userEntity;
            return InfluencerInfoDTO.builder()
                    .email(influencer.getEmail())
                    .region(influencer.getRegion())
                    .snsType(influencer.getSnsType())
                    .snsUrl(influencer.getSnsUrl())
                    .followerCount(influencer.getFollowerCount())
                    .build();
        }
    }


    /**
     * 회원 수정
     */
    @Transactional
    public void updateMerchant(String username, MerchantUpdateDTO merchantUpdateDTO) {
        MerchantEntity merchantEntity = (MerchantEntity) userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (merchantUpdateDTO.getEmail() != null &&
                !merchantUpdateDTO.getEmail().equals(merchantEntity.getEmail()) &&
                userRepository.existsByEmail(merchantUpdateDTO.getEmail())) {
            throw new IllegalStateException("이미 사용중인 이메일입니다.");
        }

        merchantEntity.updateBasicInfo(
                merchantUpdateDTO.getPassword(),
                merchantUpdateDTO.getEmail(),
                merchantUpdateDTO.getRegion()
        );

        merchantEntity.updateStoreInfo(
                merchantUpdateDTO.getStoreType(),
                merchantUpdateDTO.getStoreName(),
                merchantUpdateDTO.getStoreAddress()
        );
    }

    @Transactional
    public void updateInfluencer(String username, InfluencerUpdateDTO influencerUpdateDTO) {
        InfluencerEntity influencerEntity = (InfluencerEntity) userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (influencerUpdateDTO.getEmail() != null &&
                !influencerUpdateDTO.getEmail().equals(influencerEntity.getEmail()) &&
                userRepository.existsByEmail(influencerUpdateDTO.getEmail())) {
            throw new IllegalStateException("이미 사용중인 이메일입니다.");
        }

        influencerEntity.updateBasicInfo(
                influencerUpdateDTO.getPassword(),
                influencerUpdateDTO.getEmail(),
                influencerUpdateDTO.getRegion()
        );

        influencerEntity.updateSnsInfo(
                influencerUpdateDTO.getSnsType(),
                influencerUpdateDTO.getSnsUrl(),
                influencerUpdateDTO.getFollowerCount()
        );
    }


    // 아이디, 이메일 중복 검사
    private void validateDuplicateMember(SignUpDTO signupDTO) {
        if (userRepository.existsByUsername(signupDTO.getUsername())) {
            throw new IllegalStateException("이미 사용중인 아이디입니다.");
        }

        if (userRepository.existsByEmail(signupDTO.getEmail())) {
            throw new IllegalStateException("이미 사용중인 이메일입니다.");
        }
    }
}
