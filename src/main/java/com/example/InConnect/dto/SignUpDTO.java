package com.example.InConnect.dto;

import com.example.InConnect.enums.GenderType;
import com.example.InConnect.enums.UserType;
import lombok.*;

import java.time.LocalDate;

/**
 * 회원가입 요청 시 공통으로 필요한 정보를 담는 부모 DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpDTO {
    private String username;
    private String password;
    private String email;
    private GenderType genderType;
    private LocalDate birthDate;
    private String region;

    public SignUpDTO(String username, String password, String email, GenderType genderType,
                     LocalDate birthDate, String region) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.genderType = genderType;
        this.birthDate = birthDate;
        this.region = region;
    }
}
