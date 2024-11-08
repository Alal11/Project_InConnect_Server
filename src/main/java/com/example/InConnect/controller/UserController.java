package com.example.InConnect.controller;

import com.example.InConnect.dto.*;
import com.example.InConnect.security.SessionManager;
import com.example.InConnect.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final SessionManager sessionManager;

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
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        LoginResponseDTO responseDTO = userService.login(loginDTO, session);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * 회원 수정
     */
    @GetMapping("/update")
    public String updateForm() {
        return "users/update";
    }

    @GetMapping("/me")
    @ResponseBody
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        String username = sessionManager.getLoginUsername(session);
        return ResponseEntity.ok(userService.getCurrentUser(username));
    }

    @PutMapping("/merchants/me")
    @ResponseBody
    public ResponseEntity<Void> updateMerchant(@RequestBody MerchantUpdateDTO merchantUpdateDTO,
                                               HttpSession session) {
        String username = sessionManager.getLoginUsername(session);
        userService.updateMerchant(username, merchantUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/influencers/me")
    @ResponseBody
    public ResponseEntity<Void> updateInfluencer(@RequestBody InfluencerUpdateDTO influencerUpdateDTO,
                                                 HttpSession session) {
        String username = sessionManager.getLoginUsername(session);
        userService.updateInfluencer(username, influencerUpdateDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * 회원 탈퇴
     */
    @DeleteMapping("/me")
    @ResponseBody
    public ResponseEntity<Void> withdraw(HttpSession session) {
        userService.withdraw(session);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<Void> logout(HttpSession session) {
        userService.logout(session);
        return ResponseEntity.ok().build();
    }

}
