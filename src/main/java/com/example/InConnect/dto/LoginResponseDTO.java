package com.example.InConnect.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDTO {
    private String username;
    private String userType;
    private String message;

    @Builder
    public LoginResponseDTO(String username, String message, String userType) {
        this.username = username;
        this.userType = userType;
        this.message = message;
    }
}
