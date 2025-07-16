package com.codelab.hospital.auth.controller;

import com.codelab.hospital.jwt.JwtTokenProvider;
import com.codelab.hospital.user.dto.JoinRequest;
import com.codelab.hospital.user.dto.LoginRequest;
import com.codelab.hospital.user.dto.LoginResponse;
import com.codelab.hospital.user.domain.User;
import com.codelab.hospital.user.repository.UserRepository;
import com.codelab.hospital.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * 회원가입 및 로그인 요청을 처리하는 컨트롤러입니다.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody JoinRequest request) {
        userService.register(request);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if (user.getRole().equals("ROLE_ADMIN") && !user.isApproved()) {
            throw new IllegalArgumentException("관리자 계정은 승인 후 로그인할 수 있습니다.");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail(), user.getRole());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
