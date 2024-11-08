package com.example.InConnect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {  // 메인 화면 및 공통 페이지

    @GetMapping("/")  // 첫 화면 (로그인 전)
    public String home() {
        return "home";
    }

    @GetMapping("/main")  // 로그인 후 메인
    public String mainPage() {
        return "main";
    }

    // 서비스 소개 화면 등등..
}
