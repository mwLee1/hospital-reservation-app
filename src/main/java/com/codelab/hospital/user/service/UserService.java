package com.codelab.hospital.user.service;

import com.codelab.hospital.user.domain.User;
import com.codelab.hospital.user.dto.JoinRequest;
import com.codelab.hospital.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 회원가입 및 로그인 관련 비즈니스 로직을 담당하는 서비스입니다.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public void register(JoinRequest request) {
        if (userRepository.findByUsername(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = request.isAdmin()
                ? User.createAdmin(request.getEmail(), encodedPassword, request.getName(), request.getPhone(), request.getBirth())
                : User.createUser(request.getEmail(), encodedPassword, request.getName(), request.getPhone(), request.getBirth());

        userRepository.save(user);
    }
}
