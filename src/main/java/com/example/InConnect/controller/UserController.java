package com.example.InConnect.controller;

import com.example.InConnect.dto.InfluencerSignUpDTO;
import com.example.InConnect.dto.LoginDTO;
import com.example.InConnect.dto.LoginResponseDTO;
import com.example.InConnect.dto.MerchantSignUpDTO;
import com.example.InConnect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signupForm() {
        return "users/signup";
    }

    /**
     * 소상공인 회원가입
     */
    @PostMapping("/merchants/signup")
    @ResponseBody
    public ResponseEntity<Long> signupMerchant(@RequestBody MerchantSignUpDTO merchantSignUpDTO) {
        return ResponseEntity.ok(userService.signupMerchant(merchantSignUpDTO));
    }

    /**
     * 인플루언서 회원가입
     */
    @PostMapping("/influencers/signup")
    @ResponseBody
    public ResponseEntity<Long> signupInfluencer(@RequestBody InfluencerSignUpDTO influencerSignUpDTO) {
        return ResponseEntity.ok(userService.signupInfluencer(influencerSignUpDTO));
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm() {
        return "users/login";
    }

    /**
     * 로그인 처리
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        LoginResponseDTO responseDTO = userService.login(loginDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
