package com.example.InConnect.controller;

import com.example.InConnect.dto.InfluencerSignUpDTO;
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

    @GetMapping("/signup")
    public String signupForm() {
        return "users/signup";
    }

    /**
     * 소상공인 회원가입
     */
    @PostMapping("/merchants/signup")
    @ResponseBody
    public ResponseEntity<Long> signupStoreOwner(@RequestBody MerchantSignUpDTO merchantSignUpDTO) {
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

    @GetMapping("/login")
    public String loginForm() {
        return "users/login";
    }
}
