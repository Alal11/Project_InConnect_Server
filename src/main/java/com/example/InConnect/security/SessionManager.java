package com.example.InConnect.security;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionManager {

    public String getLoginUsername(HttpSession session) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return username;
    }
}
