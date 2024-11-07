package com.example.InConnect.service;

import com.example.InConnect.dto.InfluencerSignUpDTO;
import com.example.InConnect.dto.MerchantSignUpDTO;
import com.example.InConnect.dto.SignUpDTO;
import com.example.InConnect.entity.InfluencerEntity;
import com.example.InConnect.entity.MerchantEntity;
import com.example.InConnect.repository.UserRepository;
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
