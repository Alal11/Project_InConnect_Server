package com.example.InConnect.service;

import com.example.InConnect.entity.UserEntity;
import com.example.InConnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void signup(UserEntity userEntity) {
        // 이메일 중복 확인
        if(userRepository.existsByEmail(userEntity.getEmail())){
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }

        userRepository.save(userEntity);
    }
}
